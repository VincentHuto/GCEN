package com.vincenthuto.gcen.entity.ai;

import com.vincenthuto.gcen.GluttonousCaninesEatingNightshades;
import com.vincenthuto.gcen.block.TomatoCropBlock;
import com.vincenthuto.gcen.entity.LabradorDogEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class LabradorEatTomatoesGoal extends MoveToBlockGoal {
    private final LabradorDogEntity labrador;

    public LabradorEatTomatoesGoal(LabradorDogEntity labrador, double speedModifier, int searchRange) {
        super(labrador, speedModifier, searchRange, 1);
        this.labrador = labrador;
    }

    @Override
    public boolean canUse() {
        return this.canEatTomatoes() && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return this.canEatTomatoes() && super.canContinueToUse();
    }

    @Override
    protected int nextStartTick(PathfinderMob mob) {
        return reducedTickDelay(40 + mob.getRandom().nextInt(40));
    }

    @Override
    public double acceptedDistance() {
        return 2.0;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isReachedTarget() && this.labrador.level() instanceof ServerLevel serverLevel) {
            BlockPos cropPos = this.blockPos.above();
            BlockState cropState = serverLevel.getBlockState(cropPos);
            if (this.isFullyGrownTomato(cropState)) {
                BlockState resetState = cropState.setValue(TomatoCropBlock.AGE, 2);
                serverLevel.setBlock(cropPos, resetState, 2);
                serverLevel.playSound(null, cropPos, SoundEvents.GENERIC_EAT.value(), SoundSource.NEUTRAL, 0.7F,
                        0.9F + this.labrador.getRandom().nextFloat() * 0.2F);
                serverLevel.playSound(null, cropPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 0.6F,
                        0.9F + this.labrador.getRandom().nextFloat() * 0.2F);
                serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, cropPos, GameEvent.Context.of(this.labrador, resetState));
            }
        }
    }

    @Override
    protected boolean isValidTarget(LevelReader level, BlockPos pos) {
        return this.isFullyGrownTomato(level.getBlockState(pos.above()));
    }

    private boolean canEatTomatoes() {
        return !this.labrador.level().isClientSide()
                && !this.labrador.isOrderedToSit()
                && !this.labrador.isInSittingPose()
                && !this.labrador.isAngry()
                && this.labrador.getTarget() == null
                && !this.labrador.isLeashed()
                && !this.labrador.isPassenger();
    }

    private boolean isFullyGrownTomato(BlockState state) {
        return state.is(GluttonousCaninesEatingNightshades.TOMATO_CROP.get())
                && state.getValue(TomatoCropBlock.AGE) == TomatoCropBlock.MAX_AGE;
    }
}


