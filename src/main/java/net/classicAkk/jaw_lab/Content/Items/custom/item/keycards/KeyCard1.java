<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Content/Items/custom/item/keycards/KeyCard1.java
package net.classicAkk.jaw_lab.Content.Items.custom.item.keycards;
=======
package net.awyvrix.jaw_lab.content.items.custom.item.keycards;
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/content/Items/custom/item/keycards/KeyCard1.java

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class KeyCard1 extends Item {
    public KeyCard1(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.lab.key_card1.tooltip"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}