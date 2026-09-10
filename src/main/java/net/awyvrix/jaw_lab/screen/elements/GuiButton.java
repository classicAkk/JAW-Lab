package net.awyvrix.jaw_lab.screen.elements;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class GuiButton extends Button {
    private final ResourceLocation TEXTURE;
    private final int u;
    private final int v;
    private final int hoverV;
    private final String hover;
    private final boolean isProtected;

    public GuiButton(ResourceLocation TEXTURE, int x, int y, int width, int height, int u, int v, int hoverV, Component message, String hover, boolean isProtected, OnPress onPress) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION);
        this.TEXTURE = TEXTURE;
        this.u = u;
        this.v = v;
        this.hoverV = hoverV;
        this.hover = hover;
        this.isProtected = isProtected;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        RenderSystem.setShaderTexture(0, TEXTURE);
        int textureV = this.isHovered ? hoverV : v;

        if (this.isHovered && !hover.isEmpty()) {
            //Component tooltip = Screen.hasShiftDown() ? Component.translatable(hover) : Component.translatable("gui.button.tooltip.expand");
            Component tooltip = Component.translatable(hover);
            guiGraphics.renderTooltip(Minecraft.getInstance().font, tooltip, mouseX, mouseY);
        }

        guiGraphics.blit(TEXTURE, this.getX(), this.getY(), u, textureV, this.width, this.height);
    }

    @Override
    public boolean isActive() {
        if (!isProtected) return true;
        return Screen.hasAltDown();
    }
}