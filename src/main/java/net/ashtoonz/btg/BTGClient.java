package net.ashtoonz.btg;

import net.ashtoonz.btg.entity.ModEntities;
import net.ashtoonz.btg.entity.client.FallingGungnirEntityModel;
import net.ashtoonz.btg.entity.client.FallingGungnirEntityRenderer;
import net.ashtoonz.btg.entity.client.MagicCircleEntityModel;
import net.ashtoonz.btg.entity.client.MagicCircleEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class BTGClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(FallingGungnirEntityModel.FALLING_GUNGNIR, FallingGungnirEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.FALLING_GUNGNIR, FallingGungnirEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(MagicCircleEntityModel.MAGIC_CIRCLE, MagicCircleEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MAGIC_CIRCLE, MagicCircleEntityRenderer::new);
    }
}
