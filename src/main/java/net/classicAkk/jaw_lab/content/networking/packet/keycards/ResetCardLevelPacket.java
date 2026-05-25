package net.awyvrix.jaw_lab.content.networking.packet.keycards;

import net.awyvrix.jaw_lab.Lab;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ResetCardLevelPacket() implements CustomPacketPayload {

    public static final Type<ResetCardLevelPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Lab.MOD_ID, "reset_card_level"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ResetCardLevelPacket> STREAM_CODEC =
            StreamCodec.unit(new ResetCardLevelPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}