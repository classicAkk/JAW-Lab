package net.awyvrix.jaw_lab.content.networking.packet.doors;

import net.awyvrix.jaw_lab.Lab;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record CodeDoorSubmitPacket(BlockPos pos, String code) implements CustomPacketPayload {

    public static final Type<CodeDoorSubmitPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Lab.MOD_ID, "code_door_submit"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CodeDoorSubmitPacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,
                    CodeDoorSubmitPacket::pos,
                    ByteBufCodecs.STRING_UTF8,
                    CodeDoorSubmitPacket::code,
                    CodeDoorSubmitPacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}