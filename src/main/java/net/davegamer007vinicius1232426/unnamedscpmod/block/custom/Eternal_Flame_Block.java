package net.davegamer007vinicius1232426.unnamedscpmod.block.custom;

import net.davegamer007vinicius1232426.unnamedscpmod.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.portal.PortalShape;

import javax.annotation.Nullable;

import static net.minecraft.world.level.block.BaseFireBlock.getState;

public class Eternal_Flame_Block extends FallingBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public Eternal_Flame_Block(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(WATERLOGGED, false)));
    }

    private static boolean isNotUnderwater(BlockState pState){return !(Boolean)pState.getValue(WATERLOGGED);}

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(24) == 0) {
            pLevel.playLocalSound((double)pPos.getX() + 0.5, (double)pPos.getY() + 0.5, (double)pPos.getZ() + 0.5, SoundEvents.FIRE_AMBIENT, SoundSource.BLOCKS, 1.0F + pRandom.nextFloat(), pRandom.nextFloat() * 0.7F + 0.3F, false);
        }
        int j1;
        double d7;
        double d12;
        double d17;
        for(j1 = 0; j1 < 3; ++j1) {
            d7 = (double)pPos.getX() + pRandom.nextDouble();
            d12 = (double)pPos.getY() + pRandom.nextDouble() * 0.5 + 0.5;
            d17 = (double)pPos.getZ() + pRandom.nextDouble();
            if (isNotUnderwater(pState)){
                pLevel.addParticle(ParticleTypes.LARGE_SMOKE, d7, d12, d17, 0.0, 0.0, 0.0);
            } else
                pLevel.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, d7, d12, d17, 0.0, 0.0, 0.0);
        }
        if (!pState.canSurvive(pLevel, pPos)) {
            pLevel.destroyBlock(pPos,false);
            pLevel.removeBlock(pPos,false);
            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(),0);
            pLevel.removeBlockEntity(pPos);
        }
        if (!pState.canSurvive(pLevel, pPos)) {
            pLevel.playLocalSound((double)pPos.getX() + 0.5,(double)pPos.getX() + 0.5,(double)pPos.getX() + 0.5, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS,1.0F + pRandom.nextFloat(),pRandom.nextFloat() * 0.7F + 0.3F,false);
        }
    }

    @Override
    protected void falling(FallingBlockEntity pEntity) {
        pEntity.playSound(SoundEvents.FIRE_EXTINGUISH);
        pEntity.kill();
    }

    public void entityInside(BlockState state, Level level, BlockPos blockPos, Entity entity) {
        if (entity instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(ModEffects.ETERNAL_FLAME.get(), -1));
        }
        if (entity instanceof Arrow arrow){
            arrow.addEffect(new MobEffectInstance(ModEffects.ETERNAL_FLAME.get(), -1));
        }
        else entity.setSecondsOnFire(8);
    }

    public static boolean canBePlacedAt (BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return !pState.getCollisionShape(pLevel, pPos).getFaceShape(Direction.UP).isEmpty() || pState.isFaceSturdy(pLevel, pPos, Direction.UP);
    }











    //waterlogged stuff



    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos1) {
        if (state.getValue(BlockStateProperties.WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return super.updateShape(state, direction, state1, levelAccessor, blockPos, blockPos1);
    }

    public FluidState getFluidState(BlockState pState) {
        return (Boolean)pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(new Property[]{WATERLOGGED});
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState $$1 = pContext.getLevel().getBlockState(pContext.getClickedPos());
            FluidState pFluidState = pContext.getLevel().getFluidState(pContext.getClickedPos());
            boolean $$3 = pFluidState.getType() == Fluids.WATER;
            return (BlockState)super.getStateForPlacement(pContext).setValue(BlockStateProperties.WATERLOGGED, $$3);
    }
}
