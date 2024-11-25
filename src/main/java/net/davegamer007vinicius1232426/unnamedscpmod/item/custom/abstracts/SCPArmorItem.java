package net.davegamer007vinicius1232426.unnamedscpmod.item.custom.abstracts;

import net.davegamer007vinicius1232426.unnamedscpmod.effect.ModEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SCPArmorItem extends ArmorItem {
    public SCPArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        Double entityY = entity.getY();
        float YSpeed = (entity.fallDistance + 5) / 50f;
        if (entityY <= -64) {
            entity.setDeltaMovement(0, YSpeed, 0);
        }
        if(entity.lifespan < 10){
            entity.lifespan = 200;
        }
        return super.onEntityItemUpdate(stack, entity);
    }

    @Override
    public boolean canBeHurtBy(DamageSource pDamageSource) {
        return false;
    }
}
