package net.classicAkk.jaw_lab.Content.Blocks;

<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Content/Blocks/LabBlocks.java
import net.classicAkk.jaw_lab.Content.Blocks.Blocks.Doors.CodeDoor;
import net.classicAkk.jaw_lab.Content.Blocks.Blocks.Decorations.GlassRailings;
import net.classicAkk.jaw_lab.Content.Blocks.Blocks.Decorations.GlassRailingsCorner;
import net.classicAkk.jaw_lab.Content.Blocks.Blocks.Decorations.Grating;
import net.classicAkk.jaw_lab.Content.Blocks.Blocks.Doors.DoorBottom;
import net.classicAkk.jaw_lab.Content.Blocks.Blocks.Doors.KeyDoor;
import net.classicAkk.jaw_lab.Content.Blocks.Blocks.ElevatorButtons.InsideElevatorButton;
import net.classicAkk.jaw_lab.Content.Blocks.Blocks.ElevatorButtons.OutsideElevatorButton;
import net.classicAkk.jaw_lab.Content.Blocks.Blocks.Barrier.BarrierGate;
import net.classicAkk.jaw_lab.Content.Blocks.Blocks.Barrier.BarrierGateConnector;
import net.classicAkk.jaw_lab.Content.Blocks.Blocks.Barrier.BarrierGateOff;
import net.classicAkk.jaw_lab.Content.Blocks.Blocks.KeycardProgrammator;
import net.classicAkk.jaw_lab.Content.Blocks.Blocks.LEDLamp;
import net.classicAkk.jaw_lab.Lab;
import net.classicAkk.jaw_lab.Content.Items.LabItems;
=======
import net.awyvrix.jaw_lab.content.blocks.blocks.doors.CodeDoor;
import net.awyvrix.jaw_lab.content.blocks.blocks.decorations.GlassRailings;
import net.awyvrix.jaw_lab.content.blocks.blocks.decorations.GlassRailingsCorner;
import net.awyvrix.jaw_lab.content.blocks.blocks.decorations.Grating;
import net.awyvrix.jaw_lab.content.blocks.blocks.doors.DoorBottom;
import net.awyvrix.jaw_lab.content.blocks.blocks.doors.KeyDoor;
import net.awyvrix.jaw_lab.content.blocks.blocks.elevatorButtons.InsideElevatorButton;
import net.awyvrix.jaw_lab.content.blocks.blocks.elevatorButtons.OutsideElevatorButton;
import net.awyvrix.jaw_lab.content.blocks.blocks.barrier.BarrierGate;
import net.awyvrix.jaw_lab.content.blocks.blocks.barrier.BarrierGateConnector;
import net.awyvrix.jaw_lab.content.blocks.blocks.barrier.BarrierGateOff;
import net.awyvrix.jaw_lab.content.blocks.blocks.KeycardProgrammator;
import net.awyvrix.jaw_lab.content.blocks.blocks.LEDLamp;
import net.awyvrix.jaw_lab.Lab;
import net.awyvrix.jaw_lab.content.items.LabItems;
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/content/blocks/LabBlocks.java
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class LabBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Lab.MOD_ID);

    //InteractableBlocks
    public static final DeferredBlock<Block> KEYCARD_PROGRAMMATOR = registerBlock("keycard_programmator",
            () -> new KeycardProgrammator(BlockBehaviour.Properties.of().sound(SoundType.METAL)));

    //ArmoredConcrete
    public static final DeferredBlock<Block> ARMORED_CONCRETE = registerBlock("armored_concrete",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ARMORED_CONCRETE_PAINTED = registerBlock("armored_concrete_painted",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ARMORED_CONCRETE_G = registerBlock("armored_concrete_g",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ARMORED_CONCRETE_G_PAINTED = registerBlock("armored_concrete_g_painted",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ARMORED_CONCRETE_B = registerBlock("armored_concrete_b",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ARMORED_CONCRETE_B_PAINTED = registerBlock("armored_concrete_b_painted",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE)));

    //ZonesBlocks
    public static final DeferredBlock<Block> YELLOW_UP = registerBlock("yellow_up",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> YELLOW_DOWN = registerBlock("yellow_down",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE)));


    public static final DeferredBlock<Block> CYAN_UP = registerBlock("cyan_up",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> CYAN_DOWN = registerBlock("cyan_down",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE)));


    public static final DeferredBlock<Block> GREEN_UP = registerBlock("green_up",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GREEN_DOWN = registerBlock("green_down",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> RED_BLACK_UP = registerBlock("red_black_down",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RED_BLACK_DOWN = registerBlock("red_black_up",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> RED_UP = registerBlock("red_up",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RED_DOWN = registerBlock("red_down",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ORANGE_UP = registerBlock("orange_up",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORANGE_DOWN = registerBlock("orange_down",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE)));


    //KeyDoors
    public static final DeferredBlock<Block> DOOR_BOTTOM = registerBlock("door_bottom",
            () -> new DoorBottom(BlockBehaviour.Properties.of().sound(SoundType.METAL)
                    .noOcclusion()
                    .dynamicShape()));

    public static final DeferredBlock<Block> KEY_DOOR = registerBlock("key_door",
            () -> new KeyDoor(BlockBehaviour.Properties.of().sound(SoundType.METAL)
                    .noOcclusion()
                    .dynamicShape()));

    public static final DeferredBlock<Block> CODE_DOOR = registerBlock("code_door",
            () -> new CodeDoor(BlockBehaviour.Properties.of().sound(SoundType.METAL)
                    .noOcclusion()
                    .dynamicShape()));

<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Content/Blocks/LabBlocks.java
    //Decorations
    public static final RegistryObject<Block> GLASS_RAILINGS = registerBlock("glass_railings",
            () -> new GlassRailings(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.GLASS)
=======
    //decorations
    public static final DeferredBlock<Block> GLASS_RAILINGS = registerBlock("glass_railings",
            () -> new GlassRailings(BlockBehaviour.Properties.of().sound(SoundType.GLASS)
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/content/blocks/LabBlocks.java
                    .noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> GLASS_RAILINGS_CORNER = registerBlock("glass_railings_corner",
            () -> new GlassRailingsCorner(BlockBehaviour.Properties.of().sound(SoundType.GLASS)
                    .noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> GRATING = registerBlock("grating",
            () -> new Grating(BlockBehaviour.Properties.of().sound(SoundType.METAL)
                    .noOcclusion()
                    .dynamicShape()));

    //Buttons
    public static final DeferredBlock<Block> ELEVATOR_INSIDE = registerBlock("elevator_inside",
            () -> new InsideElevatorButton(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK), 20));
    public static final DeferredBlock<Block> ELEVATOR_OUTSIDE = registerBlock("elevator_outside",
            () -> new OutsideElevatorButton(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK), 20));

    //Useful
    public static final DeferredBlock<Block> LED_LAMP = registerBlock("led_lamp",
            () -> new LEDLamp(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK)));

    //barriers
    public static final DeferredBlock<Block> BARRIER_GATE = registerBlock("barrier_gate",
            () -> new BarrierGate(BlockBehaviour.Properties.of().sound(SoundType.GLASS)
                    .noOcclusion()
                    .dynamicShape()
                    .strength(-1)));
    public static final DeferredBlock<Block> BARRIER_GATE_OFF = registerBlock("barrier_gate_off",
            () -> new BarrierGateOff(BlockBehaviour.Properties.of().sound(SoundType.GLASS)
                    .noOcclusion()
                    .dynamicShape()
                    .strength(100)));
    public static final DeferredBlock<Block> BARRIER_GATE_CONNECTOR = registerBlock("barrier_gate_connector",
            () -> new BarrierGateConnector(BlockBehaviour.Properties.of().sound(SoundType.GLASS)
                    .noOcclusion()
                    .dynamicShape()
                    .strength(-1)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        LabItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}