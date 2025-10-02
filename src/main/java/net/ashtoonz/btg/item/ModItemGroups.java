package net.ashtoonz.btg.item;

import net.ashtoonz.btg.BTG;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup BTG_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(BTG.MOD_ID, "btg_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.GUNGNIR))
                    .displayName(Text.translatable("itemgroup.btg.btg_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.GUNGNIR);


                    }).build());

    public static void registerItemGroups() {
        BTG.LOGGER.info("Registering Item Groups for " + BTG.MOD_ID);
    }
}
