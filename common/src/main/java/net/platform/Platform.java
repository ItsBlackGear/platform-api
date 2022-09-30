package net.platform;

import net.platform.core.Instance;

public class Platform {
    public static final String MOD_ID = "platform";
    public static final Instance INSTANCE = Instance.create(Platform.MOD_ID).build();
    
    public static void init() {
        INSTANCE.bootstrap();
    }
}