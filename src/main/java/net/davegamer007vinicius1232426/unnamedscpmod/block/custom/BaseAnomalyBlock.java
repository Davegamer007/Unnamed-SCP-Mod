package net.davegamer007vinicius1232426.unnamedscpmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;



public class BaseAnomalyBlock extends Block {
    private final Entity pEntity;
    private Level pLevel;

    public BaseAnomalyBlock(Properties pProperties, Entity pEntity) {
        super(pProperties);
        this.pEntity = pEntity;
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        pLevel.addFreshEntity(pEntity);
        pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 1);
        this.pLevel = pLevel;
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        super.tick(pState, pLevel, pPos, pRandom);
        pLevel.addFreshEntity(pEntity);
        pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 1);
    }

    @Override
    public void onNeighborChange(BlockState state, LevelReader level, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(state, level, pos, neighbor);
        pLevel.addFreshEntity(pEntity);
        pLevel.setBlock(pos, Blocks.AIR.defaultBlockState(), 1);
    }
}
