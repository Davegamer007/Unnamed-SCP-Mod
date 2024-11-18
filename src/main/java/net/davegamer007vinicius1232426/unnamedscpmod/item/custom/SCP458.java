package net.davegamer007vinicius1232426.unnamedscpmod.item.custom;

import net.davegamer007vinicius1232426.unnamedscpmod.block.ModBlocks;
import net.davegamer007vinicius1232426.unnamedscpmod.item.custom.abstracts.SCPItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class SCP458 extends SCPItem {

    public SCP458(Properties pProperties) {
        super(pProperties);
    }
    public int MAX_SLICES = 8;
    public int MAX_EAT_TIME = 30;



    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        CompoundTag nbtData = new CompoundTag();
        if (!pStack.hasTag()){
            nbtData.putInt("unnamedscpmod:number_of_slices", MAX_SLICES);
            nbtData.putInt("unnamedscpmod:eat_time", MAX_EAT_TIME);
            nbtData.putBoolean("unnamedscpmod:should_place", false);
        }
        pStack.setTag(nbtData);
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

//
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack pPizza = pPlayer.getItemInHand(pUsedHand);
        CompoundTag nbtData = pPizza.getTag();
        int pSlices = pPizza.getTag().getInt("unnamedscpmod:number_of_slices");

        if (!pPlayer.isShiftKeyDown() && pPlayer.getFoodData().needsFood()||pPlayer.isCreative()){
            nbtData.putInt("unnamedscpmod:eat_time", MAX_EAT_TIME);
            nbtData.putBoolean("unnamedscpmod:should_place", false);
            pPlayer.startUsingItem(pUsedHand);
        }

        if (pPlayer.isShiftKeyDown() && !pPlayer.isUsingItem()){
            nbtData.putBoolean("unnamedscpmod:should_place", true);
        }

        if (!pPlayer.isShiftKeyDown() && pSlices == 0){
            nbtData.putInt("unnamedscpmod:number_of_slices", MAX_SLICES);
            nbtData.putInt("unnamedscpmod:eat_time", MAX_EAT_TIME);
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }








    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        if (!(pLivingEntity instanceof Player pPlayer)){return;}
        InteractionHand pUsedHand = pPlayer.getUsedItemHand();
        ItemStack pPizza = pPlayer.getItemInHand(pUsedHand);
        CompoundTag nbtData = pPizza.getTag();


        int pEatTime = pPizza.getTag().getInt("unnamedscpmod:eat_time");
        int pSlices = pPizza.getTag().getInt("unnamedscpmod:number_of_slices");

        if (pPlayer.isShiftKeyDown()){
            return;
        }

        if (pSlices >= 1 && pEatTime >= 1){
            nbtData.putInt("unnamedscpmod:eat_time", (int)(pEatTime-1));
        }

        if (pSlices >= 1 && pEatTime == 0 && pPlayer.getFoodData().needsFood()){
            pPlayer.getFoodData().eat(4,4);
            nbtData.putInt("unnamedscpmod:number_of_slices", (int)(pSlices-1));
            nbtData.putInt("unnamedscpmod:eat_time", MAX_EAT_TIME);
            pPlayer.stopUsingItem();
        }

        if (pSlices <= 0 && !pPlayer.isCreative()){
            pPlayer.getCooldowns().addCooldown(this, 1200);
        }

        if (pSlices <= 0 && pPlayer.isCreative()){
            pPlayer.getCooldowns().addCooldown(this, 20);
        }

        super.onUseTick(pLevel, pLivingEntity, pStack, pRemainingUseDuration);
    }








    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Player pPlayer = pContext.getPlayer();
        Level pLevel = pContext.getLevel();
        BlockPos pBlockPos = pContext.getClickedPos();
        BlockPos pFacePos = pBlockPos.relative(pContext.getClickedFace());


        ItemStack pStack = pContext.getItemInHand();
        boolean pPlace = pStack.getTag().getBoolean("unnamedscpmod:should_place");

        if (!pPlace){
            return super.useOn(pContext);
        }

        if (!pPlayer.isCreative()){
            pLevel.setBlock(pFacePos, ModBlocks.FLESH_BLOCK.get().defaultBlockState(), 1);
            pStack.setCount(pStack.getCount()-1);
        }

        if (pPlayer.isCreative()){
            pLevel.setBlock(pFacePos, ModBlocks.FLESH_BLOCK.get().defaultBlockState(), 1);
        }
        return super.useOn(pContext);
    }






    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        boolean pPlace = pStack.getTag().getBoolean("unnamedscpmod:should_place");
        if (pPlace){
            return UseAnim.BLOCK;
        }
        return UseAnim.EAT;
    }





    @Override
    public int getUseDuration(ItemStack pStack) {
        boolean pPlace = pStack.getTag().getBoolean("unnamedscpmod:should_place");
        if (!pPlace){
            return 20;
        }
         return 0;
    }






    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        int pSLICES = pStack.getTag().getInt("unnamedscpmod:number_of_slices");

        if (pSLICES > 1) {
            pTooltipComponents.add(Component.literal("There are " + pSLICES + " slices left in the box"));
        }

        if (pSLICES == 1) {
            pTooltipComponents.add(Component.literal("There is " + pSLICES + " slice left in the box"));
        }

        if (pSLICES == 0) {
            pTooltipComponents.add(Component.literal("Regenerating..."));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
