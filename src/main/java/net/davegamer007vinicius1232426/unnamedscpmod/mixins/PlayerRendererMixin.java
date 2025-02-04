package net.davegamer007vinicius1232426.unnamedscpmod.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.effect.ModEffects;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {

    private PlayerRenderer pRenderer(){
        return (PlayerRenderer) (Object) this;
    }

    @Inject(method = "getTextureLocation(Lnet/minecraft/client/player/AbstractClientPlayer;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"), cancellable = true)
    private void getResourceLocation(AbstractClientPlayer pEntity, CallbackInfoReturnable<ResourceLocation> cir){
        if (pEntity.hasEffect(ModEffects.INCOGNITO_EFFECT.get())){
            cir.setReturnValue(new ResourceLocation(UnnamedSCPMod.MOD_ID,"textures/other/incognito.png"));
        }
    }

    @Inject(method = "renderNameTag(Lnet/minecraft/client/player/AbstractClientPlayer;Lnet/minecraft/network/chat/Component;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("HEAD"), cancellable = true)
    private void renderNameTag(AbstractClientPlayer pEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, CallbackInfo ci){
        if (pEntity.hasEffect(ModEffects.INCOGNITO_EFFECT.get())){
            ci.cancel();
        }
    }
}
