package net.davegamer007vinicius1232426.unnamedscpmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BasePosterBlock extends Block {
    public static final DirectionProperty FACING = DirectionProperty.create("facing");
    public static final VoxelShape SHAPE_S = Block.box(0,0,15,16,16,16);
    public static final VoxelShape SHAPE_N = Block.box(0,0,0,16,16,1);
    public static final VoxelShape SHAPE_W = Block.box(0,0,0,1,16,16);
    public static final VoxelShape SHAPE_E = Block.box(15,0,0,16,16,16);
    public BasePosterBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch (pState.getValue(FACING)){
            default -> {
                return SHAPE_S;
            }
            case NORTH -> {
                return SHAPE_N;
            }
            case SOUTH -> {
                return SHAPE_S;
            }
            case EAST -> {
                return SHAPE_E;
            }
            case WEST -> {
                return SHAPE_W;
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
        super.createBlockStateDefinition(pBuilder);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return super.getStateForPlacement(pContext).setValue(FACING, pContext.getClickedFace().getOpposite());
    }
}
