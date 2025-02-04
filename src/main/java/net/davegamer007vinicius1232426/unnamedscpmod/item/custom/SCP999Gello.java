package net.davegamer007vinicius1232426.unnamedscpmod.item.custom;

import net.davegamer007vinicius1232426.unnamedscpmod.effect.ModEffects;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class SCP999Gello extends Item {
    public SCP999Gello(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.TOOT_HORN;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack pStack = pPlayer.getItemInHand(pUsedHand);
        if (!pPlayer.isCreative()){
            pStack.setCount(pStack.getCount()-1);
        }
        pPlayer.addEffect(new MobEffectInstance(ModEffects.SEROTONIN_EFFECT.get(), 1200));
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        if (!pPlayer.isCreative()){
            pStack.setCount(pStack.getCount()-1);
        }
        pInteractionTarget.addEffect(new MobEffectInstance(ModEffects.SEROTONIN_EFFECT.get(), 1200));
        return super.interactLivingEntity(pStack, pPlayer, pInteractionTarget, pUsedHand);
    }
}
