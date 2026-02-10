package io.github.redrain0o0.globaladvancements;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Globaladvancements implements ModInitializer {
    public static String MOD_ID = "globaladvancements";
    public static Logger LOGGER = LoggerFactory.getLogger("GlobalAdvancments");

    @Override
    public void onInitialize() {
        ServerPlayerEvents.JOIN.register((player) -> {
            ((PlayerAccess)player).gadva$setHasMod(true);
        });
    }
}
