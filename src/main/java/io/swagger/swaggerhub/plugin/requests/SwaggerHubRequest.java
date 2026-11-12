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
    private final DefinitionType definitionType;

    /**
     * @return definition format, {@code json} or {@code yaml}
     */
    public String getFormat() {
        return format;
    }

    /**
     * @return the definition content to upload, {@code null} for downloads
     */
    public String getSwagger() {
        return swagger;
    }

    /**
     * @return whether the definition is private in SwaggerHub
     */
    public boolean isPrivate() {
        return isPrivate;
    }

    /**
     * @return whether the request concerns an API or a domain
     */
    public DefinitionType getDefinitionType() {
        return definitionType;
    }

    private SwaggerHubRequest(Builder builder) {
        super(builder.api, builder.owner, builder.version, builder.oas);
        this.format = builder.format;
        this.swagger = builder.swagger;
        this.isPrivate = builder.isPrivate;
        this.definitionType = builder.definitionType;
    }

    /**
     * Builder for {@link SwaggerHubRequest}. Definition type, API, owner and version are mandatory, everything else is optional.
     */
    public static class Builder {
        private final DefinitionType definitionType;
        private final String api;
        private final String owner;
        private final String version;
        private String format;
        private String swagger;
        private boolean isPrivate;
        private String oas;

        /**
         * @param definitionType whether the request concerns an API or a domain
         * @param api            API name
         * @param owner          SwaggerHub owner (user or organization) of the API
         * @param version        API version
         */
        public Builder(DefinitionType definitionType, String api, String owner, String version) {
            this.definitionType = definitionType;
            this.api = api;
            this.owner = owner;
            this.version = version;
        }

        /**
         * @param format definition format, {@code json} or {@code yaml}
         * @return this builder
         */
        public Builder format(String format) {
            this.format = format;
            return this;
        }

        /**
         * @param swagger the definition content to upload
         * @return this builder
         */
        public Builder swagger(String swagger) {
            this.swagger = swagger;
            return this;
        }

        /**
         * @param isPrivate whether the definition is private in SwaggerHub
         * @return this builder
         */
        public Builder isPrivate(boolean isPrivate) {
            this.isPrivate = isPrivate;
            return this;
        }

        /**
         * @param oas OpenAPI/Swagger specification version of the definition
         * @return this builder
         */
        public Builder oas(String oas){
            this.oas = oas;
            return this;
        }

        /**
         * @return the request
         */
        public SwaggerHubRequest build() {
            return new SwaggerHubRequest(this);
        }

    }
}
