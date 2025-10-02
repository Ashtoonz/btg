package net.ashtoonz.btg.events;

import net.ashtoonz.btg.item.custom.Gungnir;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;

public class MouseEvents {
    public static boolean onScroll(PlayerInventory playerInventory, double scroll) {
        if(playerInventory.getStack(playerInventory.selectedSlot).getItem() instanceof Gungnir && playerInventory.player.isSneaking()) {
            ((Gungnir)(playerInventory.getStack(playerInventory.selectedSlot).getItem())).changeState((int) scroll);
            return true;
        }

        return false;
    }
}
