<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Content/Items/custom/item/items/DuctTape.java
package net.classicAkk.jaw_lab.Content.Items.custom.item.items;
=======
package net.awyvrix.jaw_lab.content.items.custom.item.items;
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/content/Items/custom/item/items/DuctTape.java

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class DuctTape extends Item {
    public DuctTape(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.lab.duct_tape.tooltip"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}