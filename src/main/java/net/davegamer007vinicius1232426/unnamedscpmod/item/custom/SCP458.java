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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class SCP458 extends SCPItem {

    public SCP458(Properties pProperties) {
        super(pProperties);
    }

    public int SLICES = 8;

    public int MAX_SLICES = 8;

    public int EAT_TIME = 20;

    public int MAX_EAT_TIME = 20;

    private boolean SHOULD_BE_PLACED = false;



    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pPlayer.isShiftKeyDown() && pPlayer.getFoodData().needsFood()||pPlayer.isCreative()){
            SHOULD_BE_PLACED = false;
            EAT_TIME = MAX_EAT_TIME;
            pPlayer.startUsingItem(pUsedHand);
        }

        if (SLICES == 0){
            SLICES = MAX_SLICES;
        }

        if (pPlayer.isShiftKeyDown() && !pPlayer.isUsingItem()){
            SHOULD_BE_PLACED = true;
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {

        if (!(pLivingEntity instanceof Player pPlayer)){
            return;
        }

        if (pPlayer.isShiftKeyDown()){
            return;
        }

        if (SLICES > 0 && EAT_TIME > 0){
            EAT_TIME = EAT_TIME -1;
        }

        if (SLICES > 0 && EAT_TIME == 0 && pPlayer.getFoodData().needsFood()){
            pPlayer.getFoodData().eat(4,4);
            SLICES = SLICES -1;
            EAT_TIME = MAX_EAT_TIME;
            pPlayer.stopUsingItem();
        }

        if (SLICES <= 0 && !pPlayer.isCreative()){
            pPlayer.getCooldowns().addCooldown(this, 1200);
        }
        if (SLICES <= 0 && pPlayer.isCreative()){
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

        if (!SHOULD_BE_PLACED){
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
        if (SHOULD_BE_PLACED){
            return UseAnim.BLOCK;
        }
        return UseAnim.EAT;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        if (!SHOULD_BE_PLACED){
            return 20;
        }
         return 0;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (SLICES > 1) {
            pTooltipComponents.add(Component.literal("There are " + SLICES + " slices left in the box"));
        }

        if (SLICES == 1) {
            pTooltipComponents.add(Component.literal("There is " + SLICES + " slice left in the box"));
        }

        if (SLICES == 0) {
            pTooltipComponents.add(Component.literal("Regenerating..."));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
