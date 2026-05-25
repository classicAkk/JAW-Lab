package net.awyvrix.jaw_lab.content.networking.packet.keycards;

import net.awyvrix.jaw_lab.Lab;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record DecreaseCardLevelPacket(String network)
        implements CustomPacketPayload {

    public static final Type<DecreaseCardLevelPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Lab.MOD_ID, "decrease_card_level"));

    public static final StreamCodec<RegistryFriendlyByteBuf, DecreaseCardLevelPacket> STREAM_CODEC =
            StreamCodec.composite(ByteBufCodecs.STRING_UTF8, DecreaseCardLevelPacket::network, DecreaseCardLevelPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}