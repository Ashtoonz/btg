package net.ashtoonz.btg.entity.client;

import net.ashtoonz.btg.BTG;
import net.ashtoonz.btg.entity.custom.FallingGungnirEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;

public class FallingGungnirEntityRenderer extends EntityRenderer<FallingGungnirEntity> {
    protected FallingGungnirEntityModel model;

    public FallingGungnirEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new FallingGungnirEntityModel(ctx.getPart(FallingGungnirEntityModel.FALLING_GUNGNIR));
    }

    @Override
    public void render(FallingGungnirEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(360-entity.getYaw()));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(entity.getPitch()+90));

        matrices.scale(1.5f, 1.5f, 1.5f);

        matrices.translate(0, -1.5, 0);

        VertexConsumer vertexconsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers,
                this.model.getLayer(Identifier.of(BTG.MOD_ID, "textures/entity/falling_gungnir/falling_gungnir.png")), false, false);
        this.model.render(matrices, vertexconsumer, 255, OverlayTexture.DEFAULT_UV);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(FallingGungnirEntity entity) {
        return Identifier.of(BTG.MOD_ID, "textures/entity/falling_gungnir/falling_gungnir.png");
    }
}
