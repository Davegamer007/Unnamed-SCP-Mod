package net.davegamer007vinicius1232426.unnamedscpmod.effect.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class IncognitoEffect extends MobEffect {
    public IncognitoEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {

        if (pLivingEntity.getHealth() >= pLivingEntity.getMaxHealth()){
            pLivingEntity.setInvisible(true);
            pLivingEntity.setCustomName(Component.literal("Something"));
            pLivingEntity.setCustomNameVisible(false);
        }

        if (pLivingEntity instanceof Player pPlayer){
            pPlayer.setCustomName(Component.literal("Something"));
            pPlayer.setCustomNameVisible(false);
            pPlayer.setInvisible(true);
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }
}
