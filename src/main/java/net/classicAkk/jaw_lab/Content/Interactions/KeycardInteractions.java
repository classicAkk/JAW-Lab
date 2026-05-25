package net.classicAkk.jaw_lab.Content.Interactions;

<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Content/Interactions/KeycardInteractions.java
import net.classicAkk.jaw_lab.Content.Network.NetworkSecurity;
import net.classicAkk.jaw_lab.Content.Network.NetworkWorldData;
import net.classicAkk.jaw_lab.Screen.KCPCopy.KeycardProgrammatorCopyMenu;
import net.classicAkk.jaw_lab.Screen.KCPMain.KeycardProgrammatorMainMenu;
import net.minecraft.nbt.CompoundTag;
=======
import net.awyvrix.jaw_lab.content.network.Network;
import net.awyvrix.jaw_lab.content.network.NetworkSecurity;
import net.awyvrix.jaw_lab.content.network.NetworkUser;
import net.awyvrix.jaw_lab.content.network.NetworkWorldData;
import net.awyvrix.jaw_lab.screen.KCPCopy.KeycardProgrammatorCopyMenu;
import net.awyvrix.jaw_lab.screen.KCPMain.KeycardProgrammatorMainMenu;
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/content/interactions/KeycardInteractions.java
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.function.UnaryOperator;

public class KeycardInteractions {
    public static void increaseLevel(KeycardProgrammatorMainMenu menu, int id, ServerLevel serverLevel, String networkName, Player player) {
        ItemStack stack = menu.getSlot(id).getItem();

        if (networkName == null) return;
        if (!stack.isEmpty()) {
            Network network = NetworkWorldData.get(serverLevel).getNetwork(networkName);

            if (network == null) return;
            NetworkUser user = network.getUser(player.getUUID());

            if (!NetworkSecurity.canChangeCardLevel(user)) return;
            set(stack, d -> d.withChangedLevel(1));
            menu.getSlot(id).setChanged();
            menu.broadcastChanges();
        }
    }
    public static void decreaseLevel(KeycardProgrammatorMainMenu menu, int id, ServerLevel serverLevel, String networkName, Player player) {
        ItemStack stack = menu.getSlot(id).getItem();

        if (networkName == null) return;
        if (!stack.isEmpty()) {
            Network network = NetworkWorldData.get(serverLevel).getNetwork(networkName);

            if (network == null) return;
            NetworkUser user = network.getUser(player.getUUID());

            if (!NetworkSecurity.canChangeCardLevel(user)) return;
            set(stack, d -> d.withChangedLevel(-1));
            menu.getSlot(id).setChanged();
            menu.broadcastChanges();
        }
    }

    public static void resetLevel(KeycardProgrammatorMainMenu menu, int id) {
        ItemStack stack = menu.getSlot(id).getItem();

        if (!stack.isEmpty()) {
            set(stack, d -> d.withLevel(0));
            menu.getSlot(id).setChanged();
            menu.broadcastChanges();
        }
    }

    public static void copyCard(KeycardProgrammatorCopyMenu menu, int id) {
        ItemStack source = menu.getSlot(id).getItem();
        ItemStack target = menu.getSlot(id + 1).getItem();

        if (!source.isEmpty() && !target.isEmpty()) {
            KeycardData s = KeycardData.get(source);
            set(target, new KeycardData(
                    s.level(),
                    s.owner(),
                    s.uuid(),
                    s.network()
            ));
            menu.getSlot(id + 1).setChanged();
            menu.broadcastChanges();
        }
    }

    public static void setUUID(KeycardProgrammatorMainMenu menu, Player player, int id) {
        ItemStack stack = menu.getSlot(id).getItem();
        if (!stack.isEmpty()) {
            set(stack, d -> d.withUUID(player.getStringUUID()));

            menu.getSlot(id).setChanged();
            menu.broadcastChanges();
        }
    }
    public static void setUsername(KeycardProgrammatorMainMenu menu, int id, String owner) {
        ItemStack stack = menu.getSlot(id).getItem();

        if (!stack.isEmpty()) {
            set(stack, d -> d.withOwner(owner));
            menu.getSlot(id).setChanged();
            menu.broadcastChanges();
        }
    }

    public static void resetUsername(KeycardProgrammatorMainMenu menu, int id) {
        ItemStack stack = menu.getSlot(id).getItem();

        if (!stack.isEmpty()) {
            set(stack, d -> d.withOwner("none"));
            menu.getSlot(id).setChanged();
            menu.broadcastChanges();
        }
    }
    public static void resetUUID(KeycardProgrammatorMainMenu menu, int id) {
        ItemStack stack = menu.getSlot(id).getItem();
        if (!stack.isEmpty()) {
            set(stack, d -> d.withUUID("none"));

            menu.getSlot(id).setChanged();
            menu.broadcastChanges();
        }
    }

    public static void unbindUser(KeycardProgrammatorMainMenu menu, int id){
        resetUsername(menu, id);
        resetUUID(menu, id);
    }
    public static void resetKeycard(KeycardProgrammatorMainMenu menu, ServerPlayer player, int id){
        unbindUser(menu, id);
        resetLevel(menu, id);
        removeNetwork(menu, id);
    }

    public static void addNetwork(KeycardProgrammatorMainMenu menu, Level level, int id, String networkName, Player player) {
        ItemStack stack = menu.getSlot(id).getItem();

        if (stack.isEmpty()) return;
        if (networkName == null) return;
        ServerLevel server = (ServerLevel) level;
        NetworkWorldData data = NetworkWorldData.get(server);
        Network network = NetworkWorldData.get(server).getNetwork(networkName);

        if (network == null) return;
        NetworkUser user = network.getUser(player.getUUID());

        if (!NetworkSecurity.canChangeCardLevel(user)) return;
        if (data.isValidNetwork(networkName)) {
            set(stack, d -> d.withNetwork(networkName));
            menu.getSlot(id).setChanged();
            menu.broadcastChanges();
        }
    }
    public static void removeNetwork(KeycardProgrammatorMainMenu menu, int id) {
        ItemStack stack = menu.getSlot(id).getItem();

        if (!stack.isEmpty()) {
            set(stack, d -> d.withNetwork("none"));

            menu.getSlot(id).setChanged();
            menu.broadcastChanges();
        }
    }

    public static int getColor(String string) {
        if (string == null) return 0xFF2400;
        if (string.equals("none") || string.equals("false")) {
            return 0xFF2400;
        }
        if (string.equals("true")) {
            return 0x008000;
        }
        return 0xFFA500;
    }

    public static int getColorNumbers(int number) {
        if (number != 0) {
            return 0x42AAFF;
        }
        return 0xFF2400;
    }

    public static String getCardOwner(KeycardProgrammatorMainMenu menu, int id) {
        ItemStack stack = menu.getSlot(id).getItem();
        return stack.isEmpty() ? "none" : KeycardData.get(stack).owner();
    }
    public static String getCardUUID(KeycardProgrammatorMainMenu menu, int id) {
        ItemStack stack = menu.getSlot(id).getItem();
        return stack.isEmpty() ? "false" : KeycardData.get(stack).uuid();
    }
    public static String getFormattedCardUUID(KeycardProgrammatorMainMenu menu, int id) {
        ItemStack stack = menu.getSlot(id).getItem();
        if (!stack.isEmpty()) {
            String uuid = KeycardData.get(stack).uuid();
            if (!uuid.equals("none")) return "true";
        }
        return "false";
    }
    public static String getCardNetwork(KeycardProgrammatorMainMenu menu, int id) {
        ItemStack stack = menu.getSlot(id).getItem();
        return stack.isEmpty() ? "none" : KeycardData.get(stack).network();
    }
    public static int getCardLevel(KeycardProgrammatorMainMenu menu, int id) {
        ItemStack stack = menu.getSlot(id).getItem();
        return stack.isEmpty() ? 0 : KeycardData.get(stack).level();
    }

    private static void set(ItemStack stack, KeycardData data) {
        stack.set(LabDataComponents.KEYCARD.get(), data);
    }

    public static void set(ItemStack stack, UnaryOperator<KeycardData> operator) {
        KeycardData data = KeycardData.get(stack);

        stack.set(LabDataComponents.KEYCARD.get(), operator.apply(data));
    }
}