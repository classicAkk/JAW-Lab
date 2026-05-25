package net.awyvrix.jaw_lab.screen.processingPackets;

import net.awyvrix.jaw_lab.content.interactions.DoorInteractions;
import net.awyvrix.jaw_lab.content.interactions.KeycardInteractions;
import net.awyvrix.jaw_lab.content.interactions.NetworkInteractions;
import net.awyvrix.jaw_lab.content.network.NetworkRole;
import net.awyvrix.jaw_lab.content.network.UUIDFetcher;
import net.awyvrix.jaw_lab.screen.codeDoor.CodeDoorMenu;
import net.awyvrix.jaw_lab.screen.doorProgrammator.CodeDoor.DoorProgrammatorCodeMenu;
import net.awyvrix.jaw_lab.screen.doorProgrammator.KeyDoor.DoorProgrammatorKeyMenu;
import net.awyvrix.jaw_lab.screen.KCPCopy.KeycardProgrammatorCopyMenu;
import net.awyvrix.jaw_lab.screen.KCPMain.KeycardProgrammatorMainMenu;
import net.awyvrix.jaw_lab.screen.KCPNetwork.KeycardProgrammatorNetworkMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ProcessingPacket {
    private int containerId;
    private NetworkRole role;
    private String type;
    private String parameter;
    private String parameter2;
    private String networkName;
    private Level level;
    private ServerLevel serverLevel;
    private MinecraftServer server;
    private Player player;
    private BlockEntity blockEntity;

    public ProcessingPacket(int containerId, String type) {
        this.containerId = containerId;
        this.type = type;
    }
    public ProcessingPacket(int containerId, String type, String parameter) {
        this.containerId = containerId;
        this.type = type;
        this.parameter = parameter;
    }
    public ProcessingPacket(int containerId, String type, Player player, String parameter) {
        this.containerId = containerId;
        this.type = type;
        this.player = player;
        this.parameter = parameter;
    }
    public ProcessingPacket(Level level, Player player, String networkName, String type) {
        this.type = type;
        this.level = level;
        this.player = player;
        this.networkName = networkName;
        if (!level.isClientSide()) {
            this.serverLevel = (ServerLevel) level;
        }
    }
    public ProcessingPacket(Level level, Player player, String networkName, String type, String parameter) {
        this.type = type;
        this.level = level;
        this.player = player;
        this.networkName = networkName;
        this.parameter = parameter;
        if (!level.isClientSide()) {
            this.serverLevel = (ServerLevel) level;
        }
    }
    public ProcessingPacket(Level level, Player player, String networkName, String type, String parameter, NetworkRole role) {
        this.type = type;
        this.level = level;
        this.player = player;
        this.networkName = networkName;
        this.parameter = parameter;
        this.role = role;
        if (!level.isClientSide()) {
            this.serverLevel = (ServerLevel) level;
        }
    }
    public ProcessingPacket(Level level, BlockEntity blockEntity, String type, String parameter, Player player) {
        this.level = level;
        this.blockEntity = blockEntity;
        this.type = type;
        this.parameter = parameter;
        this.player = player;
    }
    public ProcessingPacket(Level level, BlockEntity blockEntity, String type, String parameter, String parameter2, Player player) {
        this.level = level;
        this.blockEntity = blockEntity;
        this.type = type;
        this.parameter = parameter;
        this.parameter2 = parameter2;
        this.player = player;
    }

    public ProcessingPacket(int containerId, String type, String parameter, String parameter2, String networkName) {
        this.containerId = containerId;
        this.type = type;
        this.parameter = parameter;
        this.parameter2 = parameter2;
        this.networkName = networkName;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(containerId);
        buf.writeUtf(type);

        buf.writeNullable(parameter, FriendlyByteBuf::writeUtf);
        buf.writeNullable(parameter2, FriendlyByteBuf::writeUtf);
        buf.writeNullable(networkName, FriendlyByteBuf::writeUtf);
    }

    public static ProcessingPacket decode(FriendlyByteBuf buf) {
        int containerId = buf.readInt();
        String type = buf.readUtf();
        String parameter = buf.readNullable(FriendlyByteBuf::readUtf);
        String parameter2 = buf.readNullable(FriendlyByteBuf::readUtf);
        String networkName = buf.readNullable(FriendlyByteBuf::readUtf);

        return new ProcessingPacket(containerId, type, parameter, parameter2, networkName);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer serverPlayer = ctx.get().getSender();
            if (serverPlayer == null) {return;}

            if (serverPlayer.containerMenu instanceof KeycardProgrammatorMainMenu MainMenu) {
                switch(type) {
                    case("resetKeycard"):{KeycardInteractions.resetKeycard(MainMenu, serverPlayer, 36); break;}
                    case("uuid"): {KeycardInteractions.setUUID(MainMenu, serverPlayer, 36); break;}
                    case("owner"):{KeycardInteractions.setUsername(MainMenu, 36, parameter); break;}
                    case("increaseLevel"): {KeycardInteractions.increaseLevel(MainMenu, 36, serverLevel, networkName, player); break;}
                    case("decreaseLevel"): {KeycardInteractions.decreaseLevel(MainMenu, 36, serverLevel, networkName, player); break;}
                    case("resetLevel"): {KeycardInteractions.resetLevel(MainMenu, 36); break;}
                    case("addNetwork"): {KeycardInteractions.addNetwork(MainMenu, level, 36, parameter, player); break;}
                    case("removeNetwork"): {KeycardInteractions.removeNetwork(MainMenu, 36); break;}
                    default: {System.out.print("Jaw Lab: cannot find case in net/classic_akk/jaw_lab/screen/processingPackets\n"); break;}
                }
            }
            if (serverPlayer.containerMenu instanceof KeycardProgrammatorNetworkMenu NetworkMenu) {
                switch(type) {
                    case("createNetwork"): {NetworkInteractions.createNetwork(serverLevel, networkName, player); break;}
                    case("deleteNetwork"): {NetworkInteractions.deleteNetwork(serverLevel, networkName, player); break;}
                    case("addUser"): {UUIDFetcher.getPlayerUUIDAsync(ctx.get().getSender().getServer(), parameter).thenAccept(uuid -> {
                        if (uuid != null) {NetworkInteractions.addUser(serverLevel, uuid, parameter, networkName);}
                        else {System.out.println("Cannot get the UUID of "+parameter);}});break;}
                    case("removeUser"): {NetworkInteractions.removeUser(serverLevel, networkName, player, parameter); break;}
                    case("increaseUserLevel"): {NetworkInteractions.increaseUserLevel(serverLevel, networkName, player, parameter); break;}
                    case("decreaseUserLevel"): {NetworkInteractions.decreaseUserLevel(serverLevel, networkName, player, parameter); break;}
                    case("setRole"): {NetworkInteractions.setRole(serverLevel, networkName, player, parameter, role); break;}
                    default: {System.out.print("Jaw Lab: cannot find case in net/classic_akk/jaw_lab/screen/processingPackets\n"); break;}
                }
            }
            if (serverPlayer.containerMenu instanceof KeycardProgrammatorCopyMenu CopyMenu) {
                switch(type) {
                    case("copyCard"): {KeycardInteractions.copyCard(CopyMenu, 36); break;}
                    default: {System.out.print("Jaw Lab: cannot find case in net/classic_akk/jaw_lab/screen/processingPackets\n"); break;}
                }
            }
            if (serverPlayer.containerMenu instanceof CodeDoorMenu CodeDoorMenu) {
                switch(type) {
                    case("openDoor"): {DoorInteractions.setDoor(blockEntity, level, player);break;}
                    case("setCode"): {DoorInteractions.setCode(blockEntity, level, parameter, player);break;}
                    case("error"): {DoorInteractions.error(blockEntity, level, player);break;}
                    default: {System.out.print("Jaw Lab: cannot find case in net/classic_akk/jaw_lab/screen/processingPackets\n"); break;}
                }
            }
            if (serverPlayer.containerMenu instanceof DoorProgrammatorCodeMenu codeMenu) {
                switch(type) {
                    case("switchAutoClose"): {DoorInteractions.switchAutoClose(blockEntity, level);break;}
                    case("resetDoor"): {DoorInteractions.resetDoor(blockEntity, level, parameter, player);break;}
                    case("setNetwork"): {DoorInteractions.setNetwork(blockEntity, level, parameter, parameter2, player);break;}
                    default: {System.out.print("Jaw Lab: cannot find case in net/classic_akk/jaw_lab/screen/processingPackets\n"); break;}
                }
            }
            if (serverPlayer.containerMenu instanceof DoorProgrammatorKeyMenu keyMenu) {
                switch(type) {
                    case("switchAutoClose"): {DoorInteractions.switchAutoClose(blockEntity, level);break;}
                    case("resetDoor"): {DoorInteractions.resetDoor(blockEntity, level, parameter, player);break;}
                    case("increment"): {DoorInteractions.incrementLevel(blockEntity, level, parameter, player);break;}
                    case("decrement"): {DoorInteractions.decrementLevel(blockEntity, level, parameter, player);break;}
                    case("setNetwork"): {DoorInteractions.setNetwork(blockEntity, level, parameter, parameter2, player);break;}
                    default: {System.out.print("Jaw Lab: cannot find case in net/classic_akk/jaw_lab/screen/processingPackets\n"); break;}
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}