package io.swagger.swaggerhub.plugin.requests.dtos;

import io.swagger.swaggerhub.plugin.requests.SaveSCMPluginConfigRequest;

/**
 * Data transfer object used to send a potential SCM plugin configuration as a request body on a POST/PUT request
 */
public class SCMIntegrationPluginConfiguration {

    /**
     * Required due to Swagger 2.0's polymorphism support. Config type must be set to the name of the Model which the receiving service will marshall the request body to.
     */
    private final String configType = "SCMIntegrationPluginConfiguration";

    private String token;
    private String branch;
    private String owner;
    private String repository;
    private String outputFile;
    private String target;
    private String syncMethod;
    private String enabled;

    public SCMIntegrationPluginConfiguration(SaveSCMPluginConfigRequest configRequest){
        this.token = configRequest.getToken();
        this.branch = configRequest.getBranch();
        this.owner = configRequest.getRepositoryOwner();
        this.repository = configRequest.getRepository();
        this.outputFile = configRequest.getOutputFile();
        this.target = configRequest.getTarget();
        this.syncMethod = configRequest.getSyncMethod();
        this.enabled = configRequest.getEnabled();
    }

    public String getConfigType() {
        return configType;
    }

    public String getToken() {
        return token;
    }

    public String getBranch() {
        return branch;
    }

    public String getOwner() {
        return owner;
    }

    public String getRepository() {
        return repository;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public String getTarget() { return target; }

    public String getSyncMethod() {
        return syncMethod;
    }

    public String getEnabled() {
        return enabled;
    }
}