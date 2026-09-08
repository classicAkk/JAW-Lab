package net.awyvrix.jaw_lab.screen.processingPackets;

import net.awyvrix.jaw_lab.content.network.NetworkRole;
import net.awyvrix.jaw_lab.screen.KCPNetwork.KeycardProgrammatorNetworkScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class NetworkUserInfoPacket {
    private final String network;
    private final String user;
    private final NetworkRole role;
    private final int accessLevel;

    public NetworkUserInfoPacket(String network, String user, NetworkRole role, int accessLevel) {
        this.network = network;
        this.user = user;
        this.role = role;
        this.accessLevel = accessLevel;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeNullable(network, FriendlyByteBuf::writeUtf);
        buf.writeNullable(user, FriendlyByteBuf::writeUtf);
        buf.writeEnum(role);
        buf.writeInt(accessLevel);
    }

    public static NetworkUserInfoPacket decode(FriendlyByteBuf buf) {
        return new NetworkUserInfoPacket(
                buf.readNullable(FriendlyByteBuf::readUtf),
                buf.readNullable(FriendlyByteBuf::readUtf),
                buf.readEnum(NetworkRole.class),
                buf.readInt()
                );
    }

    public static void handle(NetworkUserInfoPacket msg, CustomPayloadEvent.Context ctx) {
        ctx.enqueueWork(() -> {
            Minecraft minecraft = Minecraft.getInstance();

            if (!(minecraft.screen instanceof KeycardProgrammatorNetworkScreen screen)) return;
            screen.updateUserInfo(
                    msg.network,
                    msg.user,
                    msg.role,
                    msg.accessLevel
            );
        });
        ctx.setPacketHandled(true);
    }
}