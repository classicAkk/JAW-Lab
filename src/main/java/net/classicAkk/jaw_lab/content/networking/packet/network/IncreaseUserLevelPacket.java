package net.awyvrix.jaw_lab.content.networking.packet.network;

import net.awyvrix.jaw_lab.Lab;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record IncreaseUserLevelPacket(String network, String username) implements CustomPacketPayload {

    public static final Type<IncreaseUserLevelPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(
                    Lab.MOD_ID,
                    "increase_user_level"
            ));

    public static final StreamCodec<RegistryFriendlyByteBuf, IncreaseUserLevelPacket> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8,
                    IncreaseUserLevelPacket::network,
                    ByteBufCodecs.STRING_UTF8,
                    IncreaseUserLevelPacket::username,
                    IncreaseUserLevelPacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}