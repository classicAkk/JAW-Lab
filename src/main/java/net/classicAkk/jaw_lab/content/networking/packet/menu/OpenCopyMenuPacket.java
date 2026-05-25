package net.awyvrix.jaw_lab.content.networking.packet.menu;

import net.awyvrix.jaw_lab.Lab;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record OpenCopyMenuPacket(BlockPos pos) implements CustomPacketPayload {

    public static final Type<OpenCopyMenuPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Lab.MOD_ID, "open_copy_menu"));

    public static final StreamCodec<RegistryFriendlyByteBuf, OpenCopyMenuPacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,
                    OpenCopyMenuPacket::pos,
                    OpenCopyMenuPacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}