package net.davegamer007vinicius1232426.unnamedscpmod.effect.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SerotoninEffect extends MobEffect {
    public SerotoninEffect(MobEffectCategory pCategory, int pColor) {super(pCategory, pColor);}

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        for(double i=0; i<=1; i = i+0.1){
            addAttributeModifier(Attributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", i, AttributeModifier.Operation.ADDITION);
            if (pLivingEntity.getHealth() < pLivingEntity.getMaxHealth()){
              i = 0;
            }
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }
}
