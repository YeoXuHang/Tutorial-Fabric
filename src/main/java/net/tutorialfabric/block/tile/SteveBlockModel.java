package net.tutorialfabric.block.tile;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import javax.swing.text.html.parser.Entity;

public class SteveBlockModel<T extends Entity> extends Model {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "steveblock"), "main");
	public final ModelPart Root;
	private final ModelPart Steve;

	public SteveBlockModel(ModelPart root) {
		super(RenderType::entityCutout);
		this.Root = root;
		this.Steve = Root.getChild("Steve");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Steve = partdefinition.addOrReplaceChild("Steve", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 4.5F));

		PartDefinition head = Steve.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 5.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 3.5F, 0.0F));

		PartDefinition left_arm = Steve.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(9.0F, 3.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 11.0F, 5.5F, -0.6981F, 0.3491F, 0.0F));

		PartDefinition right_arm = Steve.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-13.0F, -0.5F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 13.5F, 4.0F, -0.6981F, -0.3491F, 0.0F));

		PartDefinition left_leg = Steve.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition right_leg = Steve.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-6.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
		Root.render(poseStack, vertexConsumer, i, j, f, g, h, k);
	}
}