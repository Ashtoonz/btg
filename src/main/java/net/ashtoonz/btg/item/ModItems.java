package net.ashtoonz.btg.item;

import net.ashtoonz.btg.BTG;
import net.ashtoonz.btg.item.custom.Gungnir;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item GUNGNIR = registerItem("gungnir", new Gungnir(new Item.Settings()
            .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 5, -3.0f))));

    private static Item registerItem(String pName, Item pItem) {
        return Registry.register(Registries.ITEM, Identifier.of(BTG.MOD_ID, pName), pItem);
    }

    public static void registerModItems() {
        BTG.LOGGER.info("Registering Mod Items for " + BTG.MOD_ID);
    }
}
