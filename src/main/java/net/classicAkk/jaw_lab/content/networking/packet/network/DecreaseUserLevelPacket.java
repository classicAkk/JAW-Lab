package net.awyvrix.jaw_lab.content.networking.packet.network;

import net.awyvrix.jaw_lab.Lab;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record DecreaseUserLevelPacket(String network, String username) implements CustomPacketPayload {

    public static final Type<DecreaseUserLevelPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Lab.MOD_ID, "decrease_user_level"));

    public static final StreamCodec<RegistryFriendlyByteBuf, DecreaseUserLevelPacket> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8,
                    DecreaseUserLevelPacket::network,
                    ByteBufCodecs.STRING_UTF8,
                    DecreaseUserLevelPacket::username,
                    DecreaseUserLevelPacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}