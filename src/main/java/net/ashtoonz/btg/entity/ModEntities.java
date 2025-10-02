package net.ashtoonz.btg.entity;

import net.ashtoonz.btg.BTG;
import net.ashtoonz.btg.entity.custom.FallingGungnirEntity;
import net.ashtoonz.btg.entity.custom.MagicCircleEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<FallingGungnirEntity> FALLING_GUNGNIR = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(BTG.MOD_ID, "falling_gungnir"),
            EntityType.Builder.<FallingGungnirEntity>create(FallingGungnirEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 1.15f).build());

    public static final EntityType<MagicCircleEntity> MAGIC_CIRCLE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(BTG.MOD_ID, "magic_circle"),
            EntityType.Builder.<MagicCircleEntity>create(MagicCircleEntity::new, SpawnGroup.MISC)
                    .dimensions(2f, 0.5f).build());

    public static void registerModEntities() {
        BTG.LOGGER.info("Registering Mod Entities for " + BTG.MOD_ID);
    }
}
