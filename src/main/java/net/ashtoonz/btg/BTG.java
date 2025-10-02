package net.ashtoonz.btg;

import net.ashtoonz.btg.entity.ModEntities;
import net.ashtoonz.btg.item.ModItemGroups;
import net.ashtoonz.btg.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BTG implements ModInitializer {
	public static final String MOD_ID = "btg";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModEntities.registerModEntities();
	}
}