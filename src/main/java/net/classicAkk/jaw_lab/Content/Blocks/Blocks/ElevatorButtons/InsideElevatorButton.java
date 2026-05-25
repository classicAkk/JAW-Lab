package net.classicAkk.jaw_lab.Content.Blocks.Blocks.ElevatorButtons;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class InsideElevatorButton extends FaceAttachedHorizontalDirectionalBlock {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    private final int ticksToStayPressed;

    // WALL
    private static final VoxelShape NORTH = Block.box(5, 3, 15, 11, 13, 16);
    private static final VoxelShape SOUTH = Block.box(5, 3, 0, 11, 13, 1);
    private static final VoxelShape WEST  = Block.box(15, 3, 5, 16, 13, 11);
    private static final VoxelShape EAST  = Block.box(0, 3, 5, 1, 13, 11);

    // FLOOR
    private static final VoxelShape FLOOR_X = Block.box(3, 0, 5, 13, 1, 11);
    private static final VoxelShape FLOOR_Z = Block.box(5, 0, 3, 11, 1, 13);

    // CEILING
    private static final VoxelShape CEILING_X = Block.box(3, 15, 5, 13, 16, 11);
    private static final VoxelShape CEILING_Z = Block.box(5, 15, 3, 11, 16, 13);

    public InsideElevatorButton(Properties properties, int ticksToStayPressed) {
        super(properties);

        this.ticksToStayPressed = ticksToStayPressed;
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(FACE, AttachFace.WALL).setValue(POWERED, false));
    }

    @Override
    protected MapCodec<? extends FaceAttachedHorizontalDirectionalBlock> codec() {
        return simpleCodec(properties ->
                new InsideElevatorButton(properties, ticksToStayPressed)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, FACE, POWERED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        AttachFace face = state.getValue(FACE);

        return switch (face) {
            case WALL -> getWallShape(facing);
            case FLOOR -> getFloorShape(facing);
            case CEILING -> getCeilingShape(facing);
        };
    }

    private static VoxelShape getWallShape(Direction facing) {
        return switch (facing) {
            case NORTH -> NORTH;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            case EAST -> EAST;
            default -> NORTH;
        };
    }

    private static VoxelShape getFloorShape(Direction facing) {
        return facing.getAxis() == Direction.Axis.X ? FLOOR_X : FLOOR_Z;
    }

    private static VoxelShape getCeilingShape(Direction facing) {
        return facing.getAxis() == Direction.Axis.X ? CEILING_X : CEILING_Z;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (state.getValue(POWERED)) return InteractionResult.CONSUME;
        press(state, level, pos);
        playClickSound(level, pos, true);

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private void press(BlockState state, Level level, BlockPos pos) {
        level.setBlock(pos, state.setValue(POWERED, true), 3);
        updateNeighbors(level, pos, state);
        level.scheduleTick(pos, this, ticksToStayPressed);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.getValue(POWERED)) return;

        level.setBlock(pos, state.setValue(POWERED, false), 3);
        updateNeighbors(level, pos, state);
        playClickSound(level, pos, false);
    }

    private void updateNeighbors(Level level, BlockPos pos, BlockState state) {
        level.updateNeighborsAt(pos, this);
        level.updateNeighborsAt(pos.relative(getConnectedDirection(state).getOpposite()), this);
    }

    private void playClickSound(LevelAccessor level, BlockPos pos, boolean on) {
        level.playSound(null, pos, on ? SoundEvents.STONE_BUTTON_CLICK_ON : SoundEvents.STONE_BUTTON_CLICK_OFF, SoundSource.BLOCKS);
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction dir) {
        return state.getValue(POWERED) ? 15 : 0;
    }

    @Override
    public int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction dir) {
        return state.getValue(POWERED) && getConnectedDirection(state) == dir ? 15 : 0;
    }
}