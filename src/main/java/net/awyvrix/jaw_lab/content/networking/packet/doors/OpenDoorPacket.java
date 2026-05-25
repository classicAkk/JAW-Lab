package net.awyvrix.jaw_lab.content.networking.packet.doors;

import net.awyvrix.jaw_lab.Lab;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record OpenDoorPacket(BlockPos pos)
        implements CustomPacketPayload {

    public static final Type<OpenDoorPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Lab.MOD_ID, "open_door"));

    public static final StreamCodec<RegistryFriendlyByteBuf, OpenDoorPacket> STREAM_CODEC =
            StreamCodec.composite(BlockPos.STREAM_CODEC, OpenDoorPacket::pos, OpenDoorPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}