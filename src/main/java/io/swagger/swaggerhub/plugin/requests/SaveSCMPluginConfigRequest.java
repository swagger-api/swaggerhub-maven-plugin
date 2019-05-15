package io.swagger.swaggerhub.plugin.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.swaggerhub.plugin.requests.dtos.SCMIntegrationPluginConfiguration;

/**
 * Configuration class used to store details required to create a request to SwaggerHub to create an SCM integration plugin.
 * The inner builder class follows the builder pattern which aids building the SaveSCMPluginConfigRequest object.
 * {@link SCMIntegrationPluginConfiguration} represents the object when being sent as part of a request body.
 */
public class SaveSCMPluginConfigRequest extends AbstractSwaggerHubRequest {

    /*
    Request body params
     */
    private String scmProvider;
    private String token;
    private String repository;
    private String repositoryOwner;
    private boolean enabled;
    private String branch;
    private String outputFile;
    private String target;
    private String outputFolder;
    private String name;
    private String[] managedPaths;
    private String scmPassword;
    private String scmUsername;
    private String project;
    private String personalAccessToken;
    private String account;
    private String url;
    private String projectCollection;

    private SaveSCMPluginConfigRequest(Builder builder) {
        super(builder.api, builder.apiOwner, builder.version, builder.oas);
        this.scmProvider = builder.scmProvider;
        this.token = builder.token;
        this.repository = builder.repository;
        this.repositoryOwner = builder.repositoryOwner;
        this.enabled = builder.enabled;
        this.branch = builder.branch;
        this.outputFile = builder.outputFile;
        this.target = builder.target;
        this.outputFolder = builder.outputFolder;
        this.name = builder.name;
        this.managedPaths = builder.managedPaths;
        this.scmPassword = builder.scmPassword;
        this.scmUsername = builder.scmUsername;
        this.project = builder.project;
        this.personalAccessToken = builder.personalAccessToken;
        this.account = builder.account;
        this.url = builder.url;
        this.projectCollection = builder.projectCollection;
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

    public boolean getEnabled() {
        return enabled;
    }

    public String getBranch() {
        return branch;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public String getTarget() {
        return target;
    }

    public String getOutputFolder() {
        return outputFolder;
    }

    public String getName() {
        return name;
    }

    public String[] getManagedPaths() {
        return managedPaths;
    }

    public String getScmPassword() { return scmPassword; }

    public String getScmUsername() { return scmUsername; }

    public String getProject() { return project; }

    public String getAccount() { return account; }

    public String getPersonalAccessToken() { return personalAccessToken; }

    public String getUrl() { return url; }

    public String getProjectCollection() { return projectCollection; }

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
        private boolean enabled;
        private String branch;
        private String outputFile;
        private String target;
        private String outputFolder;
        private String name;
        private String[] managedPaths;
        private String scmPassword;
        private String scmUsername;
        private String project;
        private String personalAccessToken;
        private String account;
        private String url;
        private String projectCollection;

        public Builder(String apiOwner, String api, String version) {
            this.api = api;
            this.apiOwner = apiOwner;
            this.version = version;
        }

        public SaveSCMPluginConfigRequest.Builder saveSCMPluginConfigRequest(SaveSCMPluginConfigRequest saveSCMPluginConfigRequest){
            this.oas = saveSCMPluginConfigRequest.getOas();
            this.scmProvider = saveSCMPluginConfigRequest.getScmProvider();
            this.token = saveSCMPluginConfigRequest.getToken();
            this.repository = saveSCMPluginConfigRequest.getRepository();
            this.repositoryOwner = saveSCMPluginConfigRequest.getRepositoryOwner();
            this.enabled = saveSCMPluginConfigRequest.getEnabled();
            this.branch = saveSCMPluginConfigRequest.getBranch();
            this.outputFile = saveSCMPluginConfigRequest.getOutputFile();
            this.target = saveSCMPluginConfigRequest.getTarget();
            this.outputFolder = saveSCMPluginConfigRequest.getOutputFolder();
            this.name = saveSCMPluginConfigRequest.getName();
            this.managedPaths=saveSCMPluginConfigRequest.getManagedPaths();
            this.scmPassword=saveSCMPluginConfigRequest.getScmPassword();
            this.scmUsername=saveSCMPluginConfigRequest.getScmUsername();
            this.project=saveSCMPluginConfigRequest.getProject();
            this.personalAccessToken=saveSCMPluginConfigRequest.getPersonalAccessToken();
            this.account=saveSCMPluginConfigRequest.getAccount();
            this.url = saveSCMPluginConfigRequest.getUrl();
            this.projectCollection = saveSCMPluginConfigRequest.getProjectCollection();
            return this;
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

        public SaveSCMPluginConfigRequest.Builder enabled(boolean enabled){
            this.enabled = enabled;
            return this;
        }

        public SaveSCMPluginConfigRequest.Builder branch(String branch){
            this.branch = branch;
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

        public SaveSCMPluginConfigRequest.Builder outputFolder(String outputFolder){
            this.outputFolder = outputFolder;
            return this;
        }

        public SaveSCMPluginConfigRequest.Builder name(String name){
            this.name = name;
            return this;
        }

        public SaveSCMPluginConfigRequest.Builder managedPaths(String[] managedPaths){
            this.managedPaths = managedPaths;
            return this;
        }

        public SaveSCMPluginConfigRequest.Builder scmUsername(String scmUsername){
            this.scmUsername = scmUsername;
            return this;
        }

        public SaveSCMPluginConfigRequest.Builder scmPassword(String scmPassword){
            this.scmPassword = scmPassword;
            return this;
        }

        public SaveSCMPluginConfigRequest.Builder project(String project){
            this.project = project;
            return this;
        }

        public SaveSCMPluginConfigRequest.Builder personalAccessToken(String personalAccessToken){
            this.personalAccessToken = personalAccessToken;
            return this;
        }

        public SaveSCMPluginConfigRequest.Builder account(String account){
            this.account = account;
            return this;
        }

        public SaveSCMPluginConfigRequest.Builder url(String url){
            this.url = url;
            return this;
        }

        public SaveSCMPluginConfigRequest.Builder projectCollection(String projectCollection){
            this.projectCollection = projectCollection;
            return this;
        }

        public SaveSCMPluginConfigRequest build() {
            return new SaveSCMPluginConfigRequest(this);
        }

    }
}
