package io.github.redrain0o0.globaladvancements.mixin.client;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.client.gui.components.CommonButtons;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.options.AccessibilityOptionsScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Component component) {
        super(component);
    }

    @Definition(id = "l", field = "Lnet/minecraft/client/gui/screens/TitleScreen;l:I")
    @Expression("? + 36")
    @ModifyExpressionValue(method = "init", at = @At("MIXINEXTRAS:EXPRESSION"))
    private int gadva$giveMeL(int original, @Share("arg") LocalIntRef lRef) {
        lRef.set(original);
        return original;
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void gadva$init(CallbackInfo ci, @Share("arg") LocalIntRef lRef) {
        int l = lRef.get();
        SpriteIconButton advancementsIconButton = (SpriteIconButton)this.addRenderableWidget(CommonButtons.accessibility(20, (button) -> this.minecraft.setScreen(new AccessibilityOptionsScreen(this, this.minecraft.options)), true));
        advancementsIconButton.setPosition(this.width / 2 - 124, l - 36);
    }
}
