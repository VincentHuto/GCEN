package com.vincenthuto.gcen.entity;

import com.vincenthuto.gcen.GluttonousCaninesEatingNightshades;
import com.vincenthuto.gcen.entity.ai.ChihuahuaEatSpaghettiPileGoal;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;

public class ChihuahuaDogEntity extends Wolf {
    public ChihuahuaDogEntity(EntityType<? extends Wolf> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(9, new ChihuahuaEatSpaghettiPileGoal(this, 1.0, 10));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!this.isTame() && !this.isAngry()) {
            if (itemStack.is(GluttonousCaninesEatingNightshades.SPAGHETTI.get())) {
                if (!this.level().isClientSide()) {
                    itemStack.consume(1, player);
                    this.tryToTameWithSpaghetti(player);
                    return InteractionResult.SUCCESS_SERVER;
                }

                return InteractionResult.SUCCESS;
            }

            if (itemStack.is(Items.BONE)) {
                return InteractionResult.PASS;
            }
        }

        return super.mobInteract(player, hand);
    }

    private void tryToTameWithSpaghetti(Player player) {
        if (this.random.nextInt(3) == 0 && !EventHooks.onAnimalTame(this, player)) {
            this.tame(player);
            this.navigation.stop();
            this.setTarget(null);
            this.setOrderedToSit(true);
            this.level().broadcastEntityEvent(this, (byte) 7);
        } else {
            this.level().broadcastEntityEvent(this, (byte) 6);
        }
    }
}

