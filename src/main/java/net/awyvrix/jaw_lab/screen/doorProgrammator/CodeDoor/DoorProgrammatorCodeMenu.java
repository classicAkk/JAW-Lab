package net.classicAkk.jaw_lab.Screen.DoorProgrammator.CodeDoor;

<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Screen/DoorProgrammator/CodeDoor/DoorProgrammatorCodeMenu.java
import net.classicAkk.jaw_lab.Content.Blocks.BlockEntities.Doors.CodeDoorBE;
import net.classicAkk.jaw_lab.Content.Blocks.BlockEntities.Doors.KeyDoorBE;
import net.classicAkk.jaw_lab.Screen.LabMenuTypes;
=======
import net.awyvrix.jaw_lab.content.blocks.blockEntities.doors.CodeDoorBE;
import net.awyvrix.jaw_lab.screen.LabMenuTypes;
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/screen/doorProgrammator/CodeDoor/DoorProgrammatorCodeMenu.java
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class DoorProgrammatorCodeMenu extends AbstractContainerMenu {
    public static CodeDoorBE blockEntity;
    private static Player player;

    public DoorProgrammatorCodeMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), ContainerLevelAccess.NULL);
    }

    public DoorProgrammatorCodeMenu(int id, Inventory inv, BlockEntity entity, ContainerLevelAccess access) {
        super(LabMenuTypes.DPR_CODE.get(), id);

        blockEntity = ((CodeDoorBE) entity);
        player = inv.player;
    }

    public static Player getPlayer() {
        return player;
    }

    public static BlockEntity getBE() {
        return blockEntity;
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}