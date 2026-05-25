package net.classicAkk.jaw_lab.Render;

<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Render/LabEventClientBusEvent.java
import net.classicAkk.jaw_lab.Content.Blocks.LabBlocks;
import net.classicAkk.jaw_lab.Lab;
import net.classicAkk.jaw_lab.Screen.CodeDoor.CodeDoorScreen;
import net.classicAkk.jaw_lab.Screen.DoorProgrammator.CodeDoor.DoorProgrammatorCodeScreen;
import net.classicAkk.jaw_lab.Screen.DoorProgrammator.KeyDoor.DoorProgrammatorKeyScreen;
import net.classicAkk.jaw_lab.Screen.KCPCopy.KeycardProgrammatorCopyScreen;
import net.classicAkk.jaw_lab.Screen.KCPMain.KeycardProgrammatorMainScreen;
import net.classicAkk.jaw_lab.Screen.KCPNetwork.KeycardProgrammatorNetworkScreen;
import net.classicAkk.jaw_lab.Screen.LabMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
=======
import net.awyvrix.jaw_lab.Lab;
import net.awyvrix.jaw_lab.screen.codeDoor.CodeDoorScreen;
import net.awyvrix.jaw_lab.screen.doorProgrammator.CodeDoor.DoorProgrammatorCodeScreen;
import net.awyvrix.jaw_lab.screen.doorProgrammator.KeyDoor.DoorProgrammatorKeyScreen;
import net.awyvrix.jaw_lab.screen.KCPCopy.KeycardProgrammatorCopyScreen;
import net.awyvrix.jaw_lab.screen.KCPMain.KeycardProgrammatorMainScreen;
import net.awyvrix.jaw_lab.screen.KCPNetwork.KeycardProgrammatorNetworkScreen;
import net.awyvrix.jaw_lab.screen.LabMenuTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/render/LabEventClientBusEvent.java

@EventBusSubscriber(modid = Lab.MOD_ID)
public class LabEventClientBusEvent {

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(LabMenuTypes.KCP_NETWORK.get(), KeycardProgrammatorNetworkScreen::new);
        event.register(LabMenuTypes.KCP_COPY.get(), KeycardProgrammatorCopyScreen::new);
        event.register(LabMenuTypes.KCP_MAIN.get(), KeycardProgrammatorMainScreen::new);

        event.register(LabMenuTypes.CODE_DOOR.get(), CodeDoorScreen::new);

        event.register(LabMenuTypes.DPR_CODE.get(), DoorProgrammatorCodeScreen::new);
        event.register(LabMenuTypes.DPR_KEY.get(), DoorProgrammatorKeyScreen::new);
    }
}