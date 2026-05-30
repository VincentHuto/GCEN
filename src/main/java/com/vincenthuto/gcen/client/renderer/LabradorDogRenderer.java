package com.vincenthuto.gcen.client.renderer;

import com.vincenthuto.gcen.client.model.LabradorDogAdultModel;
import com.vincenthuto.gcen.client.model.LabradorDogBabyModel;
import com.vincenthuto.gcen.client.renderer.layer.LabradorDogArmorLayer;
import com.vincenthuto.gcen.entity.LabradorDogEntity;

import net.minecraft.client.model.animal.wolf.WolfModel;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.WolfCollarLayer;
import net.minecraft.client.renderer.entity.state.WolfRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import org.jspecify.annotations.NullMarked;

@NullMarked
@SuppressWarnings("deprecation")
public class LabradorDogRenderer extends AgeableMobRenderer<LabradorDogEntity, WolfRenderState, WolfModel> {
    private static final Identifier TEXTURE = Identifier.parse("gcen:textures/entity/labrador_dog.png");

    public LabradorDogRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                new LabradorDogAdultModel(context.bakeLayer(LabradorDogAdultModel.LAYER_LOCATION)),
                new LabradorDogBabyModel(context.bakeLayer(LabradorDogBabyModel.LAYER_LOCATION)),
                0.5F);
        this.addLayer(new LabradorDogArmorLayer(this, context.getModelSet(), context.getEquipmentRenderer()));
        this.addLayer(new WolfCollarLayer(this));
    }

    @Override
    public Identifier getTextureLocation(WolfRenderState state) {
        return TEXTURE;
    }

    @Override
    protected int getModelTint(WolfRenderState state) {
        float wetShade = state.wetShade;
        return wetShade == 1.0F ? -1 : ARGB.colorFromFloat(1.0F, wetShade, wetShade, wetShade);
    }

    @Override
    public WolfRenderState createRenderState() {
        return new WolfRenderState();
    }

    @Override
    public void extractRenderState(LabradorDogEntity entity, WolfRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.isAngry = entity.isAngry();
        state.isSitting = entity.isInSittingPose();
        state.tailAngle = entity.getTailAngle();
        state.headRollAngle = entity.getHeadRollAngle(partialTicks);
        state.shakeAnim = entity.getShakeAnim(partialTicks);
        state.wetShade = entity.getWetShade(partialTicks);
        state.collarColor = entity.isTame() ? entity.getCollarColor() : null;
        state.bodyArmorItem = entity.getBodyArmorItem().copy();
    }
}

