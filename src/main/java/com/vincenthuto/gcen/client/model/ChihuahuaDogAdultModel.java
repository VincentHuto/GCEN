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

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(-1.0F, 15.5F, -7.0F));
        head.addOrReplaceChild(
                "real_head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.0F, -3.0F, -2.0F, 6.0F, 6.0F, 4.0F, deformation)
                        .texOffs(16, 14)
                        .addBox(-3.0F, -4.0F, 0.0F, 2.0F, 1.0F, 1.0F, deformation)
                        .texOffs(16, 14)
                        .addBox(1.0F, -4.0F, 0.0F, 2.0F, 1.0F, 1.0F, deformation)
                        .texOffs(1, 11)
                        .addBox(-1.5F, -0.0156F, -4.0F, 3.0F, 3.0F, 3.0F, deformation),
                PartPose.ZERO);

        root.addOrReplaceChild(
                "body",
                CubeListBuilder.create().texOffs(18, 14).addBox(-4.0F, -2.0F, -7.0F, 6.0F, 6.0F, 6.0F, deformation),
                PartPose.offsetAndRotation(0.0F, 14.0F, 2.0F, (float) (Math.PI / 2), 0.0F, 0.0F));
        root.addOrReplaceChild(
                "upper_body",
                CubeListBuilder.create().texOffs(21, 0).addBox(-4.0F, -8.0F, -7.0F, 8.0F, 6.0F, 7.0F, deformation),
                PartPose.offsetAndRotation(-1.0F, 14.0F, 2.0F, (float) (Math.PI / 2), 0.0F, 0.0F));

        root.addOrReplaceChild(
                "right_hind_leg",
                CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, 2.0F, -1.0F, 2.0F, 6.0F, 2.0F, deformation),
                PartPose.offset(-2.5F, 16.0F, 4.0F));
        root.addOrReplaceChild(
                "left_hind_leg",
                CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, 2.0F, -1.0F, 2.0F, 6.0F, 2.0F, deformation),
                PartPose.offset(0.5F, 16.0F, 4.0F));
        root.addOrReplaceChild(
                "right_front_leg",
                CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, 2.0F, -1.0F, 2.0F, 6.0F, 2.0F, deformation),
                PartPose.offset(-2.5F, 16.0F, -4.0F));
        root.addOrReplaceChild(
                "left_front_leg",
                CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, 2.0F, -1.0F, 2.0F, 6.0F, 2.0F, deformation),
                PartPose.offset(0.5F, 16.0F, -4.0F));

        PartDefinition tail = root.addOrReplaceChild(
                "tail",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(-1.0F, 18.0F, 5.0F, 0.9599F, 0.0F, 0.0F));
        tail.addOrReplaceChild(
                "real_tail",
                CubeListBuilder.create().texOffs(9, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, deformation),
                PartPose.ZERO);

        return mesh;
    }
}

