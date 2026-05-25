package net.awyvrix.jaw_lab.content.networking.packet.doors;

import net.awyvrix.jaw_lab.Lab;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record SetDoorNetworkPacket(BlockPos pos, String network, String currentNetwork) implements CustomPacketPayload {

    public static final Type<SetDoorNetworkPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Lab.MOD_ID, "set_door_network"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SetDoorNetworkPacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,
                    SetDoorNetworkPacket::pos,
                    ByteBufCodecs.STRING_UTF8,
                    SetDoorNetworkPacket::network,
                    ByteBufCodecs.STRING_UTF8,
                    SetDoorNetworkPacket::currentNetwork,
                    SetDoorNetworkPacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}