package net.davegamer007vinicius1232426.unnamedscpmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SCP458 extends Block {
    public static int MAX_SLICES = 8;
    public static final DirectionProperty FACING = DirectionProperty.create("facing");
    public static final IntegerProperty SLICES = IntegerProperty.create("number_of_slices", 0, MAX_SLICES);
    public static final VoxelShape SHAPE = Block.box(0,0,0,16,8,16);
    public static final VoxelShape DOWN_SHAPE = Block.box(0,8,0,16,16,16);

    public SCP458(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pState.getValue(FACING) == Direction.DOWN){
            return DOWN_SHAPE;
        }
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        int pSlices = pState.getValue(SLICES);

        if (!pLevel.isClientSide && pSlices == 0 && pHand == InteractionHand.MAIN_HAND){
            pLevel.setBlockAndUpdate(pPos, (BlockState) pState.setValue(SLICES, MAX_SLICES).setValue(FACING, pState.getValue(FACING)));
        }

        else if (!pLevel.isClientSide && pPlayer.getFoodData().needsFood()|| pPlayer.isCreative() && pHand == InteractionHand.MAIN_HAND && pSlices > 0){
            pPlayer.getFoodData().eat(4,4);
            pLevel.setBlockAndUpdate(pPos, (BlockState) pState.setValue(SLICES,pSlices-1).setValue(FACING, pState.getValue(FACING)));
        }

        if (pLevel.isClientSide && pPlayer.getFoodData().needsFood()|| pPlayer.isCreative()
                && pHand == InteractionHand.MAIN_HAND && pLevel.isClientSide && pSlices > 0){
            return InteractionResult.CONSUME;
        }
        else if (pLevel.isClientSide && pSlices == 0 && pHand == InteractionHand.MAIN_HAND){
            return InteractionResult.SUCCESS;
        }
        return super.use(pState,pLevel,pPos,pPlayer,pHand,pHit);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {

        pBuilder.add(FACING);
        pBuilder.add(SLICES);
        super.createBlockStateDefinition(pBuilder);
    }
}
