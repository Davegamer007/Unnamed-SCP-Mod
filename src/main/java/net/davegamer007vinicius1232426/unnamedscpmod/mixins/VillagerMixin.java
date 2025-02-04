package net.davegamer007vinicius1232426.unnamedscpmod.mixins;

import net.davegamer007vinicius1232426.unnamedscpmod.effect.ModEffects;
import net.davegamer007vinicius1232426.unnamedscpmod.item.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;

@Mixin(Villager.class)
public class VillagerMixin {

    @Inject(method = "updateSpecialPrices", at = @At(value = "TAIL"))
    private void updateSpecialPrices(Player pPlayer, CallbackInfo ci){

        Villager pVillager = (Villager) (Object) this;

        if (pVillager.hasEffect(ModEffects.SEROTONIN_EFFECT.get())){
            Iterator var5 = pVillager.getOffers().iterator();
            while(var5.hasNext()) {
                MerchantOffer merchantoffer1 = (MerchantOffer)var5.next();
                merchantoffer1.resetUses();
                merchantoffer1.addToSpecialPriceDiff(-4);
            }
        }
    }
}
