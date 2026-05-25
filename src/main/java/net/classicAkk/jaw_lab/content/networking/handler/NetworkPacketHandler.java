package net.awyvrix.jaw_lab.content.networking.handler;

import net.awyvrix.jaw_lab.content.interactions.NetworkInteractions;
import net.awyvrix.jaw_lab.content.network.UUIDFetcher;
import net.awyvrix.jaw_lab.content.networking.packet.network.*;
import net.awyvrix.jaw_lab.screen.KCPNetwork.KeycardProgrammatorNetworkMenu;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public final class NetworkPacketHandler {

    private NetworkPacketHandler() {}

    public static void handleCreateNetwork(final CreateNetworkPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            if (!(player.containerMenu instanceof KeycardProgrammatorNetworkMenu menu)) return;
            NetworkInteractions.createNetwork(
                    player.serverLevel(),
                    packet.network(),
                    player,
                    menu
            );
        });
    }

    public static void handleDeleteNetwork(final DeleteNetworkPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            if (!(player.containerMenu instanceof KeycardProgrammatorNetworkMenu menu)) return;
            NetworkInteractions.deleteNetwork(
                    player.serverLevel(),
                    packet.network(),
                    player,
                    menu
            );
        });
    }

    public static void handleFindUser(final FindUserPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {

            ServerPlayer player = (ServerPlayer) context.player();
            if (!(player.containerMenu instanceof KeycardProgrammatorNetworkMenu menu)) return;
            menu.setAccessLevel(NetworkInteractions.getUserAccessLevel(player.serverLevel(), packet.network(), packet.username()));
            menu.setRole(NetworkInteractions.getUserRole(player.serverLevel(), packet.network(), packet.username()));
            menu.broadcastChanges();
        });
    }

    public static void handleAddUser(final AddUserPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            UUIDFetcher.getPlayerUUIDAsync(player.getServer(), packet.username()).thenAccept(uuid -> {
                if (!(player.containerMenu instanceof KeycardProgrammatorNetworkMenu menu)) return;
                if (uuid == null) return;
                NetworkInteractions.addUser(
                        player.serverLevel(),
                        uuid,
                        packet.username(),
                        packet.network(),
                        menu
                );
            });
        });
    }

    public static void handleRemoveUser(final RemoveUserPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            if (!(player.containerMenu instanceof KeycardProgrammatorNetworkMenu menu)) return;
            NetworkInteractions.removeUser(
                    player.serverLevel(),
                    packet.network(),
                    player,
                    packet.username(),
                    menu
            );
        });
    }

    public static void handleSetUserRole(final SetUserRolePacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            if (!(player.containerMenu instanceof KeycardProgrammatorNetworkMenu menu)) return;
            NetworkInteractions.setRole(
                    player.serverLevel(),
                    packet.network(),
                    player,
                    packet.username(),
                    packet.role(),
                    menu
            );
        });
    }

    public static void handleIncreaseUserLevel(final IncreaseUserLevelPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            if (!(player.containerMenu instanceof KeycardProgrammatorNetworkMenu menu)) return;
            NetworkInteractions.increaseUserLevel(
                    player.serverLevel(),
                    packet.network(),
                    player,
                    packet.username(),
                    menu
            );
        });
    }

    public static void handleDecreaseUserLevel(final DecreaseUserLevelPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            if (!(player.containerMenu instanceof KeycardProgrammatorNetworkMenu menu)) return;
            NetworkInteractions.decreaseUserLevel(
                    player.serverLevel(),
                    packet.network(),
                    player,
                    packet.username(),
                    menu
            );
        });
    }
}