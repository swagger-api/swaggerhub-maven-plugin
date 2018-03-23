package io.swagger.swaggerhub.plugin;


public class SwaggerHubRequest {
    private final String api;
    private final String owner;
    private final String version;
    private final String format;
    private final String swagger;
    private final boolean isPrivate;

    public String getApi() {
        return api;
    }

    public String getOwner() {
        return owner;
    }

    public String getVersion() {
        return version;
    }

    public String getFormat() {
        return format;
    }

    public String getSwagger() {
        return swagger;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    private SwaggerHubRequest(Builder builder) {
        this.api = builder.api;
        this.owner = builder.owner;
        this.version = builder.version;
        this.format = builder.format;
        this.swagger = builder.swagger;
        this.isPrivate = builder.isPrivate;
    }

    public static class Builder {
        private final String api;
        private final String owner;
        private final String version;
        private String format;
        private String swagger;
        private boolean isPrivate;

        public Builder(String api, String owner, String version) {
            this.api = api;
            this.owner = owner;
            this.version = version;
        }

        public Builder format(String format) {
            this.format = format;
            return this;
        }

        public Builder swagger(String swagger) {
            this.swagger = swagger;
            return this;
        }

        public Builder isPrivate(boolean isPrivate) {
            this.isPrivate = isPrivate;
            return this;
        }

        public SwaggerHubRequest build() {
            return new SwaggerHubRequest(this);
        }

    }
}
