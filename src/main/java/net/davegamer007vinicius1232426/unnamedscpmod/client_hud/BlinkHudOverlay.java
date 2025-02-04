package net.davegamer007vinicius1232426.unnamedscpmod.client_hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class BlinkHudOverlay {
    private static ResourceLocation EYE;
    private static final ResourceLocation BLACK = new ResourceLocation(UnnamedSCPMod.MOD_ID, "textures/hud_elements/black.png");

    public static final IGuiOverlay BLINK_HUD = ((gui, poseStack, partialTick, width, height) -> {
      int x = width/2;
      int y = height;

        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        if (!BlinkHudData.getPlayerisOpen()){
            EYE = new ResourceLocation(UnnamedSCPMod.MOD_ID, "textures/hud_elements/eye_1.png");
            poseStack.blit(BLACK, 0,0,0,0,2*x, y);
        } else {
            switch (BlinkHudData.getPlayerBlinkSex()){
                case 0:
                    EYE = new ResourceLocation(UnnamedSCPMod.MOD_ID, "textures/hud_elements/eye_2.png");
                    break;
                case 1:
                    EYE = new ResourceLocation(UnnamedSCPMod.MOD_ID, "textures/hud_elements/eye_3.png");
                    break;
                case 2:
                    EYE = new ResourceLocation(UnnamedSCPMod.MOD_ID, "textures/hud_elements/eye_4.png");
                    break;
                case 3:
                    EYE =  new ResourceLocation(UnnamedSCPMod.MOD_ID, "textures/hud_elements/eye_5.png");
                    break;
                case 4:
                    EYE = new ResourceLocation(UnnamedSCPMod.MOD_ID, "textures/hud_elements/eye_6.png");
                    break;
                case 5:
                    EYE = new ResourceLocation(UnnamedSCPMod.MOD_ID, "textures/hud_elements/eye_7.png");
                    break;
                case 6, 7, 8:
                    EYE = new ResourceLocation(UnnamedSCPMod.MOD_ID, "textures/hud_elements/eye_8.png");
                    break;
            }
        }
        poseStack.blit(EYE, x*7/4, y*17/20,0, 0,32,32,32,32);
    });
}
