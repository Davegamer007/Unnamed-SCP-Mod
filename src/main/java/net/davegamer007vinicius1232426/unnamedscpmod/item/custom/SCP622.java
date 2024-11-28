package net.davegamer007vinicius1232426.unnamedscpmod.item.custom;

import net.davegamer007vinicius1232426.unnamedscpmod.block.ModBlocks;
import net.davegamer007vinicius1232426.unnamedscpmod.item.custom.abstracts.SCPItem;
import net.davegamer007vinicius1232426.unnamedscpmod.util.SCP622Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.Map;

public class SCP622 extends SCPItem {
    public SCP622(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level pLevel = pContext.getLevel();
        BlockPos pClickedPos = pContext.getClickedPos();
        BlockPos pClickedFace = pClickedPos.relative(pContext.getClickedFace());
        BlockState pClickedState = pLevel.getBlockState(pClickedPos);
        FluidState pFluid = pLevel.getFluidState(pClickedFace);
        Player pPlayer = pContext.getPlayer();
        BlockState pDried = SCP622Map.dryMap(pClickedState);


        if (pPlayer.isCreative()){
            if (pDried != null && pDried != Blocks.AIR.defaultBlockState() && !pPlayer.isShiftKeyDown()){
                pLevel.setBlockAndUpdate(pClickedPos, pDried);
                playEffects(pPlayer, pClickedPos.getX(), pClickedPos.getY(), pClickedPos.getZ(), false);
                return InteractionResult.SUCCESS;
            }

            if (pDried == Blocks.AIR.defaultBlockState() && !pPlayer.isShiftKeyDown()){
                pLevel.destroyBlock(pClickedPos, true);
                playEffects(pPlayer, pClickedPos.getX(), pClickedPos.getY(), pClickedPos.getZ(), false);
                return InteractionResult.SUCCESS;
            }

            if (pPlayer.isShiftKeyDown()){
                pLevel.setBlockAndUpdate(pClickedFace, ModBlocks.SALT_BLOCK.get().defaultBlockState());
                playEffects(pPlayer, pClickedFace.getX(), pClickedFace.getY(), pClickedFace.getZ(),false);
                pLevel.scheduleTick(pClickedFace, ModBlocks.SALT_BLOCK.get(), 1);
            }
        }

        if (!pPlayer.isCreative()){
        if (pDried != null && pDried != Blocks.AIR.defaultBlockState() && !pPlayer.isShiftKeyDown()){
            pLevel.setBlockAndUpdate(pClickedPos,pDried);
            playEffects(pPlayer, pClickedPos.getX(), pClickedPos.getY(), pClickedPos.getZ(), false);
            pPlayer.getCooldowns().addCooldown(this,15);
            return InteractionResult.SUCCESS;
        }

        if (pDried == Blocks.AIR.defaultBlockState() && !pPlayer.isShiftKeyDown()){
            pLevel.destroyBlock(pClickedPos, true);
            playEffects(pPlayer, pClickedPos.getX(), pClickedPos.getY(), pClickedPos.getZ(), false);
            pPlayer.getCooldowns().addCooldown(this,15);
            return InteractionResult.SUCCESS;
        }

        if (pPlayer.isShiftKeyDown()){
            pLevel.setBlockAndUpdate(pClickedFace, ModBlocks.SALT_BLOCK.get().defaultBlockState());
            playEffects(pPlayer, pClickedFace.getX(), pClickedFace.getY(), pClickedFace.getZ(), false);
            pPlayer.getCooldowns().addCooldown(this,25);
            pLevel.scheduleTick(pClickedFace, ModBlocks.SALT_BLOCK.get(), 1);
        }
        }
        return InteractionResult.FAIL;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {

        if (pInteractionTarget instanceof Drowned pZombie){
            pZombie.convertTo(EntityType.ZOMBIE, true);
            pPlayer.getCooldowns().addCooldown(this, 25);
            playEffects(pPlayer, pInteractionTarget.getX(), pInteractionTarget.getEyeY(), pInteractionTarget.getZ(),true);
            return InteractionResult.SUCCESS;
        }

        if (pInteractionTarget instanceof Zombie pZombie && !(pInteractionTarget instanceof Drowned) && !(pInteractionTarget instanceof Husk)){
            pZombie.convertTo(EntityType.HUSK, true);
            pPlayer.getCooldowns().addCooldown(this, 25);
            playEffects(pPlayer, pInteractionTarget.getX(), pInteractionTarget.getEyeY(), pInteractionTarget.getZ(), true);
            return InteractionResult.SUCCESS;
        }
        if (!(pInteractionTarget instanceof Husk) && !pPlayer.getCooldowns().isOnCooldown(this)){
            pInteractionTarget.hurt(pInteractionTarget.damageSources().dryOut(), 5);
            pInteractionTarget.setLastHurtByMob(pPlayer);
            playEffects(pPlayer,pInteractionTarget.getX(), pInteractionTarget.getEyeY(), pInteractionTarget.getZ(), true);
            pPlayer.getCooldowns().addCooldown(this, 50);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    private static void playEffects(LivingEntity pLivingEntity, double pX, double pY, double pZ, boolean isEntity){
        pLivingEntity.playSound(SoundEvents.FIRE_EXTINGUISH,5, -15);
        if (isEntity){
            double pXspeed = (pLivingEntity.getX() - pX)*-0.5;
            double pYspeed = (pLivingEntity.getY() - pY)*-0.5;
            double pZspeed = (pLivingEntity.getZ() - pZ)*-0.5;

            for(int i = 0; i < 7; ++i) {
                double d3 = 0.4 + 0.2 * (double)i;
                pLivingEntity.level().addParticle(ParticleTypes.SPIT, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), pXspeed*d3, pYspeed*d3, pZspeed *d3);
            }
        }
        else {
            double pXspeed = (pLivingEntity.getX() - pX -0.5)*-0.5;
            double pYspeed = (pLivingEntity.getY() - pY -0.5)*-0.5;
            double pZspeed = (pLivingEntity.getZ() - pZ -0.5)*-0.5;

            for(int i = 0; i < 7; ++i) {
                double d3 = 0.4 + 0.2 * (double)i;
                pLivingEntity.level().addParticle(ParticleTypes.SPIT, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), pXspeed*d3, pYspeed*d3, pZspeed *d3);
            }
        }
    }
}
