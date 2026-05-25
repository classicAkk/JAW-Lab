package net.awyvrix.jaw_lab.content.networking.packet.keycards;

import net.awyvrix.jaw_lab.Lab;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record SetUUIDPacket() implements CustomPacketPayload {

    public static final Type<SetUUIDPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Lab.MOD_ID, "set_uuid"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SetUUIDPacket> STREAM_CODEC =
            StreamCodec.unit(new SetUUIDPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}