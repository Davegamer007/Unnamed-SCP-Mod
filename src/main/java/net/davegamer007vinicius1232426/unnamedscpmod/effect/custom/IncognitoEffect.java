package net.davegamer007vinicius1232426.unnamedscpmod.effect.custom;

import net.davegamer007vinicius1232426.unnamedscpmod.effect.ModEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

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
