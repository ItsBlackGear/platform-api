package net.platform.core.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.platform.core.Instance;

public class InstanceBuilderImpl {
    public static Instance builder(String modId, Runnable common, Runnable postCommon, Runnable client, Runnable postClient) {
        return new Instance(modId, common, postCommon, client, postClient) {
            @Override
            public boolean clientSide() {
                return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
            }

            @Override
            public void bootstrap() {
                this.common.run();
                this.postCommon.run();

                if (this.clientSide()) {
                    this.client.run();
                    this.postClient.run();
                }
            }
        };
    }
}
