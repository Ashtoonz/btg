package net.ashtoonz.btg.entity.client;

import net.ashtoonz.btg.BTG;
import net.ashtoonz.btg.entity.custom.MagicCircleEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class MagicCircleEntityModel extends EntityModel<MagicCircleEntity> {
	public static final EntityModelLayer MAGIC_CIRCLE = new EntityModelLayer(Identifier.of(BTG.MOD_ID, "magic_circle"), "main");
	private final ModelPart bb_main;

	public MagicCircleEntityModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(-40, 8).cuboid(-24.0F, -1.0F, -24.0F, 48.0F, 1.0F, 48.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 126, 63);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
		bb_main.render(matrices, vertexConsumer, light, overlay, color);
	}

	@Override
	public void setAngles(MagicCircleEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}
}