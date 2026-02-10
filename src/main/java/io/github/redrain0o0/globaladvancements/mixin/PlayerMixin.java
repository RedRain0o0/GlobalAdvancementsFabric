package io.github.redrain0o0.globaladvancements.mixin;

import io.github.redrain0o0.globaladvancements.PlayerAccess;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements PlayerAccess {
    @Unique private boolean gadva$hasMod;

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
        this.gadva$hasMod = false;
    }

    @Unique
    public void gadva$setHasMod(boolean hasMod) {
        this.gadva$hasMod = hasMod;
    }

    @Unique
    public boolean gadva$getHasMod() {
        return this.gadva$hasMod;
    }
}
