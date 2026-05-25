package net.classicAkk.jaw_lab.Content.Blocks.Blocks.Doors;

<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Content/Blocks/Blocks/Doors/CodeDoor.java
import net.classicAkk.jaw_lab.Content.Blocks.BlockEntities.Doors.CodeDoorBE;
import net.classicAkk.jaw_lab.Content.Blocks.BlockEntities.Doors.KeyDoorBE;
import net.classicAkk.jaw_lab.Content.Blocks.BlockEntities.Util.DoorState;
import net.classicAkk.jaw_lab.Content.Blocks.BlockEntities.Util.TickableBE;
import net.classicAkk.jaw_lab.Content.Blocks.LabBlockEntities;
import net.classicAkk.jaw_lab.Content.Blocks.LabBlocks;
import net.classicAkk.jaw_lab.Content.Items.LabItems;
import net.classicAkk.jaw_lab.Content.Sound.LabSounds;
import net.classicAkk.jaw_lab.Screen.CodeDoor.CodeDoorMenu;
import net.classicAkk.jaw_lab.Screen.DoorProgrammator.CodeDoor.DoorProgrammatorCodeMenu;
import net.classicAkk.jaw_lab.Screen.DoorProgrammator.KeyDoor.DoorProgrammatorKeyMenu;
=======
import net.awyvrix.jaw_lab.content.blocks.blockEntities.doors.CodeDoorBE;
import net.awyvrix.jaw_lab.content.blocks.blockEntities.util.DoorState;
import net.awyvrix.jaw_lab.content.blocks.blockEntities.util.TickableBE;
import net.awyvrix.jaw_lab.content.blocks.LabBlockEntities;
import net.awyvrix.jaw_lab.content.blocks.LabBlocks;
import net.awyvrix.jaw_lab.content.items.LabItems;
import net.awyvrix.jaw_lab.content.sound.LabSounds;
import net.awyvrix.jaw_lab.screen.codeDoor.CodeDoorMenu;
import net.awyvrix.jaw_lab.screen.doorProgrammator.CodeDoor.DoorProgrammatorCodeMenu;
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/content/blocks/blocks/doors/CodeDoor.java
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.level.block.Block;

import java.util.stream.Stream;

public class CodeDoor extends Block implements EntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<DoorState> STATE = EnumProperty.create("state", DoorState.class);

    public static final VoxelShape NORTH = Stream.of(
            Block.box(6.2, 6, 6.9, 9.8, 7, 9.1),
            Block.box(15, 0, 6.5, 16, 16, 9.5),
            Block.box(0, 0, 6.5, 1, 16, 9.5),
            Block.box(1, 15, 6.5, 15, 16, 9.5),
            Block.box(1, 0, 7.5, 15, 15, 8.5),
            Block.box(6, 2, 7, 10, 7.4, 9),
            Block.box(6.5, 2.6, 6.95, 9.5, 5.6, 9.05)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(6.9, 6, 6.2, 9.1, 7, 9.8),
            Block.box(6.5, 0, 15, 9.5, 16, 16),
            Block.box(6.5, 0, 0, 9.5, 16, 1),
            Block.box(6.5, 15, 1, 9.5, 16, 15),
            Block.box(7.5, 0, 1, 8.5, 15, 15),
            Block.box(7, 2, 6, 9, 7.4, 10),
            Block.box(6.95, 2.6, 6.5, 9.05, 5.6, 9.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(6.2, 6, 6.9, 9.8, 7, 9.1),
            Block.box(15, 0, 6.5, 16, 16, 9.5),
            Block.box(0, 0, 6.5, 1, 16, 9.5),
            Block.box(1, 15, 6.5, 15, 16, 9.5),
            Block.box(1, 0, 7.5, 15, 15, 8.5),
            Block.box(6, 2, 7, 10, 7.4, 9),
            Block.box(6.5, 2.6, 6.95, 9.5, 5.6, 9.05)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(6.9, 6, 6.2, 9.1, 7, 9.8),
            Block.box(6.5, 0, 15, 9.5, 16, 16),
            Block.box(6.5, 0, 0, 9.5, 16, 1),
            Block.box(6.5, 15, 1, 9.5, 16, 15),
            Block.box(7.5, 0, 1, 8.5, 15, 15),
            Block.box(7, 2, 6, 9, 7.4, 10),
            Block.box(6.95, 2.6, 6.5, 9.05, 5.6, 9.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape NORTH_OPENED = Stream.of(
            Block.box(0, 0, 6.5, 1, 16, 9.5),
            Block.box(15, 0, 6.5, 16, 16, 9.5),
            Block.box(1, 15, 6.5, 15, 16, 9.5),
            Block.box(1, 0, 7.5, 2, 15, 8.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_OPENED = Stream.of(
            Block.box(6.5, 0, 15, 9.5, 16, 16),
            Block.box(6.5, 0, 0, 9.5, 16, 1),
            Block.box(6.5, 15, 1, 9.5, 16, 15),
            Block.box(7.5, 0, 14, 8.5, 15, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_OPENED = Stream.of(
            Block.box(15, 0, 6.5, 16, 16, 9.5),
            Block.box(0, 0, 6.5, 1, 16, 9.5),
            Block.box(1, 15, 6.5, 15, 16, 9.5),
            Block.box(14, 0, 7.5, 15, 15, 8.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_OPENED = Stream.of(
            Block.box(6.5, 0, 0, 9.5, 16, 1),
            Block.box(6.5, 0, 15, 9.5, 16, 16),
            Block.box(6.5, 15, 1, 9.5, 16, 15),
            Block.box(7.5, 0, 1, 8.5, 15, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public CodeDoor(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(STATE, DoorState.CLOSED));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        if (state.getValue(STATE) != DoorState.OPENED) {
            return switch (state.getValue(FACING)) {
                case EAST -> EAST;
                case SOUTH -> SOUTH;
                case WEST -> WEST;
                default -> NORTH;
            };
        }
        else {
            return switch (state.getValue(FACING)) {
                case EAST -> EAST_OPENED;
                case SOUTH -> SOUTH_OPENED;
                case WEST -> WEST_OPENED;
                default -> NORTH_OPENED;
            };
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(STATE);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        if (pos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(pos.above()).canBeReplaced(context)) {
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor world, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                           Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide) return ItemInteractionResult.SUCCESS;
        if (!(player instanceof ServerPlayer serverPlayer)) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        if (!(level.getBlockEntity(pos) instanceof CodeDoorBE codeDoor)) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        DoorState doorState = state.getValue(CodeDoor.STATE);

        if (doorState == DoorState.ERROR) return ItemInteractionResult.FAIL;
        if (doorState == DoorState.CLOSED) {
            if (stack.is(LabItems.DOOR_PROGRAMMATOR.get()) && !player.isShiftKeyDown()) {
                serverPlayer.openMenu(new SimpleMenuProvider((id, inv, p) -> new DoorProgrammatorCodeMenu(id, inv, level.getBlockEntity(pos),
                        ContainerLevelAccess.create(level, pos)), Component.translatable("block.lab.code_door")), pos);

                return ItemInteractionResult.SUCCESS;
            }

            if (stack.is(LabItems.DOOR_PROGRAMMATOR.get()) && player.isShiftKeyDown()) {
                return ItemInteractionResult.SUCCESS;
            }

            serverPlayer.openMenu(new SimpleMenuProvider((id, inv, p) -> new CodeDoorMenu(id, inv, level.getBlockEntity(pos),
                    ContainerLevelAccess.create(level, pos)), Component.translatable("block.lab.code_door")), pos);

            return ItemInteractionResult.SUCCESS;
        }

        if (doorState == DoorState.OPENED && !codeDoor.getAutoClose()) {
            int x = pos.getX(), y = pos.getY(), z = pos.getZ();
            level.playSound(null, pos, LabSounds.KEY_DOOR_CLOSE.get(), SoundSource.BLOCKS, 0.5f, 1f);
            level.setBlockAndUpdate(pos, state.setValue(CodeDoor.STATE, DoorState.ERROR));
            level.setBlockAndUpdate(pos.below(), LabBlocks.DOOR_BOTTOM.get().withPropertiesOf(state.setValue(CodeDoor.STATE, DoorState.CLOSED)));

            if (level instanceof ServerLevel server) {
                server.sendParticles(ParticleTypes.SMOKE,
                        x + 0.5, y, z + 0.5, 20, 0.2, 0.4, 0.2, 0.02);
            }

            codeDoor.resetTick();
        }

        return ItemInteractionResult.SUCCESS;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        BlockState bottom = LabBlocks.DOOR_BOTTOM.get()
                .defaultBlockState()
                .setValue(DoorBottom.FACING, state.getValue(DoorBottom.FACING))
                .setValue(DoorBottom.STATE, DoorState.CLOSED);

        level.setBlock(pos, bottom, 3);
        level.setBlock(pos.above(), LabBlocks.CODE_DOOR.get().withPropertiesOf(state).setValue(STATE, DoorState.CLOSED), 3);
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (level.getBlockState(pos.below()).getBlock() == LabBlocks.DOOR_BOTTOM.get()) {
            BlockPos below = pos.below();
            level.destroyBlock(below, false);
        }

        super.playerWillDestroy(level, pos, state, player);
        return state;
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state){
        return LabBlockEntities.CODE_DOOR_BE.get().create(pos,state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type){
        return TickableBE.getTickerHelper(level);
    }
}