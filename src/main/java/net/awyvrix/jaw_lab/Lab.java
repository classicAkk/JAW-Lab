package net.classicAkk.jaw_lab;

<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Lab.java
import net.classicAkk.jaw_lab.Content.Blocks.LabBlockEntities;
import net.classicAkk.jaw_lab.Content.Blocks.LabBlocks;
import net.classicAkk.jaw_lab.Content.Items.LabItems;
import net.classicAkk.jaw_lab.Content.Sound.LabSounds;
import net.classicAkk.jaw_lab.Screen.LabMenuTypes;
import net.classicAkk.jaw_lab.Util.LabPackets;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
=======
import net.awyvrix.jaw_lab.content.blocks.LabBlockEntities;
import net.awyvrix.jaw_lab.content.blocks.LabBlocks;
import net.awyvrix.jaw_lab.content.interactions.LabDataComponents;
import net.awyvrix.jaw_lab.content.items.LabItems;
import net.awyvrix.jaw_lab.content.sound.LabSounds;
import net.awyvrix.jaw_lab.screen.LabMenuTypes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/Lab.java

@Mod(Lab.MOD_ID)
public class Lab {
    public static final String MOD_ID = "lab";

    public Lab(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        LabBlockEntities.register(modEventBus);
        LabCreativeTab.register(modEventBus);
        LabBlocks.register(modEventBus);
        LabSounds.register(modEventBus);
        LabItems.register(modEventBus);
        LabMenuTypes.register(modEventBus);
        LabDataComponents.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event){}

    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {

    }
}