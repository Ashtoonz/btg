package net.ashtoonz.btg.entity.client;

import net.ashtoonz.btg.BTG;
import net.ashtoonz.btg.entity.custom.MagicCircleEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class MagicCircleEntityRenderer extends EntityRenderer<MagicCircleEntity> {
    protected MagicCircleEntityModel model;

    private final float rotationSpeed = 1;
    private float rotation = 0;

    public MagicCircleEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new MagicCircleEntityModel(ctx.getPart(MagicCircleEntityModel.MAGIC_CIRCLE));
    }

    @Override
    public void render(MagicCircleEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        rotation += rotationSpeed;
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotation));

        matrices.translate(0, -1.4, 0);

        VertexConsumer vertexconsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers,
                this.model.getLayer(Identifier.of(BTG.MOD_ID, "textures/entity/magic_circle/magic_circle.png")), false, false);
        this.model.render(matrices, vertexconsumer, 255, OverlayTexture.DEFAULT_UV);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(MagicCircleEntity entity) {
        return null;
    }
}
