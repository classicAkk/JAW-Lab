<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Content/Items/LabItems.java
package net.classicAkk.jaw_lab.Content.Items;

import net.classicAkk.jaw_lab.Content.Items.custom.item.items.Crowbar;
import net.classicAkk.jaw_lab.Content.Items.custom.item.items.DuctTape;
import net.classicAkk.jaw_lab.Content.Items.custom.item.items.Fuse;
import net.classicAkk.jaw_lab.Content.Items.custom.item.keycards.*;
import net.classicAkk.jaw_lab.Lab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
=======
package net.awyvrix.jaw_lab.content.items;

import net.awyvrix.jaw_lab.content.items.custom.item.items.Crowbar;
import net.awyvrix.jaw_lab.content.items.custom.item.items.DuctTape;
import net.awyvrix.jaw_lab.content.items.custom.item.items.Fuse;
import net.awyvrix.jaw_lab.content.items.custom.item.keycards.*;
import net.awyvrix.jaw_lab.Lab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/content/Items/LabItems.java

public class LabItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Lab.MOD_ID);

    //Keycard stuff
<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Content/Items/LabItems.java
    public static final RegistryObject<Item> DOOR_PROGRAMMATOR = ITEMS.register("door_programmator",
=======
    public static final DeferredItem<Item> DOOR_PROGRAMMATOR = ITEMS.register("door_programmator",
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/content/Items/LabItems.java
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> KEYCARD1 = ITEMS.register("keycard1",
            () -> new KeyCard1(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> KEYCARD2 = ITEMS.register("keycard2",
            () -> new KeyCard2(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> KEYCARD3 = ITEMS.register("keycard3",
            () -> new KeyCard3(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> KEYCARD4 = ITEMS.register("keycard4",
            () -> new KeyCard4(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> KEYCARD5 = ITEMS.register("keycard5",
            () -> new KeyCard5(new Item.Properties().stacksTo(1)));

    //Pickable items
<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Content/Items/LabItems.java
    public static final RegistryObject<Item> CROWBAR = ITEMS.register("crowbar",
=======
    public static final DeferredItem<Item> CROWBAR = ITEMS.register("crowbar",
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/content/Items/LabItems.java
            () -> new Crowbar(new Item.Properties()));
    public static final DeferredItem<Item> DUCT_TAPE = ITEMS.register("duct_tape",
            () -> new DuctTape(new Item.Properties()));
    public static final DeferredItem<Item> FUSE = ITEMS.register("fuse",
            () -> new Fuse(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}