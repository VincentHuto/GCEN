package com.vincenthuto.gcen.entity.ai;

import com.vincenthuto.gcen.GluttonousCaninesEatingNightshades;
import com.vincenthuto.gcen.entity.ChihuahuaDogEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class ChihuahuaEatSpaghettiPileGoal extends MoveToBlockGoal {
    private final ChihuahuaDogEntity chihuahua;

    public ChihuahuaEatSpaghettiPileGoal(ChihuahuaDogEntity chihuahua, double speedModifier, int searchRange) {
        super(chihuahua, speedModifier, searchRange, 1);
        this.chihuahua = chihuahua;
    }

    @Override
    public boolean canUse() {
        return this.canEatSpaghettiPile() && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return this.canEatSpaghettiPile() && super.canContinueToUse();
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
        if (this.isReachedTarget() && this.chihuahua.level() instanceof ServerLevel serverLevel) {
            BlockPos pilePos = this.blockPos.above();
            BlockState pileState = serverLevel.getBlockState(pilePos);
            if (this.isSpaghettiPile(pileState)) {
                serverLevel.removeBlock(pilePos, false);
                serverLevel.playSound(null, pilePos, SoundEvents.GENERIC_EAT.value(), SoundSource.NEUTRAL, 0.7F,
                        0.9F + this.chihuahua.getRandom().nextFloat() * 0.2F);
                serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, pilePos, GameEvent.Context.of(this.chihuahua, pileState));
            }
        }
    }

    @Override
    protected boolean isValidTarget(LevelReader level, BlockPos pos) {
        return this.isSpaghettiPile(level.getBlockState(pos.above()));
    }

    private boolean canEatSpaghettiPile() {
        return !this.chihuahua.level().isClientSide()
                && !this.chihuahua.isOrderedToSit()
                && !this.chihuahua.isInSittingPose()
                && !this.chihuahua.isAngry()
                && this.chihuahua.getTarget() == null
                && !this.chihuahua.isLeashed()
                && !this.chihuahua.isPassenger();
    }

    private boolean isSpaghettiPile(BlockState state) {
        return state.is(GluttonousCaninesEatingNightshades.SPAGHETTI_PILE.get());
    }
}

