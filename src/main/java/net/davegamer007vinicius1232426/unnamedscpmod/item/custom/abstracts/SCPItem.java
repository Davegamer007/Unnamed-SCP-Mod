package net.davegamer007vinicius1232426.unnamedscpmod.item.custom.abstracts;

import net.davegamer007vinicius1232426.unnamedscpmod.entity.custom.SCP999Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class SCPItem extends Item {
    public SCPItem(Properties pProperties) {
        super(pProperties.fireResistant());
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

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        if (player.getY() <= -64){
            ItemStack pStack = stack.copy();
            ItemEntity droppedItem = new ItemEntity(player.level(), player.getX(), player.getEyeY(), player.getZ(), pStack);
                droppedItem.setPickUpDelay(20);
                droppedItem.setThrower(player.getUUID());
                droppedItem.setDeltaMovement(0, player.fallDistance/10,0);
                player.level().addFreshEntity(droppedItem);
                stack.setCount(0);
        }
        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
    }
}
