package net.awyvrix.jaw_lab.content.networking.packet.doors;

import net.awyvrix.jaw_lab.Lab;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record IncrementDoorLevelPacket(BlockPos pos, String network) implements CustomPacketPayload {

    public static final Type<IncrementDoorLevelPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Lab.MOD_ID, "increment_door_level"));

    public static final StreamCodec<RegistryFriendlyByteBuf, IncrementDoorLevelPacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,
                    IncrementDoorLevelPacket::pos,
                    ByteBufCodecs.STRING_UTF8,
                    IncrementDoorLevelPacket::network,
                    IncrementDoorLevelPacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}