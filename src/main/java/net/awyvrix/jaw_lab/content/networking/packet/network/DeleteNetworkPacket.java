package net.awyvrix.jaw_lab.content.networking.packet.network;

import net.awyvrix.jaw_lab.Lab;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record DeleteNetworkPacket(String network)
        implements CustomPacketPayload {

    public static final Type<DeleteNetworkPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Lab.MOD_ID, "delete_network"));

    public static final StreamCodec<RegistryFriendlyByteBuf, DeleteNetworkPacket> STREAM_CODEC =
            StreamCodec.composite(ByteBufCodecs.STRING_UTF8, DeleteNetworkPacket::network, DeleteNetworkPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}