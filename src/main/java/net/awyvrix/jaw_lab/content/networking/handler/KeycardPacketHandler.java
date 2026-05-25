package net.awyvrix.jaw_lab.content.networking.handler;

import net.awyvrix.jaw_lab.content.interactions.KeycardInteractions;
import net.awyvrix.jaw_lab.content.networking.packet.keycards.*;
import net.awyvrix.jaw_lab.screen.KCPCopy.KeycardProgrammatorCopyMenu;
import net.awyvrix.jaw_lab.screen.KCPMain.KeycardProgrammatorMainMenu;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public final class KeycardPacketHandler {

    private KeycardPacketHandler() {}

    public static void handleResetKeycard(final ResetKeycardPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();

            if (!(player.containerMenu instanceof KeycardProgrammatorMainMenu menu)) return;
            KeycardInteractions.resetKeycard(menu, player, 36);
        });
    }

    public static void handleSetUUID(final SetUUIDPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            if (!(player.containerMenu instanceof KeycardProgrammatorMainMenu menu)) return;

            KeycardInteractions.setUUID(menu, player, 36);
        });
    }

    public static void handleSetUsername(final SetUsernamePacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();

            if (!(player.containerMenu instanceof KeycardProgrammatorMainMenu menu)) return;
            KeycardInteractions.setUsername(menu, 36, packet.username());
        });
    }

    public static void handleIncreaseCardLevel(final IncreaseCardLevelPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();

            if (!(player.containerMenu instanceof KeycardProgrammatorMainMenu menu)) return;
            KeycardInteractions.increaseLevel(
                    menu,
                    36,
                    player.serverLevel(),
                    packet.network(),
                    player
            );
        });
    }

    public static void handleDecreaseCardLevel(final DecreaseCardLevelPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();

            if (!(player.containerMenu instanceof KeycardProgrammatorMainMenu menu)) return;
            KeycardInteractions.decreaseLevel(
                    menu,
                    36,
                    player.serverLevel(),
                    packet.network(),
                    player
            );
        });
    }

    public static void handleResetCardLevel(final ResetCardLevelPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();

            if (!(player.containerMenu instanceof KeycardProgrammatorMainMenu menu)) return;
            KeycardInteractions.resetLevel(menu, 36);
        });
    }

    public static void handleAddCardNetwork(final AddCardNetworkPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();

            if (!(player.containerMenu instanceof KeycardProgrammatorMainMenu menu)) return;
            KeycardInteractions.addNetwork(
                    menu,
                    player.level(),
                    36,
                    packet.network(),
                    player
            );
        });
    }

    public static void handleRemoveCardNetwork(final RemoveCardNetworkPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();

            if (!(player.containerMenu instanceof KeycardProgrammatorMainMenu menu)) return;
            KeycardInteractions.removeNetwork(menu, 36);
        });
    }

    public static void handleCopyCard(final CopyCardPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();

            if (!(player.containerMenu instanceof KeycardProgrammatorCopyMenu menu)) return;
            KeycardInteractions.copyCard(menu, 36);
        });
    }
}