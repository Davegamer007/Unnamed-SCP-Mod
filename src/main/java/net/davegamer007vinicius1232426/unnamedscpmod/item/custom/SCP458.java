package net.davegamer007vinicius1232426.unnamedscpmod.item.custom;

import net.davegamer007vinicius1232426.unnamedscpmod.block.ModBlocks;
import net.davegamer007vinicius1232426.unnamedscpmod.item.custom.abstracts.SCPItem;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SCP458 extends SCPItem {

    public SCP458(Properties pProperties) {
        super(pProperties);
    }
    private final String EAT_TAG = "eat_time";
    private final String PLACE_TAG = "should_place";
    private final String SLICE_TAG = "number_of_slices";
    private final int MAX_EAT_TIME = 30;
    private final int MAX_SLICES = 8;


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack pPizza = pPlayer.getItemInHand(pUsedHand);
        CompoundTag nbtData = pPizza.getOrCreateTag();
        int pSlices = nbtData.getInt(SLICE_TAG);

        if (!pPlayer.isShiftKeyDown() && pPlayer.getFoodData().needsFood()||pPlayer.isCreative() && pSlices !=0){
            nbtData.putInt(EAT_TAG, MAX_EAT_TIME);
            pPlayer.startUsingItem(pUsedHand);
        }

        if (!pPlayer.isShiftKeyDown() && pSlices == 0){
            nbtData.putInt(SLICE_TAG, MAX_SLICES);
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        if (!(pLivingEntity instanceof Player pPlayer)){return;}
        InteractionHand pUsedHand = pPlayer.getUsedItemHand();
        ItemStack pPizza = pPlayer.getItemInHand(pUsedHand);
        CompoundTag nbtData = pPizza.getOrCreateTag();
        boolean pPlace = nbtData.getBoolean(PLACE_TAG);

        int pEatTime = nbtData.getInt(EAT_TAG);
        int pSlices = nbtData.getInt(SLICE_TAG);

        if (pSlices >= 1 && pEatTime >= -1){
            nbtData.putInt(EAT_TAG, pEatTime-1);
        }

        if (pSlices >= 1 && pEatTime == 0 && pPlayer.getFoodData().needsFood()){
            pPlayer.stopUsingItem();
        }

        if (pSlices <= 0 && !pPlayer.isCreative()){
            pPlayer.getCooldowns().addCooldown(this, 1200);
        }

        if (pSlices <= 0 && pPlayer.isCreative()){
            pPlayer.getCooldowns().addCooldown(this, 20);
        }

        if (pPlace){
            pPlayer.stopUsingItem();
        }
        super.onUseTick(pLevel, pLivingEntity, pStack, pRemainingUseDuration);
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        CompoundTag nbtdata = stack.getOrCreateTag();
        boolean pPlace = nbtdata.getBoolean(PLACE_TAG);
        int pSlices = nbtdata.getInt(SLICE_TAG);
        int pEatTime = nbtdata.getInt(EAT_TAG);
        if (entity instanceof Player pPlayer && !pPlace && pSlices != 0){
            pPlayer.getFoodData().eat(4,4);
            nbtdata.putInt(SLICE_TAG, pSlices-1);
            pPlayer.getCooldowns().addCooldown(stack.getItem(), 10 + pEatTime);
        }
        super.onStopUsing(stack, entity, count);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        CompoundTag nbtData = pStack.getOrCreateTag();
        if (!(pEntity instanceof Player pPlayer)){
            return;
        }
        nbtData.putBoolean(PLACE_TAG, pPlayer.isShiftKeyDown());
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Player pPlayer = pContext.getPlayer();
        Level pLevel = pContext.getLevel();
        BlockPos pBlockPos = pContext.getClickedPos();
        BlockPos pFacePos = pBlockPos.relative(pContext.getClickedFace());
        ItemStack pStack = pContext.getItemInHand();
        CompoundTag nbtData = pStack.getOrCreateTag();
        int pSlices = nbtData.getInt(SLICE_TAG);
        BlockState pState = ModBlocks.SCP458_BLOCK.get().defaultBlockState();
        BlockState pPizza =  (BlockState) pState.setValue(net.davegamer007vinicius1232426.unnamedscpmod.block.custom.SCP458.SLICES, pSlices)
                .setValue(net.davegamer007vinicius1232426.unnamedscpmod.block.custom.SCP458.FACING,pContext.getClickedFace());

        boolean pPlace = pStack.getOrCreateTag().getBoolean(PLACE_TAG);

        if (!pPlace){
            return super.useOn(pContext);
        }

        if (!(pLevel.getBlockState(pFacePos).isAir())){
            return InteractionResult.FAIL;
        }

        if (!pPlayer.isCreative()){
            pLevel.setBlock(pFacePos, pPizza, 1);
            pStack.setCount(pStack.getCount()-1);
        }

        if (pPlayer.isCreative()){
            pLevel.setBlock(pFacePos, pPizza, 1);
        }
        return super.useOn(pContext);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        CompoundTag nbtData = pStack.getOrCreateTag();
        boolean pPlace = nbtData.getBoolean(PLACE_TAG);
        if (pPlace){
            return UseAnim.BLOCK;
        }
        return UseAnim.EAT;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        CompoundTag nbtData = pStack.getOrCreateTag();
        boolean pPlace = nbtData.getBoolean(PLACE_TAG);
        if (!pPlace){
            return MAX_EAT_TIME+10;
        }
         return 0;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        CompoundTag nbtData = pStack.getOrCreateTag();
        int pSLICES = nbtData.getInt(SLICE_TAG);

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
