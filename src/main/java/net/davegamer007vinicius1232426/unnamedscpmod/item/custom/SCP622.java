package net.davegamer007vinicius1232426.unnamedscpmod.item.custom;

import net.davegamer007vinicius1232426.unnamedscpmod.block.ModBlocks;
import net.davegamer007vinicius1232426.unnamedscpmod.item.custom.abstracts.SCPItem;
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


        if (pPlayer.isCreative()){
            if (SCP622Map(pClickedState) != null && SCP622Map(pClickedState) != Blocks.AIR.defaultBlockState() && !pPlayer.isShiftKeyDown()){
                pLevel.setBlockAndUpdate(pClickedPos,SCP622Map(pClickedState));
                playEffects(pPlayer, pClickedPos.getX(), pClickedPos.getY(), pClickedPos.getZ());
                return InteractionResult.SUCCESS;
            }

            if (SCP622Map(pClickedState) == Blocks.AIR.defaultBlockState() && !pPlayer.isShiftKeyDown()){
                pLevel.destroyBlock(pClickedPos, true);
                playEffects(pPlayer, pClickedPos.getX(), pClickedPos.getY(), pClickedPos.getZ());
                return InteractionResult.SUCCESS;
            }

            if (pPlayer.isShiftKeyDown()){
                pLevel.setBlockAndUpdate(pClickedFace, ModBlocks.SALT_BLOCK.get().defaultBlockState());
                playEffects(pPlayer, pClickedFace.getX(), pClickedFace.getY(), pClickedFace.getZ());
                pLevel.scheduleTick(pClickedFace, ModBlocks.SALT_BLOCK.get(), 1);
            }
        }

        if (!pPlayer.isCreative()){
        if (SCP622Map(pClickedState) != null && SCP622Map(pClickedState) != Blocks.AIR.defaultBlockState() && !pPlayer.isShiftKeyDown()){
            pLevel.setBlockAndUpdate(pClickedPos,SCP622Map(pClickedState));
            playEffects(pPlayer, pClickedPos.getX(), pClickedPos.getY(), pClickedPos.getZ());
            pPlayer.getCooldowns().addCooldown(this,15);
            return InteractionResult.SUCCESS;
        }

        if (SCP622Map(pClickedState) == Blocks.AIR.defaultBlockState() && !pPlayer.isShiftKeyDown()){
            pLevel.destroyBlock(pClickedPos, true);
            playEffects(pPlayer, pClickedPos.getX(), pClickedPos.getY(), pClickedPos.getZ());
            pPlayer.getCooldowns().addCooldown(this,15);
            return InteractionResult.SUCCESS;
        }

        if (pPlayer.isShiftKeyDown()){
            pLevel.setBlockAndUpdate(pClickedFace, ModBlocks.SALT_BLOCK.get().defaultBlockState());
            playEffects(pPlayer, pClickedFace.getX(), pClickedFace.getY(), pClickedFace.getZ());
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
            playEffects(pPlayer, pInteractionTarget.getX(), pInteractionTarget.getY() + 1, pInteractionTarget.getZ());
            return InteractionResult.SUCCESS;
        }

        if (pInteractionTarget instanceof Zombie pZombie && !(pInteractionTarget instanceof Drowned) && !(pInteractionTarget instanceof Husk)){
            pZombie.convertTo(EntityType.HUSK, true);
            pPlayer.getCooldowns().addCooldown(this, 25);
            playEffects(pPlayer, pInteractionTarget.getX(), pInteractionTarget.getY() + 1, pInteractionTarget.getZ());
            return InteractionResult.SUCCESS;
        }

        if (pInteractionTarget != null && !(pInteractionTarget instanceof Husk) && !pPlayer.getCooldowns().isOnCooldown(this)){
            pInteractionTarget.hurt(pInteractionTarget.damageSources().dryOut(), 5);
            pInteractionTarget.setLastHurtByMob(pPlayer);
            playEffects(pPlayer,pInteractionTarget.getX(), pInteractionTarget.getY() + 1, pInteractionTarget.getZ());
            pPlayer.getCooldowns().addCooldown(this, 50);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    protected BlockState SCP622Map(BlockState pInput){
        Map<BlockState, BlockState> mapSCP622 = new HashMap<>();

        mapSCP622.put(Blocks.GRASS_BLOCK.defaultBlockState(), Blocks.DIRT.defaultBlockState());
        mapSCP622.put(Blocks.DIRT.defaultBlockState(), Blocks.COARSE_DIRT.defaultBlockState());
        mapSCP622.put(Blocks.COARSE_DIRT.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
        mapSCP622.put(Blocks.GRAVEL.defaultBlockState(), Blocks.SAND.defaultBlockState());

        mapSCP622.put(Blocks.BLUE_ICE.defaultBlockState(), Blocks.PACKED_ICE.defaultBlockState());
        mapSCP622.put(Blocks.PACKED_ICE.defaultBlockState(), Blocks.ICE.defaultBlockState());

        mapSCP622.put(Blocks.MUD.defaultBlockState(), Blocks.CLAY.defaultBlockState());
        mapSCP622.put(Blocks.CLAY.defaultBlockState(), Blocks.TERRACOTTA.defaultBlockState());
        mapSCP622.put(Blocks.TERRACOTTA.defaultBlockState(), Blocks.RED_SAND.defaultBlockState());

        mapSCP622.put(ModBlocks.FLESH_BLOCK.get().defaultBlockState(), Blocks.AIR.defaultBlockState());
        mapSCP622.put(ModBlocks.FLESH_SPONGE_BLOCK.get().defaultBlockState(), Blocks.AIR.defaultBlockState());
        mapSCP622.put(Blocks.SNOW.defaultBlockState(), Blocks.AIR.defaultBlockState());
        mapSCP622.put(Blocks.SNOW_BLOCK.defaultBlockState(), Blocks.AIR.defaultBlockState());
        mapSCP622.put(Blocks.ICE.defaultBlockState(), Blocks.AIR.defaultBlockState());

        return mapSCP622.get(pInput);
    }

    private static void playEffects(LivingEntity pLivingEntity, double pX, double pY, double pZ){
        pLivingEntity.playSound(SoundEvents.FIRE_EXTINGUISH,5, -15);

        double pXspeed = (pLivingEntity.getX() - pX)*-0.5;
        double pYspeed = (pLivingEntity.getY() - pY)*-0.5;
        double pZspeed = (pLivingEntity.getZ() - pZ)*-0.5;

        for(int i = 0; i < 7; ++i) {
            double d3 = 0.4 + 0.2 * (double)i;
            pLivingEntity.level().addParticle(ParticleTypes.SPIT, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), pXspeed*d3, pYspeed*d3, pZspeed *d3);
        }
    }
}
