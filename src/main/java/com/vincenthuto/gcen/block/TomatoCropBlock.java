package com.vincenthuto.gcen.block;

import com.mojang.serialization.MapCodec;
import com.vincenthuto.gcen.GluttonousCaninesEatingNightshades;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TomatoCropBlock extends CropBlock {
    public static final MapCodec<TomatoCropBlock> CODEC = simpleCodec(TomatoCropBlock::new);
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape[] SHAPES = Block.boxes(3, age -> Block.column(16.0, 0.0, 2 + age * 2));

    @Override
    public MapCodec<TomatoCropBlock> codec() {
        return CODEC;
    }

    public TomatoCropBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return GluttonousCaninesEatingNightshades.TOMATO_SEEDS.get();
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(3) != 0) {
            super.randomTick(state, level, pos, random);
        }
    }

    @Override
    protected int getBonemealAgeIncrease(Level level) {
        return Math.max(1, super.getBonemealAgeIncrease(level) / 3);
    }

    @Override
    protected InteractionResult useItemOn(
            ItemStack itemStack,
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            InteractionHand hand,
            BlockHitResult hitResult) {
        if (!isMaxAge(state) && itemStack.is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        }

        return this.tryHarvest(state, level, pos, player);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return this.tryHarvest(state, level, pos, player);
    }

    private InteractionResult tryHarvest(BlockState state, Level level, BlockPos pos, Player player) {
        if (!isMaxAge(state)) {
            return InteractionResult.PASS;
        }

        if (level instanceof ServerLevel serverLevel) {
            int tomatoCount = 1 + serverLevel.getRandom().nextInt(2);
            Block.popResource(serverLevel, pos, new ItemStack(GluttonousCaninesEatingNightshades.TOMATO.get(), tomatoCount));

            BlockState resetState = state.setValue(AGE, 2);
            serverLevel.setBlock(pos, resetState, 2);
            serverLevel.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F,
                    0.9F + serverLevel.getRandom().nextFloat() * 0.2F);
            serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, resetState));
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES[this.getAge(state)];
    }
}



