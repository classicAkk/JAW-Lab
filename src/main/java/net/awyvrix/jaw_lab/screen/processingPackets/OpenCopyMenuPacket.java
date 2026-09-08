package net.awyvrix.jaw_lab.screen.processingPackets;

import net.awyvrix.jaw_lab.content.blocks.blockEntities.KeycardProgrammatorBE;
import net.awyvrix.jaw_lab.screen.KCPCopy.KeycardProgrammatorCopyMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.network.CustomPayloadEvent;

import java.util.function.Supplier;

public class OpenCopyMenuPacket {
    private final BlockPos pos;

    public OpenCopyMenuPacket(BlockPos pos) {
        this.pos = pos;
    }

    public static void encode(OpenCopyMenuPacket msg, FriendlyByteBuf buf) {
        buf.writeBlockPos(msg.pos);
    }

    public static OpenCopyMenuPacket decode(FriendlyByteBuf buf) {
        return new OpenCopyMenuPacket(buf.readBlockPos());
    }

    public static void handle(OpenCopyMenuPacket msg, CustomPayloadEvent.Context ctx) {
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();

            if (player == null) return;
            BlockEntity entity = player.level().getBlockEntity(msg.pos);

            if (entity == null) return;
            if (!(entity instanceof KeycardProgrammatorBE)) return;
            ContainerData data = new SimpleContainerData(2);
            player.openMenu(new MenuProvider() {

                @Override
                public Component getDisplayName() {
                    return Component.translatable("block.lab.ui.kcp_copy");
                }

                @Override
                public AbstractContainerMenu createMenu(int pContainerId, Inventory inv, Player p) {
                    return new KeycardProgrammatorCopyMenu(pContainerId, inv, entity, data);
                }
            }, buf -> buf.writeBlockPos(msg.pos));
        });
        ctx.setPacketHandled(true);
    }
}