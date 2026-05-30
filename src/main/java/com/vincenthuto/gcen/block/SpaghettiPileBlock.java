package com.vincenthuto.gcen.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SpaghettiPileBlock extends Block {
    public static final MapCodec<SpaghettiPileBlock> CODEC = simpleCodec(SpaghettiPileBlock::new);
    // Match the custom model footprint: 2..14 on X/Z and 0..5 on Y.
    private static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 5.0, 14.0);

    @Override
    public MapCodec<SpaghettiPileBlock> codec() {
        return CODEC;
    }

    public SpaghettiPileBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}

