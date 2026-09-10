package net.awyvrix.jaw_lab.screen.processingPackets;

import net.awyvrix.jaw_lab.content.interactions.NetworkInteractions;
import net.awyvrix.jaw_lab.util.LabPackets;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayDeque;
import java.util.Queue;

import static net.awyvrix.jaw_lab.Lab.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class TaskProcess {
    public static final Queue<Task> tasks = new ArrayDeque<>();

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {

            if (tasks.peek() == null) return;
            Task task = tasks.poll();
            ServerPlayer serverPlayer = task.serverPlayer();
            String networkName = task.networkName();
            String playerName = task.playerName();
            ServerLevel serverLevel = task.serverPlayer().serverLevel();

            if (playerName.isEmpty()) {
                String playerName2 = task.serverPlayer().getName().getString();
                LabPackets.sendToClient(new NetworkUserInfoPacket(
                        networkName, playerName2,
                        NetworkInteractions.getUserRole(serverLevel, networkName, playerName2),
                        NetworkInteractions.getUserAccessLevel(serverLevel, networkName, playerName2)
                ), serverPlayer);
            } else {
                LabPackets.sendToClient(new NetworkUserInfoPacket(
                        networkName, playerName,
                        NetworkInteractions.getUserRole(serverLevel, networkName, playerName),
                        NetworkInteractions.getUserAccessLevel(serverLevel, networkName, playerName)
                ), serverPlayer);
            }
        }
    }
}
