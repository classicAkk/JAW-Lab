<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Content/Blocks/BlockEntities/Util/DoorState.java
package net.classicAkk.jaw_lab.Content.Blocks.BlockEntities.Util;
=======
package net.awyvrix.jaw_lab.content.blocks.blockEntities.util;
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/content/blocks/blockEntities/Util/DoorState.java

import net.minecraft.util.StringRepresentable;

public enum DoorState implements StringRepresentable {
    OPENED,
    CLOSED,
    ERROR;

    @Override
    public String getSerializedName() {
        return name().toLowerCase();
    }
}