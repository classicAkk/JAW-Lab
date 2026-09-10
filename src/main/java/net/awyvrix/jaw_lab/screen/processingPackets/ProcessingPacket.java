package net.awyvrix.jaw_lab.screen.processingPackets;

import net.awyvrix.jaw_lab.Lab;
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
import net.awyvrix.jaw_lab.util.LabPackets;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class ProcessingPacket {
    private final String type;
    private final BlockPos pos;

    private int containerId;
    private NetworkRole role = NetworkRole.UNKNOWN;
    private String networkName = "none";
    private String playerName = "";

    public ProcessingPacket(String type, BlockPos pos) {
        this.type = type;
        this.pos = pos;
    }

    public ProcessingPacket(String type, BlockPos pos, int containerId) {
        this.type = type;
        this.pos = pos;

        this.containerId = containerId;
    }

    public ProcessingPacket(String type, BlockPos pos, int containerId, String networkName) {
        this.type = type;
        this.pos = pos;

        this.containerId = containerId;
        this.networkName = networkName;
    }

    public ProcessingPacket(String type, BlockPos pos, String networkName) {
        this.type = type;
        this.pos = pos;

        this.networkName = networkName;
    }

    public ProcessingPacket(String type, BlockPos pos, String networkName, String playerName) {
        this.type = type;
        this.pos = pos;

        this.networkName = networkName;
        this.playerName = playerName;
    }

    public ProcessingPacket(String type, BlockPos pos, String networkName, String playerName, NetworkRole role) {
        this.type = type;
        this.pos = pos;

        this.networkName = networkName;
        this.playerName = playerName;
        this.role = role;
    }

    public ProcessingPacket(String type, BlockPos pos, int containerId, String networkName, String playerName, NetworkRole role) {
        this.type = type;
        this.pos = pos;

        this.containerId = containerId;
        this.networkName = networkName;
        this.playerName = playerName;
        this.role = role;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(type);
        buf.writeBlockPos(pos);
        buf.writeInt(containerId);

        buf.writeEnum(role);
        buf.writeNullable(networkName, FriendlyByteBuf::writeUtf);
        buf.writeNullable(playerName, FriendlyByteBuf::writeUtf);
    }

    public static ProcessingPacket decode(FriendlyByteBuf buf) {
        String type = buf.readUtf();
        BlockPos pos = buf.readBlockPos();

        int containerId = buf.readInt();
        NetworkRole role = buf.readEnum(NetworkRole.class);
        String networkName = buf.readNullable(FriendlyByteBuf::readUtf);
        String playerName = buf.readNullable(FriendlyByteBuf::readUtf);

        return new ProcessingPacket(type, pos, containerId, networkName, playerName, role);
    }

    public void handle(CustomPayloadEvent.Context ctx) {
        ctx.enqueueWork(() -> {
            ServerPlayer serverPlayer = ctx.getSender();
            
            if (serverPlayer == null) return;
            ServerLevel serverLevel = serverPlayer.serverLevel();
            BlockEntity blockEntity = serverLevel.getBlockEntity(pos);

            if (blockEntity == null) return;
            if (serverPlayer.containerMenu instanceof KeycardProgrammatorMainMenu MainMenu) {
                switch(type) {
                    case("resetKeycard") -> KeycardInteractions.resetKeycard(MainMenu, serverPlayer, 36);
                    case("uuid") -> KeycardInteractions.setUUID(MainMenu, serverPlayer, 36);
                    case("owner") -> KeycardInteractions.setUsername(MainMenu, 36, networkName); // Network name = player name
                    case("increaseLevel") -> KeycardInteractions.increaseLevel(MainMenu, 36, serverLevel, networkName, serverPlayer);
                    case("decreaseLevel") -> KeycardInteractions.decreaseLevel(MainMenu, 36, serverLevel, networkName, serverPlayer);
                    case("resetLevel") -> KeycardInteractions.resetLevel(MainMenu, 36);
                    case("addNetwork") -> KeycardInteractions.addNetwork(MainMenu, serverLevel, 36, networkName, serverPlayer);
                    case("removeNetwork") -> KeycardInteractions.removeNetwork(MainMenu, 36);
                    default -> Lab.LOGGER.error("Cannot find case to process packet caused by: {}", type);
                }
            }
            if (serverPlayer.containerMenu instanceof KeycardProgrammatorNetworkMenu) {
                switch(type) {
                    case("createNetwork") -> NetworkInteractions.createNetwork(serverLevel, networkName, serverPlayer);
                    case("deleteNetwork") -> NetworkInteractions.deleteNetwork(serverLevel, networkName, serverPlayer);
                    case("addUser") -> 
                        UUIDFetcher.getPlayerUUIDAsync(ctx.getSender().getServer(), playerName).thenAccept(uuid -> {

                        if (uuid != null) NetworkInteractions.addUser(serverLevel, uuid, playerName, networkName);
                        else Lab.LOGGER.error("Cannot get the UUID of: " + playerName + "; User wasn't created");
                    });
                    case("removeUser") -> NetworkInteractions.removeUser(serverLevel, networkName, serverPlayer, playerName);
                    case("increaseUserLevel") -> NetworkInteractions.increaseUserLevel(serverLevel, networkName, serverPlayer, playerName);
                    case("decreaseUserLevel") -> NetworkInteractions.decreaseUserLevel(serverLevel, networkName, serverPlayer, playerName);
                    case("setRole") -> NetworkInteractions.setRole(serverLevel, networkName, serverPlayer, playerName, role);
                    default -> Lab.LOGGER.error("Cannot find case to process packet caused by: {}", type);
                }
            }
            if (serverPlayer.containerMenu instanceof KeycardProgrammatorCopyMenu CopyMenu) {
                switch(type) {
                    case("copyCard") -> KeycardInteractions.copyCard(CopyMenu, 36);
                    default -> Lab.LOGGER.error("Cannot find case to process packet caused by: {}", type);
                }
            }
            if (serverPlayer.containerMenu instanceof CodeDoorMenu) {
                switch(type) {
                    case("setCode") -> DoorInteractions.setCode(blockEntity, networkName, serverPlayer); // networkName = passcode
                    case("openDoor") -> DoorInteractions.setDoor(blockEntity, serverLevel, serverPlayer);
                    case("error") -> DoorInteractions.error(blockEntity, serverLevel, serverPlayer);
                    default -> Lab.LOGGER.error("Cannot find case to process packet caused by: {}", type);
                }
            }
            if (serverPlayer.containerMenu instanceof DoorProgrammatorCodeMenu) {
                switch(type) {
                    case("switchAutoClose") -> DoorInteractions.switchAutoClose(blockEntity, serverLevel, serverPlayer);
                    case("resetDoor") -> DoorInteractions.resetDoor(blockEntity, serverLevel, serverPlayer);
                    case("setNetwork") -> DoorInteractions.setNetwork(blockEntity, serverLevel, networkName, serverPlayer);
                    default -> Lab.LOGGER.error("Cannot find case to process packet caused by: {}", type);
                }
            }
            if (serverPlayer.containerMenu instanceof DoorProgrammatorKeyMenu) {
                switch(type) {
                    case("switchAutoClose") -> DoorInteractions.switchAutoClose(blockEntity, serverLevel, serverPlayer);
                    case("resetDoor") -> DoorInteractions.resetDoor(blockEntity, serverLevel, serverPlayer);
                    case("increment") -> DoorInteractions.incrementLevel(blockEntity, serverLevel, serverPlayer);
                    case("decrement") -> DoorInteractions.decrementLevel(blockEntity, serverLevel, serverPlayer);
                    case("setNetwork") -> DoorInteractions.setNetwork(blockEntity, serverLevel, networkName, serverPlayer);
                    default -> Lab.LOGGER.error("Cannot find case to process packet caused by: {}", type);
                }
            }
            TaskProcess.tasks.add(new Task(networkName, playerName, serverPlayer));
        });

        ctx.setPacketHandled(true);
    }
}