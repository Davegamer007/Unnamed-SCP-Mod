package net.davegamer007vinicius1232426.unnamedscpmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class SaltBlock extends FallingBlock {
    public static final int MAX_DEPTH = 6;
    public static final int MAX_COUNT = 64;
    private static final Direction[] ALL_DIRECTIONS = Direction.values();


    public SaltBlock(Properties pProperties) {
        super(pProperties);
    }

    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        this.tryAbsorbWater(pLevel, pPos);
    }

    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        this.tryAbsorbWater(pLevel, pPos);
        super.neighborChanged(pState, pLevel, pPos, pBlock, pFromPos, pIsMoving);
    }

    protected void tryAbsorbWater(Level pLevel, BlockPos pPos) {
        if (this.removeWaterBreadthFirstSearch(pLevel, pPos)) {
            pLevel.destroyBlock(pPos, true);
            pLevel.levelEvent(2001, pPos, Block.getId(Blocks.WATER.defaultBlockState()));
        }
    }

    private boolean removeWaterBreadthFirstSearch(Level pLevel, BlockPos pPos) {
        return BlockPos.breadthFirstTraversal(pPos, 6, 65, (p_277519_, p_277492_) -> {
            Direction[] var2 = ALL_DIRECTIONS;
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Direction direction = var2[var4];
                p_277492_.accept(p_277519_.relative(direction));
            }

        }, (p_279054_) -> {
            if (p_279054_.equals(pPos)) {
                return true;
            } else {
                BlockState blockstate = pLevel.getBlockState(p_279054_);
                if (blockstate.getBlock() instanceof LiquidBlock pLiquid && pLiquid.getFluid() == Fluids.WATER) {
                    pLevel.setBlock(p_279054_, Blocks.AIR.defaultBlockState(), 3);
                }
                else {
                    if (!blockstate.is(Blocks.KELP) && !blockstate.is(Blocks.KELP_PLANT) && !blockstate.is(Blocks.SEAGRASS) && !blockstate.is(Blocks.TALL_SEAGRASS)) {
                        return false;
                    }
                        BlockEntity blockentity = blockstate.hasBlockEntity() ? pLevel.getBlockEntity(p_279054_) : null;
                        dropResources(blockstate, pLevel, p_279054_, blockentity);
                        pLevel.setBlock(p_279054_, Blocks.AIR.defaultBlockState(), 3);
                    }

                    return true;
                }
            })>1;
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (isFree(pLevel.getBlockState(pPos.below())) && pPos.getY() >= pLevel.getMinBuildHeight()) {
            FallingBlockEntity $$4 = FallingBlockEntity.fall(pLevel, pPos, pState);
            this.falling($$4);
        }
        pLevel.updateNeighborsAt(pPos.below(), this);
    }
}
