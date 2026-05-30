package com.vincenthuto.gcen.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.level.Level;

public class ChihuahuaDogEntity extends Wolf {
    public ChihuahuaDogEntity(EntityType<? extends Wolf> entityType, Level level) {
        super(entityType, level);
    }
}

