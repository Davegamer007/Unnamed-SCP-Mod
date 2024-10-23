package net.davegamer007vinicius1232426.unnamedscpmod.item.custom;

import net.davegamer007vinicius1232426.unnamedscpmod.block.ModBlocks;
import net.davegamer007vinicius1232426.unnamedscpmod.block.custom.Eternal_Flame_Block;
import net.davegamer007vinicius1232426.unnamedscpmod.effect.ModEffects;
import net.davegamer007vinicius1232426.unnamedscpmod.item.custom.abstracts.SCPFuelItem;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class SCP310Item extends SCPFuelItem {
    public SCP310Item(Properties pProperties) {
        super(pProperties, 200);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Player pPlayer = pContext.getPlayer();
        Level pLevel = pContext.getLevel();
        BlockPos pBlockPos = pContext.getClickedPos();
        BlockState pBlockState = pLevel.getBlockState(pBlockPos);
        BlockPos pClickedFace = pBlockPos.relative(pContext.getClickedFace());
        BlockState pOutputBlock = ModBlocks.ETERNAL_FLAME_BLOCK.get().defaultBlockState();

        if (!CampfireBlock.canLight(pBlockState) && !CandleBlock.canLight(pBlockState) && !CandleCakeBlock.canLight(pBlockState)) {
            if (Eternal_Flame_Block.canBePlacedAt(pBlockState,pLevel,pClickedFace)) {
                pLevel.playSound(pPlayer, pClickedFace, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0F, pLevel.getRandom().nextFloat() * 0.4F + 0.8F);
                pLevel.setBlock(pClickedFace, pOutputBlock, 11);
                pLevel.gameEvent(pPlayer, GameEvent.BLOCK_PLACE, pBlockPos);
                return InteractionResult.sidedSuccess(pLevel.isClientSide());
            } else {
                return InteractionResult.FAIL;
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.addEffect(new MobEffectInstance(ModEffects.ETERNAL_FLAME.get(), -1));
        return true;
    }
}
