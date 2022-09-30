package net.platform.core;

import dev.architectury.injectables.annotations.ExpectPlatform;

public abstract class Instance {
    public final String modId;
    public Runnable common;
    public Runnable postCommon;
    public Runnable client;
    public Runnable postClient;

    protected Instance(String modId, Runnable common, Runnable postCommon, Runnable client, Runnable postClient) {
        this.modId = modId;
        this.common = common;
        this.postCommon = postCommon;
        this.client = client;
        this.postClient = postClient;
        this.fillEmpty();
    }

    public static Builder create(String modId) {
        return new Builder(modId);
    }

    public abstract void bootstrap();

    public abstract boolean clientSide();

    private void commonSetup(Runnable common) {
        this.common = common;
    }

    private void postCommonSetup(Runnable common) {
        this.postCommon = common;
    }

    private void clientSetup(Runnable client) {
        this.client = client;
    }

    private void postClientSetup(Runnable client) {
        this.postClient = client;
    }

    private void fillEmpty() {
        if (this.common == null) this.commonSetup(this::empty);
        if (this.postCommon == null) this.postCommonSetup(this::empty);
        if (this.client == null) this.clientSetup(this::empty);
        if (this.postClient == null) this.postClientSetup(this::empty);
    }

    private void empty() {}

    public static class Builder {
        private final String modId;
        private Runnable onCommon;
        private Runnable onPostCommon;
        private Runnable onClient;
        private Runnable onPostClient;

        protected Builder(String modId) {
            this.modId = modId;
        }

        public Builder common(Runnable common) {
            this.onCommon = common;
            return this;
        }

        public Builder postCommon(Runnable common) {
            this.onPostCommon = common;
            return this;
        }

        public Builder client(Runnable client) {
            this.onClient = client;
            return this;
        }

        public Builder postClient(Runnable client) {
            this.onPostClient = client;
            return this;
        }

        public Instance build() {
            return builder(this.modId, this.onCommon, this.onPostCommon, this.onClient, this.onPostClient);
        }

        @ExpectPlatform
        public static Instance builder(String modId, Runnable common, Runnable postCommon, Runnable client, Runnable postClient) {
            throw new AssertionError();
        }
    }
}