package net.awyvrix.jaw_lab.content.networking.packet.keycards;

import net.awyvrix.jaw_lab.Lab;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record RemoveCardNetworkPacket() implements CustomPacketPayload {

    public static final Type<RemoveCardNetworkPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Lab.MOD_ID, "remove_card_network"));

    public static final StreamCodec<RegistryFriendlyByteBuf, RemoveCardNetworkPacket> STREAM_CODEC =
            StreamCodec.unit(new RemoveCardNetworkPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}