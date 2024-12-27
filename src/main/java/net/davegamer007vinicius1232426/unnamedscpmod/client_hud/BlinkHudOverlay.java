package net.davegamer007vinicius1232426.unnamedscpmod.client_hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class BlinkHudOverlay {
    private static final ResourceLocation EYE_8 = new ResourceLocation(UnnamedSCPMod.MOD_ID, "textures/hud_elements/eye_8.png");
    private static final ResourceLocation EYE_7 = new ResourceLocation(UnnamedSCPMod.MOD_ID, "textures/hud_elements/eye_7.png");
    private static final ResourceLocation EYE_6 = new ResourceLocation(UnnamedSCPMod.MOD_ID, "textures/hud_elements/eye_6.png");
    private static final ResourceLocation EYE_5 = new ResourceLocation(UnnamedSCPMod.MOD_ID, "textures/hud_elements/eye_5.png");
    private static final ResourceLocation EYE_4 = new ResourceLocation(UnnamedSCPMod.MOD_ID, "textures/hud_elements/eye_4.png");
    private static final ResourceLocation EYE_3 = new ResourceLocation(UnnamedSCPMod.MOD_ID, "textures/hud_elements/eye_3.png");
    private static final ResourceLocation EYE_2 = new ResourceLocation(UnnamedSCPMod.MOD_ID, "textures/hud_elements/eye_2.png");
    private static final ResourceLocation EYE_1 = new ResourceLocation(UnnamedSCPMod.MOD_ID, "textures/hud_elements/eye_1.png");


    public static final IGuiOverlay BLINK_HUD = ((gui, poseStack, partialTick, width, height) -> {
      int x = width/2;
      int y = height;

        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);


        if (!BlinkHudData.getPlayerisOpen()){
            RenderSystem.setShaderTexture(0, EYE_1);
            poseStack.blit(EYE_1, x*7/4, y*17/20,0, 0,32,32,32,32);
        } else


        if (BlinkHudData.getPlayerBlinkSex() == 8|| BlinkHudData.getPlayerBlinkSex() == 7){
            RenderSystem.setShaderTexture(0, EYE_8);
            poseStack.blit(EYE_8, x*7/4, y*17/20,0, 0,32,32,32,32);
        } else
        if (BlinkHudData.getPlayerBlinkSex() == 6){
            RenderSystem.setShaderTexture(0, EYE_7);
            poseStack.blit(EYE_7, x*7/4, y*17/20,0, 0,32,32,32,32);
        } else
        if (BlinkHudData.getPlayerBlinkSex() == 5){
            RenderSystem.setShaderTexture(0, EYE_6);
            poseStack.blit(EYE_6, x*7/4, y*17/20,0, 0,32,32,32,32);
        } else
        if (BlinkHudData.getPlayerBlinkSex() == 4){
            RenderSystem.setShaderTexture(0, EYE_5);
            poseStack.blit(EYE_5, x*7/4, y*17/20,0, 0,32,32,32,32);
        } else
        if (BlinkHudData.getPlayerBlinkSex() == 3){
            RenderSystem.setShaderTexture(0, EYE_4);
            poseStack.blit(EYE_4, x*7/4, y*17/20,0, 0,32,32,32,32);
        } else
        if (BlinkHudData.getPlayerBlinkSex() == 2){
            RenderSystem.setShaderTexture(0, EYE_3);
            poseStack.blit(EYE_3, x*7/4, y*17/20,0, 0,32,32,32,32);
        } else
        if (BlinkHudData.getPlayerBlinkSex() == 1){
            RenderSystem.setShaderTexture(0, EYE_2);
            poseStack.blit(EYE_2, x*7/4, y*17/20,0, 0,32,32,32,32);
        } else
        if (BlinkHudData.getPlayerBlinkSex() == 0){
            RenderSystem.setShaderTexture(0, EYE_1);
            poseStack.blit(EYE_1, x*7/4, y*17/20,0, 0,32,32,32,32);
        }



    });
}
