package com.vincenthuto.gcen.client.model;

import com.vincenthuto.gcen.GluttonousCaninesEatingNightshades;

import net.minecraft.client.model.animal.wolf.BabyWolfModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.Identifier;

public class ChihuahuaDogBabyModel extends BabyWolfModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            Identifier.parse(GluttonousCaninesEatingNightshades.MODID + ":chihuahua_dog_baby"),
            "main");

    public ChihuahuaDogBabyModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition head = root.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                        .texOffs(0, 12)
                        .addBox(-3.0F, -3.5F, -3.0F, 6.0F, 5.5F, 5.0F, new CubeDeformation(0.025F))
                        .texOffs(17, 12)
                        .addBox(-1.25F, -0.35F, -5.0F, 2.5F, 1.75F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 18.5F, -3.75F));
        head.addOrReplaceChild(
                "right_ear",
                CubeListBuilder.create().texOffs(0, 5).addBox(-1.0F, -1.25F, -0.5F, 2.0F, 2.5F, 1.0F),
                PartPose.offset(-2.0F, -4.5F, -0.25F));
        head.addOrReplaceChild(
                "left_ear",
                CubeListBuilder.create().texOffs(20, 5).addBox(-1.0F, -1.25F, -0.5F, 2.0F, 2.5F, 1.0F),
                PartPose.offset(2.0F, -4.5F, -0.25F));

        root.addOrReplaceChild(
                "body",
                CubeListBuilder.create().texOffs(0, 0).addBox(-2.75F, -1.5F, -3.5F, 5.5F, 3.5F, 7.0F),
                PartPose.offset(0.0F, 20.0F, 0.5F));
        root.addOrReplaceChild(
                "right_hind_leg",
                CubeListBuilder.create().texOffs(0, 22).addBox(-0.75F, 0.0F, -0.75F, 1.5F, 2.5F, 1.5F),
                PartPose.offset(-1.25F, 21.5F, 2.75F));
        root.addOrReplaceChild(
                "left_hind_leg",
                CubeListBuilder.create().texOffs(8, 22).addBox(-0.75F, 0.0F, -0.75F, 1.5F, 2.5F, 1.5F),
                PartPose.offset(1.25F, 21.5F, 2.75F));
        root.addOrReplaceChild(
                "right_front_leg",
                CubeListBuilder.create().texOffs(0, 0).addBox(-0.75F, 0.0F, -0.75F, 1.5F, 2.5F, 1.5F),
                PartPose.offset(-1.25F, 21.5F, -2.25F));
        root.addOrReplaceChild(
                "left_front_leg",
                CubeListBuilder.create().texOffs(20, 0).addBox(-0.75F, 0.0F, -0.75F, 1.5F, 2.5F, 1.5F),
                PartPose.offset(1.25F, 21.5F, -2.25F));

        PartDefinition tail = root.addOrReplaceChild(
                "tail",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(0.0F, 20.0F, 3.25F, -0.5236F, 0.0F, 0.0F));
        tail.addOrReplaceChild(
                "tail_r1",
                CubeListBuilder.create().texOffs(22, 16).addBox(-0.75F, -4.7F, -0.75F, 1.5F, 5.0F, 1.5F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -0.4F, 0.15F, -3.1F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 32, 32);
    }
}

