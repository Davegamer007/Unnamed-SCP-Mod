package net.davegamer007vinicius1232426.unnamedscpmod.item.custom.abstracts;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class SCPFuelItem extends EternalFuel {
    public SCPFuelItem(Properties pProperties, int pBurnTime) {
        super(pProperties.fireResistant(), pBurnTime);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
         Double entityY = entity.getY();
         float YSpeed = (entity.fallDistance + 5)/50f;
         entity.isInvulnerable();
         entity.setUnlimitedLifetime();
         if (entityY <= -64){
             entity.setDeltaMovement(0,YSpeed,0);
         }
        return super.onEntityItemUpdate(stack, entity);
    }
}
