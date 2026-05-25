<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Content/Blocks/BlockEntities/Barriers/BarrierGateConnectorBE.java
package net.classicAkk.jaw_lab.Content.Blocks.BlockEntities.Barriers;
=======
package net.awyvrix.jaw_lab.content.blocks.blockEntities.barriers;
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/content/blocks/blockEntities/Barriers/BarrierGateConnectorBE.java

import net.classicAkk.jaw_lab.Content.Blocks.LabBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BarrierGateConnectorBE extends BlockEntity {
    private int ticks;
    private int timer;
    private boolean bstate;

    public BarrierGateConnectorBE(BlockPos pos, BlockState state) {
        super(LabBlockEntities.BARRIER_GATE_BE.get(), pos, state);
    }

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
        tag.putBoolean("bState", bstate);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
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
}