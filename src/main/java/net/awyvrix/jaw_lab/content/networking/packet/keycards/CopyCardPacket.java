package net.awyvrix.jaw_lab.content.networking.packet.keycards;

import net.awyvrix.jaw_lab.Lab;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record CopyCardPacket()
        implements CustomPacketPayload {

    public static final Type<CopyCardPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Lab.MOD_ID, "copy_card"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CopyCardPacket> STREAM_CODEC =
            StreamCodec.unit(new CopyCardPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}