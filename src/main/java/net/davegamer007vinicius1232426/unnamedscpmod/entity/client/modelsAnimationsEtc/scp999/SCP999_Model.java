package net.davegamer007vinicius1232426.unnamedscpmod.entity.client.modelsAnimationsEtc.scp999;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.davegamer007vinicius1232426.unnamedscpmod.entity.custom.SCP999Entity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class SCP999_Model<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart Ground;
	private final ModelPart Body;
	private final ModelPart HeadGoop;
	private final ModelPart Nuclei;
	private final ModelPart NucleiHead;
	private final ModelPart Face;
	private final ModelPart Eye_Right;
	private final ModelPart Eye_Left;
	private final ModelPart Mouth;
	private final ModelPart NucleiFoot;
	private final ModelPart FootGoop;
	private final ModelPart Trail;

	public SCP999_Model(ModelPart root) {
		this.Ground = root.getChild("Ground");
		this.Body = this.Ground.getChild("Body");
		this.HeadGoop = this.Body.getChild("HeadGoop");
		this.Nuclei = this.Body.getChild("Nuclei");
		this.NucleiHead = this.Nuclei.getChild("NucleiHead");
		this.Face = this.NucleiHead.getChild("Face");
		this.Eye_Right = this.Face.getChild("Eye_Right");
		this.Eye_Left = this.Face.getChild("Eye_Left");
		this.Mouth = this.Face.getChild("Mouth");
		this.NucleiFoot = this.Nuclei.getChild("NucleiFoot");
		this.FootGoop = this.Body.getChild("FootGoop");
		this.Trail = this.Ground.getChild("Trail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Ground = partdefinition.addOrReplaceChild("Ground", CubeListBuilder.create(), PartPose.offset(0.0F, 25.0F, 0.0F));

		PartDefinition Body = Ground.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition HeadGoop = Body.addOrReplaceChild("HeadGoop", CubeListBuilder.create().texOffs(0, 22).addBox(-7.0F, -11.0F, -7.0F, 14.0F, 10.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition Nuclei = Body.addOrReplaceChild("Nuclei", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition NucleiHead = Nuclei.addOrReplaceChild("NucleiHead", CubeListBuilder.create().texOffs(56, 34).addBox(-5.0F, -4.5F, -5.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition Face = NucleiHead.addOrReplaceChild("Face", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition Eye_Right = Face.addOrReplaceChild("Eye_Right", CubeListBuilder.create().texOffs(56, 59).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -1.5F, -4.1F));

		PartDefinition Eye_Left = Face.addOrReplaceChild("Eye_Left", CubeListBuilder.create().texOffs(56, 53).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -1.5F, -4.1F));

		PartDefinition Mouth = Face.addOrReplaceChild("Mouth", CubeListBuilder.create().texOffs(0, 62).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, -4.0F));

		PartDefinition NucleiFoot = Nuclei.addOrReplaceChild("NucleiFoot", CubeListBuilder.create().texOffs(0, 46).addBox(-7.0F, -2.0F, -7.0F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));

		PartDefinition FootGoop = Body.addOrReplaceChild("FootGoop", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -4.0F, -9.0F, 18.0F, 4.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition Trail = Ground.addOrReplaceChild("Trail", CubeListBuilder.create().texOffs(56, 22).addBox(-8.0F, -0.15F, 8.0F, 16.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.25F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyCoreRot((SCP999Entity) entity, netHeadYaw, headPitch, ageInTicks);
		this.applyTrail((SCP999Entity) entity, entity.xRotO, ageInTicks);

		SCP999Entity p999 = (SCP999Entity) entity;

		this.animateWalk(SCP999AnimationDefinitions.walk, limbSwing, limbSwingAmount, 1.5F, 1.0F);
		this.animate(p999.idleState, SCP999AnimationDefinitions.idleState, ageInTicks);
		this.animate(p999.spinState, SCP999AnimationDefinitions.Spin, ageInTicks);
		this.animate(p999.danceState, SCP999AnimationDefinitions.Jiggle, ageInTicks);
		this.animate(p999.squashedState, SCP999AnimationDefinitions.squash, ageInTicks);
		this.animate(p999.pongState, SCP999AnimationDefinitions.pong, ageInTicks);
		this.animate(p999.inAndOutState, SCP999AnimationDefinitions.in_and_out, ageInTicks);
		this.animate(p999.sleepState, SCP999AnimationDefinitions.sleeping, ageInTicks);
		this.animate(p999.blinkState, SCP999AnimationDefinitions.blink, ageInTicks);
	}

	private void applyCoreRot(SCP999Entity pEntity, float pHeadYawn,float pHeadPitch, float pAgeInTicks){
		pHeadYawn = Mth.clamp(pHeadYawn, -10.0F, 10.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -10.0F, 35.0F);

		this.NucleiHead.yRot = pHeadYawn*((float) Math.PI/180F);
		this.NucleiHead.xRot = pHeadPitch*((float) Math.PI/180F);
	}

	private void applyTrail(SCP999Entity pEntity, float pBodyRot, float pAgeInTicks){
		this.Trail.xRot = -pBodyRot*((float) Math.PI/180F);
		SCP999Entity p999 = (SCP999Entity) pEntity;
		if (p999.getSpeed() !=0 && !p999.isFallFlying()){
			this.Trail.visible= true;
		} else {this.Trail.visible = false;}


	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Ground.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Trail.render(poseStack,vertexConsumer,packedLight,packedOverlay,red,green,blue,alpha);
	}

	@Override
	public ModelPart root() {
		return Ground;
	}


}