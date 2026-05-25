package net.awyvrix.jaw_lab.content.networking.handler;

import net.awyvrix.jaw_lab.content.blocks.blockEntities.doors.CodeDoorBE;
import net.awyvrix.jaw_lab.content.interactions.DoorInteractions;
import net.awyvrix.jaw_lab.content.networking.packet.doors.CodeDoorSubmitPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public final class CodeDoorPacketHandler {
    private CodeDoorPacketHandler() {}

    public static void handleSubmit(final CodeDoorSubmitPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            BlockEntity be = player.level().getBlockEntity(packet.pos());

            if (!(be instanceof CodeDoorBE codeDoor)) return;
            String input = packet.code();
            String real = codeDoor.getPasscode();

            if (real == null || real.isEmpty()) {
                codeDoor.setPasscode(input);
                DoorInteractions.setCode(codeDoor, player.level(), input, player);
                player.closeContainer();
                return;
            }

            if (real.equals(input)) {
                DoorInteractions.setDoor(codeDoor, player.level(), player);
            } else {
                DoorInteractions.error(codeDoor, player.level(), player);
            }

            player.closeContainer();
        });
    }
}