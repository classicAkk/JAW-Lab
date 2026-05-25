<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Content/Blocks/BlockEntities/Barriers/BarrierGateOffBE.java
package net.classicAkk.jaw_lab.Content.Blocks.BlockEntities.Barriers;

import net.classicAkk.jaw_lab.Content.Blocks.BlockEntities.Util.TickableBE;
import net.classicAkk.jaw_lab.Content.Blocks.LabBlockEntities;
import net.classicAkk.jaw_lab.Content.Blocks.LabBlocks;
=======
package net.awyvrix.jaw_lab.content.blocks.blockEntities.barriers;

import net.awyvrix.jaw_lab.content.blocks.blockEntities.util.TickableBE;
import net.awyvrix.jaw_lab.content.blocks.LabBlockEntities;
import net.awyvrix.jaw_lab.content.blocks.LabBlocks;
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/content/blocks/blockEntities/Barriers/BarrierGateOffBE.java
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BarrierGateOffBE extends BlockEntity implements TickableBE {
    private int clevel;
    private boolean bstate;

    public BarrierGateOffBE(BlockPos pos, BlockState state) {
        super(LabBlockEntities.BARRIER_GATE_OFF.get(), pos, state);
    }

    public int checkDirection(){
        Direction dir = this.level.getBlockState(this.worldPosition).getValue(HorizontalDirectionalBlock.FACING);
        Direction dir2;
        BlockEntity oppositeBlockEntity;

        for (int i = 1; i < 7; i++) {
            if (this.level.getBlockState(positioner(dir, i)).getBlock() == LabBlocks.BARRIER_GATE_OFF.get()) {
                oppositeBlockEntity = this.level.getBlockEntity(positioner(dir, i));
                dir2 = this.level.getBlockState(positioner(dir, i)).getValue(HorizontalDirectionalBlock.FACING);
                if (oppositeBlockEntity instanceof BarrierGateOffBE barrierGateBE && dir2.getOpposite() == dir) {
                    return i;
                }
            }
        }
        return 0;
    }

    public BlockPos positioner(Direction dir, int i){
        if(dir == Direction.EAST) {
            return this.worldPosition.east(i);
        }
        if(dir == Direction.WEST) {
            return this.worldPosition.west(i);
        }
        if(dir == Direction.NORTH) {
            return this.worldPosition.north(i);
        }
        if(dir == Direction.SOUTH) {
            return this.worldPosition.south(i);
        }
        return this.worldPosition;
    }

    public void setData(BlockEntity be, boolean state){
        if (be instanceof BarrierGateBE block) {
            block.setBState(state);
            block.setChanged();
        }
    }

    public BlockState stateInverter(BlockState state){
        Direction current = state.getValue(HorizontalDirectionalBlock.FACING);
        Direction opposite = current.getOpposite();

        return state.setValue(HorizontalDirectionalBlock.FACING, opposite);
    }

    /** Access Level  */
    public void setClevel(int clevel){
        this.clevel = clevel;
        setChanged();
    }
    public int getCLevel() {
        return clevel;
    }

    /** Current State (On/Off) */
    public void setBState(boolean bstate){
        this.bstate = bstate;
        setChanged();
    }

    public boolean getBState() {
        return bstate;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("cLevel", clevel);
        tag.putBoolean("bState", bstate);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        clevel = tag.getInt("cLevel");
        bstate = tag.getBoolean("bState");
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
        loadAdditional(tag, registries);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void updateBarrier() {
        Direction dir = this.level.getBlockState(this.worldPosition).getValue(HorizontalDirectionalBlock.FACING);
        BlockEntity blockEntity = this.level.getBlockEntity(this.worldPosition);
        BlockEntity blockEntityOpposite = this.level.getBlockEntity(positioner(dir, checkDirection()));
        int current = checkDirection();

        if (blockEntity instanceof BarrierGateOffBE barrierGateoffBE) {
            Direction dir2 = this.level.getBlockState(positioner(dir, checkDirection())).getValue(HorizontalDirectionalBlock.FACING);
            if (blockEntityOpposite instanceof BarrierGateOffBE barrierGateOffOppositeBE && dir2.getOpposite() == dir) {
                if (barrierGateoffBE.bstate && barrierGateOffOppositeBE.bstate) {
                    this.level.setBlockAndUpdate(this.worldPosition, LabBlocks.BARRIER_GATE.get().withPropertiesOf(this.level.getBlockState(this.worldPosition)));
                    this.level.setBlockAndUpdate(positioner(dir, checkDirection()), LabBlocks.BARRIER_GATE.get().withPropertiesOf(this.level.getBlockState(positioner(dir, checkDirection()))));
                    BlockEntity blockEntityOppositeNew = this.level.getBlockEntity(positioner(dir, current));
                    BlockEntity blockEntityNew = this.level.getBlockEntity(this.worldPosition);
                    setData(blockEntityOppositeNew, true);
                    setData(blockEntityNew, true);
                }
                else {
                    setBState(false);
                }
            }
        }
    }

    @Override
    public void tick() {
        if (this.level == null || this.level.isClientSide()) {
            return;
        }
        updateBarrier();
    }
}