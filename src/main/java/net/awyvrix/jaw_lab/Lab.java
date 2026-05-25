package net.awyvrix.jaw_lab;

import net.awyvrix.jaw_lab.content.blocks.LabBlockEntities;
import net.awyvrix.jaw_lab.content.blocks.LabBlocks;
import net.awyvrix.jaw_lab.content.Items.LabItems;
import net.awyvrix.jaw_lab.content.sound.LabSounds;
import net.awyvrix.jaw_lab.screen.LabMenuTypes;
import net.awyvrix.jaw_lab.util.LabPackets;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Lab.MOD_ID)
public class Lab {
    public static final String MOD_ID = "lab";
    public Lab() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        LabBlockEntities.register(modEventBus);
        LabCreativeTab.register(modEventBus);
        LabBlocks.register(modEventBus);
        LabSounds.register(modEventBus);
        LabItems.register(modEventBus);
        LabMenuTypes.register(modEventBus);
        LabPackets.register();


        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation resourceLocation(String loc){
        return new ResourceLocation(MOD_ID, loc);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}
}