package net.awyvrix.jaw_lab.content.networking.handler;

import net.awyvrix.jaw_lab.content.blocks.blockEntities.KeycardProgrammatorBE;
import net.awyvrix.jaw_lab.content.networking.packet.menu.OpenCopyMenuPacket;
import net.awyvrix.jaw_lab.content.networking.packet.menu.OpenMainMenuPacket;
import net.awyvrix.jaw_lab.content.networking.packet.menu.OpenNetworkMenuPacket;
import net.awyvrix.jaw_lab.screen.KCPCopy.KeycardProgrammatorCopyMenu;
import net.awyvrix.jaw_lab.screen.KCPMain.KeycardProgrammatorMainMenu;
import net.awyvrix.jaw_lab.screen.KCPNetwork.KeycardProgrammatorNetworkMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public final class MenuPacketHandler {
    private MenuPacketHandler() {}

    public static void handleOpenNetworkMenu(final OpenNetworkMenuPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            BlockEntity blockEntity = player.level().getBlockEntity(packet.pos());

            if (!(blockEntity instanceof KeycardProgrammatorBE)) return;
            player.openMenu(
                    new SimpleMenuProvider(
                            (containerId, inventory, p) ->
                                    new KeycardProgrammatorNetworkMenu(
                                            containerId,
                                            inventory,
                                            blockEntity,
                                            new SimpleContainerData(2)
                                    ),
                            Component.translatable("block.lab.ui.kcp_network")
                    ),
                    buffer -> buffer.writeBlockPos(packet.pos())
            );
        });
    }

    public static void handleOpenMainMenu(final OpenMainMenuPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            BlockEntity blockEntity = player.level().getBlockEntity(packet.pos());

            if (!(blockEntity instanceof KeycardProgrammatorBE)) return;
            player.openMenu(
                    new SimpleMenuProvider(
                            (containerId, inventory, p) ->
                                    new KeycardProgrammatorMainMenu(
                                            containerId,
                                            inventory,
                                            blockEntity
                                    ),
                            Component.translatable("block.lab.ui.kcp")
                    ),
                    buffer -> buffer.writeBlockPos(packet.pos())
            );
        });
    }

    public static void handleOpenCopyMenu(final OpenCopyMenuPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            BlockEntity blockEntity = player.level().getBlockEntity(packet.pos());

            if (!(blockEntity instanceof KeycardProgrammatorBE)) return;
            player.openMenu(
                    new SimpleMenuProvider(
                            (containerId, inventory, p) ->
                                    new KeycardProgrammatorCopyMenu(
                                            containerId,
                                            inventory,
                                            blockEntity,
                                            new SimpleContainerData(1)
                                    ),
                            Component.translatable("block.lab.ui.kcp_copy")
                    ),
                    buffer -> buffer.writeBlockPos(packet.pos())
            );
        });
    }
}