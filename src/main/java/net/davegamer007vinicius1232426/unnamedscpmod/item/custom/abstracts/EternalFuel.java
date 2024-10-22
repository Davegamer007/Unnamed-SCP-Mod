package net.davegamer007vinicius1232426.unnamedscpmod.item.custom.abstracts;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class EternalFuel extends BaseFuelItem {

    public EternalFuel(Properties pProperties, int pBurnTime) {
        super(pProperties, pBurnTime);
    }
    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }
    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        return stack.copyWithCount(1);
    }
}
