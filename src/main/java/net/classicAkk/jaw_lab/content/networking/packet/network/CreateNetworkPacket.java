package net.awyvrix.jaw_lab.content.networking.packet.network;

import net.awyvrix.jaw_lab.Lab;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record CreateNetworkPacket(String network) implements CustomPacketPayload {

    public static final Type<CreateNetworkPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Lab.MOD_ID, "create_network"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CreateNetworkPacket> STREAM_CODEC =
            StreamCodec.composite(ByteBufCodecs.STRING_UTF8, CreateNetworkPacket::network, CreateNetworkPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}