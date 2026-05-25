package net.awyvrix.jaw_lab.content.networking.packet.network;

import net.awyvrix.jaw_lab.Lab;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record FindUserPacket(String network, String username) implements CustomPacketPayload {

    public static final Type<FindUserPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Lab.MOD_ID, "find_user"));

    public static final StreamCodec<RegistryFriendlyByteBuf, FindUserPacket> STREAM_CODEC =
            StreamCodec.composite(ByteBufCodecs.STRING_UTF8, FindUserPacket::network, ByteBufCodecs.STRING_UTF8, FindUserPacket::username, FindUserPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}