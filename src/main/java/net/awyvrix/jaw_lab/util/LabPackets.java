package net.awyvrix.jaw_lab.util;

import net.awyvrix.jaw_lab.Lab;
import net.awyvrix.jaw_lab.screen.processingPackets.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

public class LabPackets {
    public static final SimpleChannel INSTANCE = ChannelBuilder
            .named(Lab.resourceLocation("main_channel"))
            .clientAcceptedVersions((status, versions) -> true)
            .serverAcceptedVersions((status, versions) -> true)
            .networkProtocolVersion(1)
            .simpleChannel();

    private static int packetId = 0;

    private static int nextID() {
        return packetId++;
    }

    public static void register() {
        INSTANCE.messageBuilder(OpenCopyMenuPacket.class, nextID(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(OpenCopyMenuPacket::encode)
                .decoder(OpenCopyMenuPacket::decode)
                .consumerMainThread(OpenCopyMenuPacket::handle)
                .add();

        INSTANCE.messageBuilder(OpenMainMenuPacket.class, nextID(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(OpenMainMenuPacket::encode)
                .decoder(OpenMainMenuPacket::decode)
                .consumerMainThread(OpenMainMenuPacket::handle)
                .add();

        INSTANCE.messageBuilder(OpenNetworkMenuPacket.class, nextID(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(OpenNetworkMenuPacket::encode)
                .decoder(OpenNetworkMenuPacket::decode)
                .consumerMainThread(OpenNetworkMenuPacket::handle)
                .add();

        INSTANCE.messageBuilder(ProcessingPacket.class, nextID(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(ProcessingPacket::encode)
                .decoder(ProcessingPacket::decode)
                .consumerMainThread(ProcessingPacket::handle)
                .add();

        INSTANCE.messageBuilder(NetworkUserInfoPacket.class, nextID(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(NetworkUserInfoPacket::encode)
                .decoder(NetworkUserInfoPacket::decode)
                .consumerMainThread(NetworkUserInfoPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.send(message, PacketDistributor.SERVER.noArg());
    }

    public static <MSG> void sendToClient(MSG message, ServerPlayer player) {
        INSTANCE.send(message, PacketDistributor.PLAYER.with(player));
    }
}