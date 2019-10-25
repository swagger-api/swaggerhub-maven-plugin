package io.swagger.swaggerhub.plugin.requests;

import io.swagger.swaggerhub.plugin.DefinitionType;

/**
 * Configuration class used to store details required to create a request to SwaggerHub to upload/download a definition
 * The inner builder class follows the builder pattern which aids building the SwaggerHubRequest object.
 */
public class SwaggerHubRequest extends AbstractSwaggerHubRequest{

    private final String format;
    private final String swagger;
    private final boolean isPrivate;

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
        super(builder.definitionType, builder.api, builder.owner, builder.version, builder.oas);
        this.format = builder.format;
        this.swagger = builder.swagger;
        this.isPrivate = builder.isPrivate;
    }

    public static class Builder {
        private final DefinitionType definitionType;
        private final String api;
        private final String owner;
        private final String version;
        private String format;
        private String swagger;
        private boolean isPrivate;
        private String oas;

        public Builder(DefinitionType definitionType, String api, String owner, String version) {
            this.definitionType = definitionType;
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

        public Builder oas(String oas){
            this.oas = oas;
            return this;
        }

        public SwaggerHubRequest build() {
            return new SwaggerHubRequest(this);
        }

    }
}
