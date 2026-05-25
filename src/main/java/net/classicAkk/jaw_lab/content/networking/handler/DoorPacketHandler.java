package net.awyvrix.jaw_lab.content.networking.handler;

import net.awyvrix.jaw_lab.content.blocks.blockEntities.doors.CodeDoorBE;
import net.awyvrix.jaw_lab.content.interactions.DoorInteractions;
import net.awyvrix.jaw_lab.content.networking.packet.doors.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public final class DoorPacketHandler {

    private DoorPacketHandler() {}

    public static void handleOpenDoor(final OpenDoorPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            BlockEntity blockEntity = player.level().getBlockEntity(packet.pos());

            if (!(blockEntity instanceof CodeDoorBE)) return;
            DoorInteractions.setDoor(
                    blockEntity,
                    player.level(),
                    player
            );
        });
    }

    public static void handleSetCode(final SetCodePacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            BlockEntity blockEntity = player.level().getBlockEntity(packet.pos());
            DoorInteractions.setCode(
                    blockEntity,
                    player.level(),
                    packet.code(),
                    player
            );
        });
    }

    public static void handleResetDoor(final ResetDoorPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            BlockEntity blockEntity = player.level().getBlockEntity(packet.pos());
            DoorInteractions.resetDoor(
                    blockEntity,
                    player.level(),
                    packet.network(),
                    player
            );
        });
    }

    public static void handleSwitchAutoClose(final SwitchAutoClosePacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            BlockEntity blockEntity = player.level().getBlockEntity(packet.pos());
            DoorInteractions.switchAutoClose(blockEntity, player.level());
        });
    }

    public static void handleSetDoorNetwork(final SetDoorNetworkPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            BlockEntity blockEntity = player.level().getBlockEntity(packet.pos());
            DoorInteractions.setNetwork(
                    blockEntity,
                    player.level(),
                    packet.network(),
                    packet.currentNetwork(),
                    player
            );
        });
    }

    public static void handleIncrementDoorLevel(final IncrementDoorLevelPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            BlockEntity blockEntity = player.level().getBlockEntity(packet.pos());
            DoorInteractions.incrementLevel(
                    blockEntity,
                    player.level(),
                    packet.network(),
                    player
            );
        });
    }

    public static void handleDecrementDoorLevel(final DecrementDoorLevelPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            BlockEntity blockEntity = player.level().getBlockEntity(packet.pos());
            DoorInteractions.decrementLevel(
                    blockEntity,
                    player.level(),
                    packet.network(),
                    player
            );
        });
    }
}