package net.davegamer007vinicius1232426.unnamedscpmod.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import net.davegamer007vinicius1232426.unnamedscpmod.effect.ModEffects;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin<T extends LivingEntity, A extends HumanoidModel<T>> {

    @Shadow protected abstract void renderArmorPiece(PoseStack pPoseStack, MultiBufferSource pBuffer, T pLivingEntity, EquipmentSlot pSlot, int pPackedLight, A pModel);

    @Shadow protected abstract A getArmorModel(EquipmentSlot pSlot);

    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/Entity;FFFFFF)V", at = @At("HEAD"), cancellable = true)
    private void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, Entity pEntity, float par5, float par6, float par7, float par8, float par9, float par10, CallbackInfo ci){
        LivingEntity pLivingEntity = (LivingEntity) pEntity;
        if (pLivingEntity.hasEffect(ModEffects.INCOGNITO_EFFECT.get())){
            this.renderArmorPiece(pPoseStack, pBuffer, (T) pEntity, EquipmentSlot.HEAD, pPackedLight, this.getArmorModel(EquipmentSlot.HEAD));
            ci.cancel();
        }
    }
}
