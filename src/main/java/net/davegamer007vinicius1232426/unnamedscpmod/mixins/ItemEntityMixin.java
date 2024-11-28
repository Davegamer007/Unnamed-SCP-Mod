package net.davegamer007vinicius1232426.unnamedscpmod.mixins;

import net.davegamer007vinicius1232426.unnamedscpmod.util.BottleToMetal;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {

    @Shadow public abstract ItemStack getItem();

    @Shadow public abstract void setItem(ItemStack pStack);

    @Shadow public abstract void tick();

    private Entity pItemEntity(){
        return (ItemEntity) (Object) this;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci){
        if (BottleToMetal.map(this.getItem().getItem()) != null && pItemEntity().isInPowderSnow){
            for (int i = this.getItem().getCount(); i > 0 ; i--){
                pItemEntity().spawnAtLocation(BottleToMetal.map(this.getItem().getItem()));
            }
            if (BottleToMetal.returnMap(this.getItem().getItem()) == null){
                pItemEntity().playSound(SoundEvents.GLASS_BREAK);
            }
            pItemEntity().discard();
        }
        if (BottleToMetal.returnMap(this.getItem().getItem()) != null && pItemEntity().isInPowderSnow){
            for (int i = this.getItem().getCount(); i > 0 ; i--){
                pItemEntity().spawnAtLocation(BottleToMetal.map(this.getItem().getItem()));
            }
            pItemEntity().discard();
        }
    }
}
