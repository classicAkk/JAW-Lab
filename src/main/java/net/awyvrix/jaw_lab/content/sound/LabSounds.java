package net.classicAkk.jaw_lab.Content.Sound;

<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Content/Sound/LabSounds.java
import net.classicAkk.jaw_lab.Lab;
=======
import net.awyvrix.jaw_lab.Lab;
import net.minecraft.core.registries.BuiltInRegistries;
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/content/sound/LabSounds.java
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class LabSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Lab.MOD_ID);

    public static final Supplier<SoundEvent> KEY_DOOR_TICK = registerSoundEvent("door_tick");
    public static final Supplier<SoundEvent> KEY_DOOR_OPEN = registerSoundEvent("door_open");
    public static final Supplier<SoundEvent> KEY_DOOR_CLOSE = registerSoundEvent("door_close");
    public static final Supplier<SoundEvent> KEY_DOOR_ERROR = registerSoundEvent("door_error");

    public static final Supplier<SoundEvent> ITEM_PICKUP = registerSoundEvent("item_pickup");


    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Lab.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}