package net.davegamer007vinicius1232426.unnamedscpmod.item.custom.easterEggs;

import net.davegamer007vinicius1232426.unnamedscpmod.item.custom.abstracts.SCPItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ThalassoRex extends SCPItem {
    public ThalassoRex(Properties pProperties) {
        super(pProperties);
    }

    private String MODE_TAG = "use_mode";

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack pStack = pPlayer.getItemInHand(pUsedHand);
        CompoundTag nbtData =  pStack.getOrCreateTag();
        int mode = nbtData.getInt(MODE_TAG);

        if (pPlayer.isShiftKeyDown() && mode == 2){
            nbtData.putInt(MODE_TAG, 0);
        }
        if (pPlayer.isShiftKeyDown() && mode < 2){
            nbtData.putInt(MODE_TAG, mode + 1);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
