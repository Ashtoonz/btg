package net.ashtoonz.btg.entity.client;

import net.ashtoonz.btg.BTG;
import net.ashtoonz.btg.entity.custom.FallingGungnirEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;

import java.awt.*;

public class FallingGungnirEntityModel extends EntityModel<FallingGungnirEntity> {
	public static final EntityModelLayer FALLING_GUNGNIR = new EntityModelLayer(Identifier.of(BTG.MOD_ID, "falling_gungnir"), "main");
	private final ModelPart handle;
	private final ModelPart neck;
	private final ModelPart blade;
	private final ModelPart layer2;
	private final ModelPart layer1;
	private final ModelPart layer0;
	private final ModelPart detail;

	public FallingGungnirEntityModel(ModelPart root) {
		this.handle = root.getChild("handle");
		this.neck = root.getChild("neck");
		this.blade = root.getChild("blade");
		this.layer2 = this.blade.getChild("layer2");
		this.layer1 = this.layer2.getChild("layer1");
		this.layer0 = this.layer1.getChild("layer0");
		this.detail = root.getChild("detail");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData handle = modelPartData.addChild("handle", ModelPartBuilder.create().uv(0, 0).cuboid(-8.25F, -27.4F, 7.7F, 0.5F, 27.0F, 0.6F, new Dilation(0.0F))
		.uv(6, 22).cuboid(-8.35F, -25.6F, 7.6F, 0.7F, 0.3F, 0.8F, new Dilation(0.0F))
		.uv(22, 6).cuboid(-8.35F, -27.5F, 7.6F, 0.7F, 0.3F, 0.8F, new Dilation(0.0F)), ModelTransform.pivot(8.0F, 24.0F, -8.05F));

		ModelPartData cube_r1 = handle.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-0.35F, -0.9F, -0.15F, 0.1F, 1.9F, 0.3F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.25F, -0.9F, -0.15F, 0.1F, 1.9F, 0.3F, new Dilation(0.0F)), ModelTransform.of(-8.0F, -26.35F, 8.0F, 0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r2 = handle.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-0.15F, -0.9F, 0.35F, 0.3F, 1.9F, 0.1F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.15F, -0.9F, 0.95F, 0.3F, 1.9F, 0.1F, new Dilation(0.0F)), ModelTransform.of(-8.0F, -26.35F, 7.3F, 0.0F, 0.0F, -0.3927F));

		ModelPartData cube_r3 = handle.addChild("cube_r3", ModelPartBuilder.create().uv(0, 0).cuboid(-0.15F, -0.9F, 0.35F, 0.3F, 1.9F, 0.1F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.15F, -0.9F, -0.25F, 0.3F, 1.9F, 0.1F, new Dilation(0.0F)), ModelTransform.of(-8.0F, -26.35F, 7.9F, 0.0F, 0.0F, 0.3927F));

		ModelPartData cube_r4 = handle.addChild("cube_r4", ModelPartBuilder.create().uv(0, 0).cuboid(-0.45F, -0.9F, -0.15F, 0.1F, 1.9F, 0.3F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-1.05F, -0.9F, -0.15F, 0.1F, 1.9F, 0.3F, new Dilation(0.0F)), ModelTransform.of(-7.3F, -26.35F, 8.0F, -0.3927F, 0.0F, 0.0F));

		ModelPartData neck = modelPartData.addChild("neck", ModelPartBuilder.create().uv(0, 0).cuboid(-0.2F, 10.2F, 0.7F, 0.4F, 1.2F, 0.4F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.2F, 10.2F, -0.1F, 0.4F, 1.2F, 0.4F, new Dilation(0.0F))
		.uv(20, 15).cuboid(-0.3F, 10.2F, 0.25F, 0.6F, 1.9F, 0.5F, new Dilation(0.0F))
		.uv(20, 18).cuboid(-0.4F, 9.6F, 0.15F, 0.8F, 0.8F, 0.7F, new Dilation(0.0F))
		.uv(16, 16).cuboid(-0.3F, 9.1F, -0.15F, 0.6F, 1.3F, 1.3F, new Dilation(0.0F))
		.uv(16, 18).cuboid(-0.3F, 7.4F, 0.15F, 0.6F, 3.0F, 0.7F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 14.2F, -0.55F));

		ModelPartData cube_r5 = neck.addChild("cube_r5", ModelPartBuilder.create().uv(20, 7).cuboid(-0.275F, -0.0076F, -0.5F, 0.55F, 1.5F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 11.2F, 0.5F, 0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r6 = neck.addChild("cube_r6", ModelPartBuilder.create().uv(6, 20).cuboid(-0.275F, -0.0076F, -0.5F, 0.55F, 1.5F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 11.2F, 0.5F, -0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r7 = neck.addChild("cube_r7", ModelPartBuilder.create().uv(22, 13).cuboid(-0.3F, -2.7F, -2.7F, 0.8F, 0.6F, 0.6F, new Dilation(0.0F))
		.uv(14, 22).cuboid(-0.3F, -2.0F, -2.0F, 0.8F, 0.6F, 0.6F, new Dilation(0.0F)), ModelTransform.of(-0.1F, 12.9F, 0.5F, -0.7854F, 0.0F, 0.0F));

		ModelPartData blade = modelPartData.addChild("blade", ModelPartBuilder.create(), ModelTransform.pivot(8.0F, 24.0F, -8.05F));

		ModelPartData cube_r8 = blade.addChild("cube_r8", ModelPartBuilder.create().uv(8, 23).cuboid(-0.3F, -0.3F, -0.3F, 0.6F, 0.6F, 0.6F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 5.7F, 8.0F, -0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r9 = blade.addChild("cube_r9", ModelPartBuilder.create().uv(20, 20).cuboid(-0.2F, -0.3F, -0.3F, 0.6F, 0.8F, 0.8F, new Dilation(0.0F)), ModelTransform.of(-8.1F, 3.1F, 8.0F, -0.7854F, 0.0F, 0.0F));

		ModelPartData layer2 = blade.addChild("layer2", ModelPartBuilder.create().uv(22, 22).cuboid(-8.2F, 3.8F, 7.7F, 0.4F, 1.2F, 0.6F, new Dilation(0.0F))
		.uv(24, 0).cuboid(-8.3F, 3.7F, 7.9F, 0.6F, 1.8F, 0.2F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-8.2F, 10.4F, 7.9F, 0.4F, 1.2F, 0.2F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-8.2F, 8.9F, 7.8F, 0.4F, 1.5F, 0.4F, new Dilation(0.0F))
		.uv(20, 12).cuboid(-8.2F, 7.4F, 7.6F, 0.4F, 1.5F, 0.8F, new Dilation(0.0F))
		.uv(20, 9).cuboid(-8.2F, 5.0F, 7.5F, 0.4F, 1.5F, 1.0F, new Dilation(0.0F))
		.uv(16, 9).cuboid(-8.2F, 2.6F, 7.2F, 0.4F, 1.3F, 1.6F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-8.2F, 6.5F, 7.8F, 0.4F, 0.9F, 0.4F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData layer1 = layer2.addChild("layer1", ModelPartBuilder.create().uv(6, 23).cuboid(-8.1F, 10.4F, 7.7F, 0.2F, 1.5F, 0.6F, new Dilation(0.0F))
		.uv(10, 20).cuboid(-8.1F, 8.9F, 7.5F, 0.2F, 1.5F, 1.0F, new Dilation(0.0F))
		.uv(20, 0).cuboid(-8.1F, 7.4F, 7.3F, 0.2F, 1.5F, 1.4F, new Dilation(0.0F))
		.uv(16, 12).cuboid(-8.1F, 5.0F, 7.2F, 0.2F, 1.5F, 1.6F, new Dilation(0.0F))
		.uv(16, 6).cuboid(-8.1F, 2.6F, 6.9F, 0.2F, 1.5F, 2.2F, new Dilation(0.0F))
		.uv(14, 20).cuboid(-8.1F, 6.5F, 7.5F, 0.2F, 0.9F, 1.0F, new Dilation(0.0F))
		.uv(12, 20).cuboid(-8.1F, 4.1F, 7.4F, 0.2F, 0.9F, 1.2F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData layer0 = layer1.addChild("layer0", ModelPartBuilder.create().uv(24, 2).cuboid(-8.0F, 2.5F, 8.9F, 0.0F, 1.6F, 0.5F, new Dilation(0.0F))
		.uv(22, 9).cuboid(-8.0F, 8.5F, 8.3F, 0.0F, 2.6F, 0.5F, new Dilation(0.0F))
		.uv(12, 22).cuboid(-8.0F, 8.5F, 7.3F, 0.0F, 2.6F, 0.5F, new Dilation(0.0F))
		.uv(4, 24).cuboid(-8.0F, 2.5F, 6.6F, 0.0F, 1.6F, 0.5F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r10 = layer0.addChild("cube_r10", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -1.7F, -0.7F, 0.0F, 1.7F, 0.5F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 12.6F, 8.4F, -0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r11 = layer0.addChild("cube_r11", ModelPartBuilder.create().uv(22, 3).cuboid(0.0F, -2.1F, 0.8F, 0.0F, 2.4F, 0.6F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 6.1F, 7.5F, 0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r12 = layer0.addChild("cube_r12", ModelPartBuilder.create().uv(20, 22).cuboid(0.0F, 1.201F, -0.4052F, 0.0F, 1.6F, 0.8F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 0.3F, 8.0F, 0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r13 = layer0.addChild("cube_r13", ModelPartBuilder.create().uv(18, 22).cuboid(0.0F, 1.201F, -0.4052F, 0.0F, 1.6F, 0.8F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 0.3F, 8.0F, -0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r14 = layer0.addChild("cube_r14", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -1.3F, -0.5F, 0.0F, 1.6F, 0.4F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 5.8F, 7.5F, -0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r15 = layer0.addChild("cube_r15", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -2.3F, 0.4F, 0.0F, 2.6F, 0.4F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 8.1F, 7.5F, 0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r16 = layer0.addChild("cube_r16", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -1.3F, -0.5F, 0.0F, 1.6F, 0.4F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 7.8F, 7.5F, -0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r17 = layer0.addChild("cube_r17", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -2.3F, 0.4F, 0.0F, 2.6F, 0.4F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 10.1F, 7.5F, 0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r18 = layer0.addChild("cube_r18", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -1.7F, 0.3F, 0.0F, 1.7F, 0.4F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 12.6F, 7.6F, 0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r19 = layer0.addChild("cube_r19", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -2.7F, 0.2F, 0.0F, 2.6F, 0.4F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 10.1F, 7.5F, -0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r20 = layer0.addChild("cube_r20", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -0.9F, 1.1F, 0.0F, 1.6F, 0.4F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 7.8F, 7.5F, 0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r21 = layer0.addChild("cube_r21", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -2.7F, 0.2F, 0.0F, 2.6F, 0.4F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 8.1F, 7.5F, -0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r22 = layer0.addChild("cube_r22", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -0.9F, 1.1F, 0.0F, 1.6F, 0.4F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 5.8F, 7.5F, 0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r23 = layer0.addChild("cube_r23", ModelPartBuilder.create().uv(22, 0).cuboid(0.0F, -2.8F, -0.7F, 0.0F, 2.4F, 0.6F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 6.1F, 7.5F, -0.7854F, 0.0F, 0.0F));

		ModelPartData detail = modelPartData.addChild("detail", ModelPartBuilder.create().uv(4, 14).cuboid(-8.1F, 1.0F, 3.1F, 0.2F, 4.0F, 1.5F, new Dilation(0.0F))
		.uv(4, 10).cuboid(-8.0F, 2.5F, 1.7F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(10, 6).cuboid(-8.0F, 2.5F, 11.4F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(12, 0).cuboid(-8.1F, 1.0F, 11.4F, 0.2F, 4.0F, 1.6F, new Dilation(0.0F)), ModelTransform.pivot(8.0F, 24.0F, -8.05F));

		ModelPartData cube_r24 = detail.addChild("cube_r24", ModelPartBuilder.create().uv(16, 0).cuboid(-0.1F, -2.0F, 3.1F, 0.2F, 4.0F, 1.5F, new Dilation(0.0F))
		.uv(4, 0).cuboid(-0.1F, -4.6F, -2.0F, 0.2F, 1.6F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 3.0F, 8.4F, -0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r25 = detail.addChild("cube_r25", ModelPartBuilder.create().uv(12, 14).cuboid(-0.1F, -1.9F, -4.5F, 0.2F, 4.0F, 1.5F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 3.0F, 7.6F, 0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r26 = detail.addChild("cube_r26", ModelPartBuilder.create().uv(10, 10).cuboid(0.0F, -0.5F, 3.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(4, 20).cuboid(0.0F, 3.0F, -0.5F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 3.0F, 8.4F, 0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r27 = detail.addChild("cube_r27", ModelPartBuilder.create().uv(20, 3).cuboid(0.0F, 2.9F, -0.5F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 6).cuboid(0.0F, -0.5F, -5.9F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(8, 14).cuboid(-0.1F, -2.0F, -4.5F, 0.2F, 4.0F, 1.5F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 3.0F, 7.6F, -0.7854F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	@Override
	public void setAngles(FallingGungnirEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
		handle.render(matrices, vertexConsumer, light, overlay, color);
		neck.render(matrices, vertexConsumer, light, overlay, color);
		blade.render(matrices, vertexConsumer, light, overlay, color);
		detail.render(matrices, vertexConsumer, light, overlay, color);
	}
}