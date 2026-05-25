package net.classicAkk.jaw_lab.Screen.KCPCopy;

import com.mojang.blaze3d.systems.RenderSystem;
<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Screen/KCPCopy/KeycardProgrammatorCopyScreen.java
import net.classicAkk.jaw_lab.Lab;
import net.classicAkk.jaw_lab.Screen.Elements.GuiButton;
import net.classicAkk.jaw_lab.Screen.ProcessingPackets.OpenMainMenuPacket;
import net.classicAkk.jaw_lab.Screen.ProcessingPackets.OpenNetworkMenuPacket;
import net.classicAkk.jaw_lab.Screen.ProcessingPackets.ProcessingPacket;
import net.classicAkk.jaw_lab.Util.LabPackets;
=======
import net.awyvrix.jaw_lab.content.networking.packet.keycards.CopyCardPacket;
import net.awyvrix.jaw_lab.content.networking.packet.menu.OpenMainMenuPacket;
import net.awyvrix.jaw_lab.content.networking.packet.menu.OpenNetworkMenuPacket;
import net.awyvrix.jaw_lab.Lab;
import net.awyvrix.jaw_lab.screen.elements.GuiButton;
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/screen/KCPCopy/KeycardProgrammatorCopyScreen.java
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;

public class KeycardProgrammatorCopyScreen extends AbstractContainerScreen<KeycardProgrammatorCopyMenu> {
    BlockPos pos = menu.blockEntity.getBlockPos();

    private final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Lab.MOD_ID, "textures/gui/keycard_programmator/kcp_copy.png");

    public KeycardProgrammatorCopyScreen(KeycardProgrammatorCopyMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 4;
        this.titleLabelX = 65;

        renderElements();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    private void renderElements(){
        this.addRenderableWidget( //copy button (network)
                new GuiButton(TEXTURE, leftPos+23, topPos+66, 17, 14, 28, 172, 188, Component.empty(),
                        button -> {
                            PacketDistributor.sendToServer(new CopyCardPacket());
                        }));

        this.addRenderableWidget( //next button (mode)
                new GuiButton(TEXTURE, leftPos+159, topPos+66, 10, 14, 4, 172, 188, Component.empty(),
                        button -> {
                            PacketDistributor.sendToServer(new OpenNetworkMenuPacket(pos));
                        }));
        this.addRenderableWidget( //previous button (mode)
                new GuiButton(TEXTURE, leftPos+147, topPos+66, 10, 14, 16, 172, 188, Component.empty(),
                        button -> {
                            PacketDistributor.sendToServer(new OpenMainMenuPacket(pos));
                        }));
    }
}