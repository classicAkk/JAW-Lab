package net.awyvrix.jaw_lab.content.networking.packet.doors;

import net.awyvrix.jaw_lab.Lab;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record SetCodePacket(BlockPos pos, String code)
        implements CustomPacketPayload {

    public static final Type<SetCodePacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Lab.MOD_ID, "set_code"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SetCodePacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,
                    SetCodePacket::pos,
                    ByteBufCodecs.STRING_UTF8,
                    SetCodePacket::code,
                    SetCodePacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}