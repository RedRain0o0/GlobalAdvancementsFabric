package io.github.redrain0o0.globaladvancements.client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.github.redrain0o0.globaladvancements.Globaladvancements;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class GlobaladvancementsClient implements ClientModInitializer {
    private static File advancements;
    private static Gson gson;

    @Override
    public void onInitializeClient() {
        gson = new Gson();

        // Create new Advancements file if it doesnt exist
        try {
            advancements = new File(FabricLoader.getInstance().getGameDir() + "/advancements.json");
            if (!advancements.exists()) {
                Globaladvancements.LOGGER.info("Advancements file not found, creating new one");
                advancements.createNewFile();
                try (Writer writer = new FileWriter(advancements)) {
                    String defaultFileContents = "{\"unlocked_advancements\":[]}";
                    writer.write(defaultFileContents);
                }
            }
        } catch (IOException e) {
            Globaladvancements.LOGGER.error("Error handling advancements file: " + e);
        }
    }

    //public static JsonObject getAdvancements() {
    //    gson.
    //    return
    //}
}
