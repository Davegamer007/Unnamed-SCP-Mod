package net.davegamer007vinicius1232426.unnamedscpmod.mixins;

import net.davegamer007vinicius1232426.unnamedscpmod.effect.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingChangeTargetEvent.class)
public abstract class LivingChangeTargetMixin {

    @Inject(method = "getNewTarget", at = @At("RETURN"), cancellable = true)
    private void getNewTarget(CallbackInfoReturnable<LivingEntity> cir){
        if(cir.getReturnValue().hasEffect(ModEffects.INCOGNITO_EFFECT.get())){
            cir.cancel();
        }
    }

}
