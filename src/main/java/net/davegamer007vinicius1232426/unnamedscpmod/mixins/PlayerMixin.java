package net.davegamer007vinicius1232426.unnamedscpmod.mixins;

import net.davegamer007vinicius1232426.unnamedscpmod.effect.ModEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin {

    private Player pPlayer(){
        return (Player) (Object) this;
    }


    @Inject(method = "getScoreboardName", at = @At("HEAD"), cancellable = true)
    private void getScoreboardName(CallbackInfoReturnable<String> cir){
        if (pPlayer().hasEffect(ModEffects.INCOGNITO_EFFECT.get())){
            cir.setReturnValue("§kSomething");
        }
    }

    @Inject(method = "getDisplayName", at = @At("HEAD"), cancellable = true)
    private void getDisplayName(CallbackInfoReturnable<Component> cir){
        if (pPlayer().hasEffect(ModEffects.INCOGNITO_EFFECT.get())){
            cir.setReturnValue(Component.literal("§kSomething"));
        }
    }

}
