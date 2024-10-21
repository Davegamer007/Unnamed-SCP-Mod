package net.davegamer007vinicius1232426.unnamedscpmod.effect.custom;

import net.davegamer007vinicius1232426.unnamedscpmod.effect.ModEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

public class EternalFlame extends MobEffect {
    public EternalFlame(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.hurt(pLivingEntity.damageSources().onFire(), 1.0F);
        if (!pLivingEntity.fireImmune()) {
            pLivingEntity.setRemainingFireTicks(pLivingEntity.getRemainingFireTicks() + 1);
            if (pLivingEntity.getRemainingFireTicks() == 0) {
                pLivingEntity.setSecondsOnFire(8);
            }
        }
        if (pLivingEntity instanceof Mob pMob && pMob.getLastHurtMob() != null && pMob.getMainHandItem().isEmpty()){
            (pMob.getLastHurtMob()).addEffect(new MobEffectInstance(ModEffects.ETERNAL_FLAME.get(), -1));
        }
        if (pLivingEntity instanceof Player pPlayer && pPlayer.getLastHurtMob() != null && pPlayer.getMainHandItem().isEmpty()){
            (pPlayer.getLastHurtMob()).addEffect(new MobEffectInstance(ModEffects.ETERNAL_FLAME.get(), -1));
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}