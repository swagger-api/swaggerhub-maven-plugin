package io.swagger.swaggerhub.plugin.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.swaggerhub.plugin.requests.dtos.SCMIntegrationPluginConfiguration;

/**
 * Configuration class used to store details required to create a request to SwaggerHub to create an SCM integration plugin.
 * The inner builder class follows the builder pattern which aids building the SaveSCMPluginConfigRequest object.
 * {@link SCMIntegrationPluginConfiguration} represents the object when being sent as part of a request body.
 */
public class SaveSCMPluginConfigRequest {

    /*
    Path Params
     */
    private String apiOwner;
    private String api;
    private String version;
    private String oas;

    /*
    Request body params
     */
    private String scmProvider;
    private String token;
    private String repository;
    private String repositoryOwner;
    private String enabled;
    private String branch;
    private String syncMethod;
    private String outputFile;
    private String target;

    private SaveSCMPluginConfigRequest(Builder builder) {
        this.apiOwner = builder.apiOwner;
        this.api = builder.api;
        this.version = builder.version;
        this.oas = builder.oas;
        this.scmProvider = builder.scmProvider;
        this.token = builder.token;
        this.repository = builder.repository;
        this.repositoryOwner = builder.repositoryOwner;
        this.enabled = builder.enabled;
        this.branch = builder.branch;
        this.syncMethod = builder.syncMethod;
        this.outputFile = builder.outputFile;
        this.target = builder.target;
    }

    public String getApiOwner() {
        return apiOwner;
    }

    public String getApi() {
        return api;
    }

    public String getVersion() {
        return version;
    }

    public String getOas() {
        return oas;
    }

    public String getScmProvider() {
        return scmProvider;
    }

    public String getToken() {
        return token;
    }

    public String getRepository() {
        return repository;
    }

    public String getRepositoryOwner() {
        return repositoryOwner;
    }

    public String getEnabled() {
        return enabled;
    }

    public String getBranch() {
        return branch;
    }

    public String getSyncMethod() {
        return syncMethod;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public String getTarget() {
        return target;
    }

    public String getRequestBody() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new SCMIntegrationPluginConfiguration(this));
    }

    public static class Builder {

        private String apiOwner;
        private String api;
        private String version;
        private String oas;
        private String scmProvider;
        private String token;
        private String repository;
        private String repositoryOwner;
        private String enabled;
        private String branch;
        private String syncMethod;
        private String outputFile;
        private String target;

        public Builder(String apiOwner, String api, String version) {
            this.api = api;
            this.apiOwner = apiOwner;
            this.version = version;
        }

        public SaveSCMPluginConfigRequest.Builder oas(String oas){
            this.oas = oas;
            return this;
        }

        public SaveSCMPluginConfigRequest.Builder scmProvider(String scmProvider){
            this.scmProvider = scmProvider;
            return this;
        }

        public SaveSCMPluginConfigRequest.Builder token(String token){
            this.token = token;
            return this;
        }

        public SaveSCMPluginConfigRequest.Builder repository(String repository){
            this.repository = repository;
            return this;
        }

        public SaveSCMPluginConfigRequest.Builder repositoryOwner(String repositoryOwner){
            this.repositoryOwner = repositoryOwner;
            return this;
        }

        public SaveSCMPluginConfigRequest.Builder enabled(String enabled){
            this.enabled = enabled;
            return this;
        }

        public SaveSCMPluginConfigRequest.Builder branch(String branch){
            this.branch = branch;
            return this;
        }

        public SaveSCMPluginConfigRequest.Builder syncMethod(String syncMethod){
            this.syncMethod = syncMethod;
            return this;
        }

        public SaveSCMPluginConfigRequest.Builder outputFile(String outputFile){
            this.outputFile = outputFile;
            return this;
        }

        public SaveSCMPluginConfigRequest.Builder target(String target){
            this.target = target;
            return this;
        }

        public SaveSCMPluginConfigRequest build() {
            return new SaveSCMPluginConfigRequest(this);
        }

    }
}
