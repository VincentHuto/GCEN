package com.vincenthuto.gcen.client.model;

import com.vincenthuto.gcen.GluttonousCaninesEatingNightshades;

import net.minecraft.client.model.animal.wolf.AdultWolfModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.Identifier;

public class ChihuahuaDogAdultModel extends AdultWolfModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            Identifier.parse(GluttonousCaninesEatingNightshades.MODID + ":chihuahua_dog"),
            "main");
    public static final ModelLayerLocation ARMOR_LAYER_LOCATION = new ModelLayerLocation(
            Identifier.parse(GluttonousCaninesEatingNightshades.MODID + ":chihuahua_dog"),
            "armor");

    public ChihuahuaDogAdultModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return LayerDefinition.create(createBodyMesh(CubeDeformation.NONE), 64, 32);
    }

    public static LayerDefinition createArmorLayer() {
        return LayerDefinition.create(createBodyMesh(new CubeDeformation(0.15F)), 64, 32);
    }

    private static MeshDefinition createBodyMesh(CubeDeformation deformation) {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 15.0F, -4.75F));
        head.addOrReplaceChild(
                "real_head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.0F, -3.5F, -2.5F, 6.0F, 6.0F, 5.0F, deformation)
                        .texOffs(18, 0)
                        .addBox(-3.0F, -6.0F, -0.25F, 2.0F, 3.0F, 1.0F, deformation)
                        .texOffs(18, 0)
                        .addBox(1.0F, -6.0F, -0.25F, 2.0F, 3.0F, 1.0F, deformation)
                        .texOffs(0, 11)
                        .addBox(-1.5F, -0.5F, -5.0F, 3.0F, 2.0F, 3.0F, deformation),
                PartPose.ZERO);

        root.addOrReplaceChild(
                "body",
                CubeListBuilder.create().texOffs(20, 15).addBox(-2.5F, -1.75F, -3.0F, 5.0F, 7.0F, 5.0F, deformation),
                PartPose.offsetAndRotation(0.0F, 17.0F, 2.0F, (float) (Math.PI / 2), 0.0F, 0.0F));
        root.addOrReplaceChild(
                "upper_body",
                CubeListBuilder.create().texOffs(22, 4).addBox(-2.5F, -2.0F, -2.5F, 5.0F, 4.0F, 5.0F, deformation),
                PartPose.offsetAndRotation(0.0F, 17.0F, -1.75F, (float) (Math.PI / 2), 0.0F, 0.0F));

        CubeListBuilder leftLeg = CubeListBuilder.create().texOffs(0, 18).addBox(-0.5F, 0.0F, -0.75F, 1.5F, 5.0F, 1.5F, deformation);
        CubeListBuilder rightLeg = CubeListBuilder.create().mirror().texOffs(0, 18).addBox(-1.0F, 0.0F, -0.75F, 1.5F, 5.0F, 1.5F, deformation);
        root.addOrReplaceChild("right_hind_leg", rightLeg, PartPose.offset(-1.25F, 19.0F, 4.25F));
        root.addOrReplaceChild("left_hind_leg", leftLeg, PartPose.offset(1.25F, 19.0F, 4.25F));
        root.addOrReplaceChild("right_front_leg", rightLeg, PartPose.offset(-1.25F, 19.0F, -2.5F));
        root.addOrReplaceChild("left_front_leg", leftLeg, PartPose.offset(1.25F, 19.0F, -2.5F));

        PartDefinition tail = root.addOrReplaceChild(
                "tail",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(0.0F, 16.5F, 5.0F, (float) (Math.PI / 4), 0.0F, 0.0F));
        tail.addOrReplaceChild(
                "real_tail",
                CubeListBuilder.create().texOffs(9, 18).addBox(-0.75F, 0.0F, -0.75F, 1.5F, 5.0F, 1.5F, deformation),
                PartPose.ZERO);

        return mesh;
    }
}

