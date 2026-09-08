package net.awyvrix.jaw_lab.screen.doorProgrammator.CodeDoor;

import com.mojang.blaze3d.systems.RenderSystem;
import net.awyvrix.jaw_lab.content.blocks.blockEntities.Doors.CodeDoorBE;
import net.awyvrix.jaw_lab.content.blocks.blockEntities.Util.DoorBlock;
import net.awyvrix.jaw_lab.content.interactions.DoorInteractions;
import net.awyvrix.jaw_lab.Lab;
import net.awyvrix.jaw_lab.screen.elements.GuiButton;
import net.awyvrix.jaw_lab.screen.processingPackets.ProcessingPacket;
import net.awyvrix.jaw_lab.util.LabPackets;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.lwjgl.glfw.GLFW;

public class DoorProgrammatorCodeScreen extends AbstractContainerScreen<DoorProgrammatorCodeMenu> {
    public int offsetX = 55;
    public int offsetY = 55;
    private EditBox field;

    private final BlockEntity blockEntity = DoorProgrammatorCodeMenu.getBE();
    private final BlockPos pos = blockEntity.getBlockPos();

    private final ResourceLocation TEXTURE =
            new ResourceLocation(Lab.MOD_ID, "textures/gui/door_programmator.png");

    public DoorProgrammatorCodeScreen(DoorProgrammatorCodeMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 4;
        this.titleLabelX = 6500;

        renderElements();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width  - 66) / 2;
        int y = (height - 56) / 2;

        if (!(blockEntity instanceof DoorBlock doorBlock)) return;
        guiGraphics.drawString(this.font, doorBlock.network(), leftPos+offsetX+8, topPos+offsetY+29, 0xFFA500);
        guiGraphics.blit(TEXTURE, x, y, 148, 0, imageWidth-84, imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    private void renderElements() {
        this.addRenderableWidget( //Auto-close button (mode)
                new GuiButton(TEXTURE, leftPos+offsetX+18, topPos+offsetY+6, 14, 14, 2, 208, 224, Component.empty(),
                        button -> {
                            LabPackets.sendToServer(new ProcessingPacket("switchAutoClose", pos));
                        }));

        this.addRenderableWidget( //Reset button (network)
                new GuiButton(TEXTURE, leftPos+offsetX+34, topPos+offsetY+6, 14, 14, 50, 208, 224, Component.empty(),
                        button -> {
                            LabPackets.sendToServer(new ProcessingPacket("resetDoor", pos));
                        }));
        this.addRenderableWidget( //Set network (mode)
                new GuiButton(TEXTURE, leftPos+offsetX+28, topPos+offsetY+56, 11, 11, 66, 208, 224, Component.empty(),
                        button -> {
                            if (field.getValue().isEmpty()) return;
                            LabPackets.sendToServer(new ProcessingPacket("setNetwork", pos, field.getValue()));
                        }));

        field = new EditBox(this.font, leftPos+offsetX+6, topPos+offsetY+42, 54, 11, Component.literal("Field"));
        field.setMaxLength(18);field.setBordered(true);field.setVisible(true);field.setTextColor(0xFFFFFF);this.addRenderableWidget(field);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (field.isFocused()) {
            if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
                return super.keyPressed(keyCode, scanCode, modifiers);
            }
            if (keyCode == GLFW.GLFW_KEY_E) {
                return true;
            }
            return field.keyPressed(keyCode, scanCode, modifiers) || field.canConsumeInput();
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}