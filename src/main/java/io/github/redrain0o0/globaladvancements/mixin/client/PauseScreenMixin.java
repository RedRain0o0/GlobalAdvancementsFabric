package io.github.redrain0o0.globaladvancements.mixin.client;

import io.github.redrain0o0.globaladvancements.Globaladvancements;
import io.github.redrain0o0.globaladvancements.PlayerAccess;
import io.github.redrain0o0.globaladvancements.client.screen.GlobalAdvancementsScreen;
import io.github.redrain0o0.globaladvancements.client.screen.GlobalStatsScreen;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Supplier;

@Mixin(PauseScreen.class)
public abstract class PauseScreenMixin extends Screen {
    protected PauseScreenMixin(Component component) {
        super(component);
    }

    @ModifyArg(method = "createPauseMenu", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/PauseScreen;openScreenButton(Lnet/minecraft/network/chat/Component;Ljava/util/function/Supplier;)Lnet/minecraft/client/gui/components/Button;", ordinal = 0), index = 1)
    private Supplier<Screen> gadva$displayGlobalAdvancementsScreen(Supplier<Screen> supplier) {
        // Check if server has mod and return either `supplier` or a GAS
        return () -> new GlobalAdvancementsScreen(this);
    }

    @ModifyArg(method = "createPauseMenu", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/PauseScreen;openScreenButton(Lnet/minecraft/network/chat/Component;Ljava/util/function/Supplier;)Lnet/minecraft/client/gui/components/Button;", ordinal = 1), index = 1)
    private Supplier<Screen> gadva$displayGlobalStatsScreen(Supplier<Screen> supplier) {
        // Check if server has mod and return either `supplier` or a GSS
        return () -> new GlobalStatsScreen(this);
    }
}
