package net.classicAkk.jaw_lab.Screen;

<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Screen/LabMenuTypes.java
import net.classicAkk.jaw_lab.Lab;
import net.classicAkk.jaw_lab.Screen.CodeDoor.CodeDoorMenu;
import net.classicAkk.jaw_lab.Screen.DoorProgrammator.CodeDoor.DoorProgrammatorCodeMenu;
import net.classicAkk.jaw_lab.Screen.DoorProgrammator.KeyDoor.DoorProgrammatorKeyMenu;
import net.classicAkk.jaw_lab.Screen.KCPCopy.KeycardProgrammatorCopyMenu;
import net.classicAkk.jaw_lab.Screen.KCPMain.KeycardProgrammatorMainMenu;
import net.classicAkk.jaw_lab.Screen.KCPNetwork.KeycardProgrammatorNetworkMenu;
=======
import net.awyvrix.jaw_lab.Lab;
import net.awyvrix.jaw_lab.screen.codeDoor.CodeDoorMenu;
import net.awyvrix.jaw_lab.screen.doorProgrammator.CodeDoor.DoorProgrammatorCodeMenu;
import net.awyvrix.jaw_lab.screen.doorProgrammator.KeyDoor.DoorProgrammatorKeyMenu;
import net.awyvrix.jaw_lab.screen.KCPCopy.KeycardProgrammatorCopyMenu;
import net.awyvrix.jaw_lab.screen.KCPMain.KeycardProgrammatorMainMenu;
import net.awyvrix.jaw_lab.screen.KCPNetwork.KeycardProgrammatorNetworkMenu;
import net.minecraft.core.registries.Registries;
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/screen/LabMenuTypes.java
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LabMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, Lab.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<KeycardProgrammatorNetworkMenu>> KCP_NETWORK =
            registerMenuType("kcp_network", KeycardProgrammatorNetworkMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<KeycardProgrammatorCopyMenu>> KCP_COPY =
            registerMenuType("kcp_copy", KeycardProgrammatorCopyMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<KeycardProgrammatorMainMenu>> KCP_MAIN =
            registerMenuType("kcp_main", KeycardProgrammatorMainMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<CodeDoorMenu>> CODE_DOOR =
            registerMenuType("code_door", CodeDoorMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<DoorProgrammatorCodeMenu>> DPR_CODE =
            registerMenuType("dpr_code", DoorProgrammatorCodeMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<DoorProgrammatorKeyMenu>> DPR_KEY =
            registerMenuType("dpr_key", DoorProgrammatorKeyMenu::new);


    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}