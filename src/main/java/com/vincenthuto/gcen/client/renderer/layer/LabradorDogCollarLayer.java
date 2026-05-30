package com.vincenthuto.gcen.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.vincenthuto.gcen.GluttonousCaninesEatingNightshades;

import net.minecraft.client.model.animal.wolf.WolfModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.WolfRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;

public class LabradorDogCollarLayer extends RenderLayer<WolfRenderState, WolfModel> {
    private static final Identifier COLLAR_LOCATION = Identifier.parse(
            GluttonousCaninesEatingNightshades.MODID + ":textures/entity/labrador_dog_collar.png");
    private static final Identifier BABY_COLLAR_LOCATION = Identifier.parse(
            GluttonousCaninesEatingNightshades.MODID + ":textures/entity/labrador_dog_collar_baby.png");

    public LabradorDogCollarLayer(RenderLayerParent<WolfRenderState, WolfModel> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, WolfRenderState state, float yRot, float xRot) {
        DyeColor collarColor = state.collarColor;
        if (collarColor != null && !state.isInvisible) {
            submitNodeCollector.order(1)
                    .submitModel(
                            this.getParentModel(),
                            state,
                            poseStack,
                            RenderTypes.entityCutout(state.isBaby ? BABY_COLLAR_LOCATION : COLLAR_LOCATION),
                            lightCoords,
                            OverlayTexture.NO_OVERLAY,
                            collarColor.getTextureDiffuseColor(),
                            null,
                            state.outlineColor,
                            null);
        }
    }
}

