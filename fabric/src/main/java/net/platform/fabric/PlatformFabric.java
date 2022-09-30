package net.platform.fabric;

import net.platform.Platform;
import net.fabricmc.api.ModInitializer;

public class PlatformFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Platform.init();
    }
}