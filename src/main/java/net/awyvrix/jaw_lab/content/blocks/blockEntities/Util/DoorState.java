package net.awyvrix.jaw_lab.content.blocks.blockEntities.Util;

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