package net.awyvrix.jaw_lab.screen.processingPackets;

import net.minecraft.server.level.ServerPlayer;

public record Task(String networkName, String playerName, ServerPlayer serverPlayer) {
}
