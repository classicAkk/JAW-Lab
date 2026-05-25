package net.awyvrix.jaw_lab.content.networking.packet.network;

import net.awyvrix.jaw_lab.content.network.NetworkRole;
import net.awyvrix.jaw_lab.Lab;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record SetUserRolePacket(String network, String username, NetworkRole role) implements CustomPacketPayload {

    public static final Type<SetUserRolePacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Lab.MOD_ID, "set_user_role"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SetUserRolePacket> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8,
                    SetUserRolePacket::network,
                    ByteBufCodecs.STRING_UTF8,
                    SetUserRolePacket::username,
                    ByteBufCodecs.STRING_UTF8.map(NetworkRole::valueOf, NetworkRole::name),
                    SetUserRolePacket::role,
                    SetUserRolePacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}