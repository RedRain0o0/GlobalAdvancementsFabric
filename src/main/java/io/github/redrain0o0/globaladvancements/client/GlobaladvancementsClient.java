package io.github.redrain0o0.globaladvancements.client;

import io.github.redrain0o0.globaladvancements.Globaladvancements;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;

public class GlobaladvancementsClient implements ClientModInitializer {
    private static File advancements;

    @Override
    public void onInitializeClient() {
        try {
            Globaladvancements.LOGGER.info("{}", FabricLoader.getInstance().getGameDir());
            advancements = new File(FabricLoader.getInstance().getGameDir() + "/advancements.json");
            if (!advancements.exists()) {
                Globaladvancements.LOGGER.info("Advancements file not found, creating new one");
                Globaladvancements.LOGGER.info("{}", advancements.exists());
                advancements.createNewFile();
                Globaladvancements.LOGGER.info("{}", advancements.exists());
            }
        } catch (IOException e) {
            Globaladvancements.LOGGER.error("Error handling advancements file: " + e);
        }
    }

    public static File getAdvancements() {
        return advancements;
    }
}
