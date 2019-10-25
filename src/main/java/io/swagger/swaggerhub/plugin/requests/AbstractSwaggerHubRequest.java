package io.swagger.swaggerhub.plugin.requests;

import io.swagger.swaggerhub.plugin.DefinitionType;

/**
 * Abstract class which contains common members shared by SwaggerHub request configuration classes
 */
public class AbstractSwaggerHubRequest {

    protected final DefinitionType definitionType;
    protected final String api;
    protected final String owner;
    protected final String version;
    protected final String oas;

    public AbstractSwaggerHubRequest(DefinitionType definitionType, String api, String owner, String version, String oas) {
        this.definitionType = definitionType;
        this.api = api;
        this.owner = owner;
        this.version = version;
        this.oas = oas;
    }

    public DefinitionType getDefinitionType() {
        return definitionType;
    }

    public String getApi() { return api; }

    public String getOwner() {
        return owner;
    }

    public String getVersion() {
        return version;
    }

    public String getOas() { return oas; }
}
