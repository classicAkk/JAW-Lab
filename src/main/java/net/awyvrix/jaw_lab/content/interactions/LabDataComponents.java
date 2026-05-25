package net.awyvrix.jaw_lab.content.interactions;

import net.awyvrix.jaw_lab.Lab;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LabDataComponents {
    public static final DeferredRegister<DataComponentType<?>> COMPONENTS =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Lab.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<KeycardData>> KEYCARD =
            COMPONENTS.register("keycard", () ->
                    DataComponentType.<KeycardData>builder()
                            .persistent(KeycardData.CODEC)
                            .networkSynchronized(KeycardData.STREAM_CODEC)
                            .build()
            );

    public static void register(IEventBus bus) {
        COMPONENTS.register(bus);
    }
}