package net.davegamer007vinicius1232426.unnamedscpmod.effect.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class IncognitoEffect extends MobEffect {
    public IncognitoEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {

        boolean isMaxHealth = pLivingEntity.getHealth() >= pLivingEntity.getMaxHealth();
        pLivingEntity.setInvisible(isMaxHealth);
        pLivingEntity.setCustomName(Component.literal("§kSomething"));
        pLivingEntity.setCustomNameVisible(false);

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }
}
