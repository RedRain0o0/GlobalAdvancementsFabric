package io.github.redrain0o0.globaladvancements.mixin;

import io.github.redrain0o0.globaladvancements.Globaladvancements;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.server.PlayerAdvancements;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerAdvancements.class)
public abstract class PlayerAdvancementsMixin {
    @Inject(method = "award", at = @At("HEAD"))
    private void gadva$award(AdvancementHolder advancementHolder, String string, CallbackInfoReturnable<Boolean> cir) {
        Globaladvancements.LOGGER.info("{}", advancementHolder);
    }
}
