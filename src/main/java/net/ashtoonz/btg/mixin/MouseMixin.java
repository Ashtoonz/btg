package net.ashtoonz.btg.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.ashtoonz.btg.events.MouseEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Mouse;
import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;


@Environment(EnvType.CLIENT)
@Mixin(Mouse.class)
public class MouseMixin {
    @WrapWithCondition(method = "onMouseScroll", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;scrollInHotbar(D)V"))
    public boolean onScroll(PlayerInventory playerInventory, double scroll) {
        boolean bypassesVanilla = MouseEvents.onScroll(playerInventory, scroll);
        return !bypassesVanilla;
    }
}
