package com.vincenthuto.gcen.client.renderer;

import com.vincenthuto.gcen.client.model.ChihuahuaDogAdultModel;
import com.vincenthuto.gcen.client.model.ChihuahuaDogBabyModel;
import com.vincenthuto.gcen.client.renderer.layer.ChihuahuaDogArmorLayer;
import com.vincenthuto.gcen.entity.ChihuahuaDogEntity;

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
public class ChihuahuaDogRenderer extends AgeableMobRenderer<ChihuahuaDogEntity, WolfRenderState, WolfModel> {
    private static final Identifier TEXTURE = Identifier.parse("gcen:textures/entity/chihuahua_dog.png");

    public ChihuahuaDogRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                new ChihuahuaDogAdultModel(context.bakeLayer(ChihuahuaDogAdultModel.LAYER_LOCATION)),
                new ChihuahuaDogBabyModel(context.bakeLayer(ChihuahuaDogBabyModel.LAYER_LOCATION)),
                0.3F);
        this.addLayer(new ChihuahuaDogArmorLayer(this, context.getModelSet(), context.getEquipmentRenderer()));
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
    public void extractRenderState(ChihuahuaDogEntity entity, WolfRenderState state, float partialTicks) {
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

