package net.davegamer007vinicius1232426.unnamedscpmod.effect.custom;

import net.davegamer007vinicius1232426.unnamedscpmod.effect.ModEffects;
import net.davegamer007vinicius1232426.unnamedscpmod.particle.ModParticles;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidType;

import java.util.List;
import java.util.Random;

public class EternalFlame extends MobEffect {
    public EternalFlame(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.hurt(pLivingEntity.damageSources().onFire(), 1.0F);
        for(int i = 0; i < 10; i++){
            if (i == 0)
                generateParticles(pLivingEntity);
        }



        if (!pLivingEntity.fireImmune() && !pLivingEntity.isInWaterOrRain() && !pLivingEntity.isInPowderSnow) {
            pLivingEntity.setSecondsOnFire(8);
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

    @Override
    public List<ItemStack> getCurativeItems() {
        return List.of();
    }

    public static void generateParticles(LivingEntity pLivingEntity){
        RandomSource pRandom = RandomSource.create();

        Double dx = (double)pLivingEntity.getX() + pRandom.nextDouble();
        Double dy = (double)pLivingEntity.getY() + pRandom.nextDouble() * 0.5;
        Double dz = (double)pLivingEntity.getZ() + pRandom.nextDouble();



        pLivingEntity.level().addParticle(ModParticles.ETERNAL_EMBERS.get(),
                dx, dy, dz,
                0,0, 0);
    }
}

