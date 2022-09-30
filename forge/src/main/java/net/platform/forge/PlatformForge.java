package net.platform.forge;

import net.platform.Platform;
import net.minecraftforge.fml.common.Mod;

@Mod(Platform.MOD_ID)
public class PlatformForge {
    public PlatformForge() {
        Platform.init();
    }
}
