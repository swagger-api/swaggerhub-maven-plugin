package io.swagger.swaggerhub.plugin.requests.dtos;

import io.swagger.swaggerhub.plugin.requests.SaveSCMPluginConfigRequest;

import java.util.Arrays;


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
    private final String syncMethod = "Advanced Sync";
    private boolean enabled;
    private String outputFolder;
    /*
    The following 3 members must match the following format
    providedPaths and ignoredPaths must be an empty list. managedPaths should contain only 1 item, the name of the file itself

    This is the format required by the SwaggerHub Plugin framework.
    By matching this, any SCM integration plugins will only overwrite/update the filename in the managedPaths array.
    Other files in the outputFolder will be untouched.
    Getters have been created for these final members, this is to allow correct marshalling by the underlying Jackson framework on the SwaggerHub backend.
    The plugin framework requires an empty list for these members instead of null members.
     */
    private final String[] providedPaths = {};
    private final String[] ignoredPaths = {};
    private String[] managedPaths = {};
    private String name;


    public SCMIntegrationPluginConfiguration(SaveSCMPluginConfigRequest configRequest){
        this.token = configRequest.getToken();
        this.branch = configRequest.getBranch();
        this.owner = configRequest.getRepositoryOwner();
        this.repository = configRequest.getRepository();
        this.outputFile = configRequest.getOutputFile();
        this.target = configRequest.getTarget();
        this.enabled = configRequest.getEnabled();
        this.outputFolder = configRequest.getOutputFolder();
        this.name = configRequest.getName();
        this.managedPaths = configRequest.getManagedPaths();
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

    public boolean getEnabled() {
        return enabled;
    }

    public String getOutputFolder() {
        return outputFolder;
    }

    public String[] getProvidedPaths() {
        return providedPaths;
    }

    public String[] getIgnoredPaths() {
        return ignoredPaths;
    }

    public String[] getManagedPaths() {
        return managedPaths;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "SCMIntegrationPluginConfiguration{" +
                "configType='" + configType + '\'' +
                ", token='" + (null !=token ? token.replace(".", "*"):"") + '\'' +
                ", branch='" + branch + '\'' +
                ", owner='" + owner + '\'' +
                ", repository='" + repository + '\'' +
                ", outputFile='" + outputFile + '\'' +
                ", target='" + target + '\'' +
                ", syncMethod='" + syncMethod + '\'' +
                ", enabled=" + enabled +
                ", outputFolder='" + outputFolder + '\'' +
                ", providedPaths=" + Arrays.toString(providedPaths) +
                ", ignoredPaths=" + Arrays.toString(ignoredPaths) +
                ", managedPaths=" + Arrays.toString(managedPaths) +
                ", name='" + name + '\'' +
                '}';
    }
}