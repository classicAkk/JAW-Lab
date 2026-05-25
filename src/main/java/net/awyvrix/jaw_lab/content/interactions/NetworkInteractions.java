package net.classicAkk.jaw_lab.Content.Interactions;

<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Content/Interactions/NetworkInteractions.java
import net.classicAkk.jaw_lab.Content.Network.NetworkRole;
import net.classicAkk.jaw_lab.Content.Network.NetworkSecurity;
import net.classicAkk.jaw_lab.Content.Network.NetworkUser;
import net.classicAkk.jaw_lab.Content.Network.NetworkWorldData;
=======
import net.awyvrix.jaw_lab.content.network.NetworkRole;
import net.awyvrix.jaw_lab.content.network.NetworkSecurity;
import net.awyvrix.jaw_lab.content.network.NetworkUser;
import net.awyvrix.jaw_lab.content.network.NetworkWorldData;
import net.awyvrix.jaw_lab.screen.KCPNetwork.KeycardProgrammatorNetworkMenu;
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/content/interactions/NetworkInteractions.java
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class NetworkInteractions {
    public static void createNetwork(ServerLevel level, String networkName, Player player, KeycardProgrammatorNetworkMenu menu) {
        NetworkWorldData data = NetworkWorldData.get((ServerLevel) level);
        data.createNetwork(networkName, level, player);
        menu.setRole(NetworkRole.FOUNDER);
        menu.broadcastChanges();
    }
    public static void deleteNetwork(ServerLevel level, String networkName, Player player, KeycardProgrammatorNetworkMenu menu) {
        NetworkWorldData data = NetworkWorldData.get((ServerLevel) level);
        if (data.isValidNetwork(networkName)) {
            data.deleteNetworkSafe(networkName, player);
            menu.setRole(null);
            menu.setAccessLevel(0);
            menu.broadcastChanges();
        }
    }

    public static void addUser(ServerLevel level, UUID uuid, String nickname, String networkName, KeycardProgrammatorNetworkMenu menu) {
        NetworkWorldData data = NetworkWorldData.get((ServerLevel) level);
        if (data.isValidNetwork(networkName)) {
            data.addUser(networkName, new NetworkUser(uuid, nickname, NetworkRole.MEMBER, 0));
            menu.setAccessLevel(0);
            menu.setRole(NetworkRole.MEMBER);
            menu.broadcastChanges();
        }
    }
    public static void removeUser(ServerLevel level, String networkName, Player player, String targetName, KeycardProgrammatorNetworkMenu menu) {
        NetworkWorldData data = NetworkWorldData.get((ServerLevel) level);
        if (!NetworkSecurity.canRemoveUsers(data.getNetwork(networkName).getUser(player.getUUID()))) return;
        if (data.isValidNetwork(networkName)) {
            data.removeUserSafe(networkName, player, data.getUserUUID(networkName, targetName));
            menu.setAccessLevel(-1);
            menu.setRole(null);
            menu.broadcastChanges();
        }
    }

    public static void increaseUserLevel(ServerLevel level, String networkName, Player player, String targetName, KeycardProgrammatorNetworkMenu menu) {
        NetworkWorldData data = NetworkWorldData.get((ServerLevel) level);
        if (data.isValidNetwork(networkName)) {
            data.incrementLevel(networkName, player, data.getUserUUID(networkName, targetName), menu);
        }
    }
    public static void decreaseUserLevel(ServerLevel level, String networkName, Player player, String targetName, KeycardProgrammatorNetworkMenu menu) {
        NetworkWorldData data = NetworkWorldData.get((ServerLevel) level);
        if (data.isValidNetwork(networkName)) {
            data.decrementLevel(networkName, player, data.getUserUUID(networkName, targetName), menu);
        }
    }

    public static void setRole(ServerLevel level, String networkName, Player player, String targetName, NetworkRole role, KeycardProgrammatorNetworkMenu menu) {
        NetworkWorldData data = NetworkWorldData.get((ServerLevel) level);
        UUID uuid = data.getUserUUID(networkName, targetName);
        UUID pUuid = player.getUUID();
        if (role == null) return;
        if (data.getUserRole(networkName, uuid) == NetworkRole.FOUNDER) return;
        if (role == NetworkRole.FOUNDER && data.getUserRole(networkName, pUuid) == NetworkRole.FOUNDER) {
            data.setUserRole(networkName, pUuid, NetworkRole.ADMIN);
        }
        data.setUserRoleSafe(networkName, player, uuid, role, menu);
    }
    public static boolean canSetRole(ServerLevel level, String networkName, Player player, String targetName, NetworkRole role) {
        NetworkWorldData data = NetworkWorldData.get((ServerLevel) level);
        UUID uuid = data.getUserUUID(networkName, targetName);
        UUID pUuid = player.getUUID();
        if (role == null) return false;
        if (!NetworkSecurity.canChangeRole(data.getUser(networkName, targetName))) return false;
        if (data.getUserRole(networkName, uuid) == NetworkRole.FOUNDER) return false;
        if (role == NetworkRole.FOUNDER && data.getUserRole(networkName, pUuid) == NetworkRole.FOUNDER) {
            return true;
        }
        return true;
    }

    public static int getUserAccessLevel(ServerLevel level, String networkName, Player player) {
        NetworkWorldData data = NetworkWorldData.get((ServerLevel) level);
        if (data.isValidNetwork(networkName)) {
            return data.getUserLevel(networkName, player.getUUID());
        }
        return -1;
    }
    public static int getUserAccessLevel(ServerLevel level, String networkName, String targetName) {
        NetworkWorldData data = NetworkWorldData.get((ServerLevel) level);
        if (data.isValidNetwork(networkName)) {
            return data.getUserLevel(networkName, data.getUserUUID(networkName, targetName));
        }
        return -1;
    }
    public static NetworkRole getUserRole(ServerLevel level, String networkName, Player player) {
        NetworkWorldData data = NetworkWorldData.get((ServerLevel) level);
        if (data.isValidNetwork(networkName)) {
            return data.getUserRole(networkName, player.getUUID());
        }
        return null;
    }
    public static NetworkRole getUserRole(ServerLevel level, String networkName, String targetName) {
        NetworkWorldData data = NetworkWorldData.get((ServerLevel) level);
        if (data.isValidNetwork(networkName)) {
            return data.getUserRole(networkName, data.getUserUUID(networkName, targetName));
        }
        return null;
    }

    public static boolean canIncreaseAccessLevel(ServerLevel level, String networkName, String targetName, Player player) {
        NetworkWorldData data = NetworkWorldData.get((ServerLevel) level);
        return data.canIncrementLevel(networkName, player, data.getUserUUID(networkName, targetName));
    }
    public static boolean canDecreaseAccessLevel(ServerLevel level, String networkName, String targetName, Player player) {
        NetworkWorldData data = NetworkWorldData.get((ServerLevel) level);
        return data.canDecrementLevel(networkName, player, data.getUserUUID(networkName, targetName));
    }
}