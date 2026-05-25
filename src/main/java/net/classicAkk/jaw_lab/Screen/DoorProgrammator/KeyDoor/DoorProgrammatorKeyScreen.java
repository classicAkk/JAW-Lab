package net.classicAkk.jaw_lab.Screen.DoorProgrammator.KeyDoor;

import com.mojang.blaze3d.systems.RenderSystem;
<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Screen/DoorProgrammator/KeyDoor/DoorProgrammatorKeyScreen.java
import net.classicAkk.jaw_lab.Content.Blocks.BlockEntities.Doors.CodeDoorBE;
import net.classicAkk.jaw_lab.Content.Interactions.DoorInteractions;
import net.classicAkk.jaw_lab.Content.Interactions.KeycardInteractions;
import net.classicAkk.jaw_lab.Content.Network.Network;
import net.classicAkk.jaw_lab.Lab;
import net.classicAkk.jaw_lab.Screen.DoorProgrammator.CodeDoor.DoorProgrammatorCodeMenu;
import net.classicAkk.jaw_lab.Screen.Elements.GuiButton;
import net.classicAkk.jaw_lab.Screen.ProcessingPackets.ProcessingPacket;
import net.classicAkk.jaw_lab.Util.LabPackets;
=======
import net.awyvrix.jaw_lab.content.interactions.DoorInteractions;
import net.awyvrix.jaw_lab.content.interactions.KeycardInteractions;
import net.awyvrix.jaw_lab.content.networking.packet.doors.*;
import net.awyvrix.jaw_lab.Lab;
import net.awyvrix.jaw_lab.screen.elements.GuiButton;
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/screen/doorProgrammator/KeyDoor/DoorProgrammatorKeyScreen.java
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

public class DoorProgrammatorKeyScreen extends AbstractContainerScreen<DoorProgrammatorKeyMenu> {
    public int offsetX = 33;
    public int offsetY = 38;
    private String net;
    private EditBox field;
    private final Player player = DoorProgrammatorKeyMenu.getPlayer();
    private final BlockEntity blockEntity = DoorProgrammatorKeyMenu.getBE();
    private final Level level = DoorProgrammatorKeyMenu.getLevel();

    private final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Lab.MOD_ID, "textures/gui/door_programmator.png");

    public DoorProgrammatorKeyScreen(DoorProgrammatorKeyMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 43;
        this.titleLabelX = 65;
        net = DoorInteractions.getNetwork(blockEntity);

        renderElements();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - 110) / 2;
        int y = (height - 90) / 2;

        renderTextElements(guiGraphics);
        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth-40, imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    private void renderTextElements(GuiGraphics guiGraphics){
        String network = DoorInteractions.getNetwork(blockEntity);
        int cLevel = DoorInteractions.getLevel(blockEntity);


        if (network != null) guiGraphics.drawString(this.font, network, leftPos+offsetX+12, topPos+offsetY+56, 0xFFA500);
        guiGraphics.drawString(this.font, String.valueOf(cLevel), leftPos+offsetX+28, topPos+offsetY+32, KeycardInteractions.getColorNumbers(cLevel));
    }

    private void renderElements(){
        this.addRenderableWidget( // Reset button (network)
                new GuiButton(TEXTURE, leftPos+offsetX+89, topPos+offsetY+13, 14, 14, 50, 208, 224, Component.empty(),
                        button -> {
                            if (net == null) return;
                            PacketDistributor.sendToServer(new ResetDoorPacket(blockEntity.getBlockPos(), net));
                        }));
        this.addRenderableWidget( // Auto-close button (mode)
                new GuiButton(TEXTURE, leftPos+offsetX+89, topPos+offsetY+29, 14, 14, 2, 208, 224, Component.empty(),
                        button -> {
                            if (net == null) return;
                            PacketDistributor.sendToServer(new SwitchAutoClosePacket(blockEntity.getBlockPos()));
                        }));

        this.addRenderableWidget( // Increase Level (mode)
                new GuiButton(TEXTURE, leftPos+offsetX+8, topPos+offsetY+13, 14, 14, 18, 208, 224, Component.empty(),
                        button -> {
                            if (net == null) return;
                            PacketDistributor.sendToServer(new IncrementDoorLevelPacket(blockEntity.getBlockPos(), net));
                        }));
        this.addRenderableWidget( // Decrease Level (mode)
                new GuiButton(TEXTURE, leftPos+offsetX+8, topPos+offsetY+29, 14, 14, 34, 208, 224, Component.empty(),
                        button -> {
                            if (net == null) return;
                            PacketDistributor.sendToServer(new DecrementDoorLevelPacket(blockEntity.getBlockPos(), net));
                        }));

<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Screen/DoorProgrammator/KeyDoor/DoorProgrammatorKeyScreen.java
        this.addRenderableWidget( //Set Network (mode)
=======
        this.addRenderableWidget( // Set network (mode)
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/screen/doorProgrammator/KeyDoor/DoorProgrammatorKeyScreen.java
                new GuiButton(TEXTURE, leftPos+offsetX+86, topPos+offsetY+70, 11, 11, 66, 208, 224, Component.empty(),
                        button -> {
                            PacketDistributor.sendToServer(
                                    new SetDoorNetworkPacket(blockEntity.getBlockPos(), field.getValue(), net == null ? "" : net)
                            );
                        }));

        field = new EditBox(this.font, leftPos+offsetX+9, topPos+offsetY+70, 74, 11, Component.literal("Field"));
        field.setMaxLength(18);field.setBordered(true);field.setVisible(true);field.setTextColor(0xFFFFFF);this.addRenderableWidget(field);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (field.isFocused()) {
            if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
                return super.keyPressed(keyCode, scanCode, modifiers);
            }
            if (keyCode == GLFW.GLFW_KEY_E) return true;
            return field.keyPressed(keyCode, scanCode, modifiers) || field.canConsumeInput();
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}