package net.awyvrix.jaw_lab.content.networking.packet.doors;

import net.awyvrix.jaw_lab.Lab;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record SwitchAutoClosePacket(BlockPos pos) implements CustomPacketPayload {

    public static final Type<SwitchAutoClosePacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Lab.MOD_ID, "switch_auto_close"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SwitchAutoClosePacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,
                    SwitchAutoClosePacket::pos,
                    SwitchAutoClosePacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}