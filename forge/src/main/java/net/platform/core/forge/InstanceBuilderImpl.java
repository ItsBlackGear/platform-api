package net.platform.core.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import net.platform.core.Instance;

public class InstanceBuilderImpl {
    public static Instance builder(String modId, Runnable common, Runnable postCommon, Runnable client, Runnable postClient) {
        return new Instance(modId, common, postCommon, client, postClient) {
            @Override
            public void bootstrap() {
                IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
                bus.<FMLCommonSetupEvent>addListener(event -> this.postCommon.run());
                bus.<FMLClientSetupEvent>addListener(event -> this.postClient.run());

                this.common.run();
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> this.client.run());
            }

            @Override
            public boolean clientSide() {
                return FMLLoader.getDist().isClient();
            }
        };
    }
}