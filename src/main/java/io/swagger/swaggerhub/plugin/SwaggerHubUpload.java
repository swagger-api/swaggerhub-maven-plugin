package io.swagger.swaggerhub.plugin;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.swaggerhub.interfaces.ExceptionThrowingConsumer;
import io.swagger.swaggerhub.plugin.exceptions.DefinitionParsingException;
import io.swagger.swaggerhub.plugin.exceptions.UploadParametersException;
import io.swagger.swaggerhub.plugin.requests.SaveSCMPluginConfigRequest;
import io.swagger.swaggerhub.plugin.requests.SwaggerHubRequest;
import io.swagger.swaggerhub.plugin.requests.dtos.SCMIntegrationPluginConfiguration;
import io.swagger.swaggerhub.plugin.services.DefinitionFileFinder;
import io.swagger.swaggerhub.plugin.services.DefinitionFileFormat;
import io.swagger.swaggerhub.plugin.services.DefinitionParserService;
import io.swagger.swaggerhub.plugin.services.StringModificationService;
import okhttp3.Response;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.settings.Settings;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Uploads API definition to SwaggerHub. Can also be configured to create SCM integration plugins on upload
 */
@Mojo(name = "upload")
public class SwaggerHubUpload extends AbstractMojo {

    private static final String UTF_8 = "UTF-8";
    private static final String REPOSITORY_LOCATION = System.getProperty("user.dir");
    private static final String SWAGGERHUB_PLUGIN_CONFIGURATION_NAME = "SwaggerHub SCM Bidirectional Plugin";

    @Parameter(property = "upload.owner", required = true)
    private String owner;
    @Parameter(property = "upload.api")
    private String api;
    @Parameter(property = "upload.version")
    private String version;
    @Parameter(property = "upload.host", defaultValue = "api.swaggerhub.com")
    private String host;
    @Parameter(property = "upload.port", defaultValue = "443")
    private int port;
    @Parameter(property = "upload.protocol", defaultValue = "https")
    private String protocol;
    @Parameter(property = "upload.format", defaultValue = "json")
    private String format;
    @Parameter(property = "upload.token", required = true)
    private String token;
    @Parameter(property = "download.definitionType", defaultValue = "API")
    private String definitionType;
    @Parameter(property = "upload.inputFile")
    private String inputFile;
    @Parameter(property = "upload.isPrivate", defaultValue = "false")
    private Boolean isPrivate;
    @Parameter(property = "upload.definitionDirectory")
    private String definitionDirectory;
    @Parameter(property = "upload.definitionFileNameRegex")
    private String definitionFileNameRegex;
    @Parameter(property = "upload.uploadType", required = true)
    private String uploadType;
    @Parameter(property = "upload.basepath")
    private String basepath;
    /*
    SCM related parameters
     */
    @Parameter(property = "upload.scmProvider")
    private String scmProvider;

    @Parameter(property = "upload.scmToken")
    private String scmToken;

    @Parameter(property = "upload.repository")
    private String repository;

    @Parameter(property = "upload.repositoryOwner")
    private String repositoryOwner;

    @Parameter(property = "upload.branch", defaultValue = "SWAGGERHUB")
    private String branch;

    @Parameter(property = "upload.enableScmIntegration", defaultValue = "true")
    private Boolean enableScmIntegration;

    @Parameter(property = "upload.skipFailures", defaultValue = "false")
    private Boolean skipFailures;

    @Parameter(property = "upload.scmUsername")
    private String scmUsername;

    @Parameter(property = "upload.scmPassword")
    private String scmPassword;

    @Parameter(property = "upload.scmProject")
    private String scmProject;

    @Parameter(property = "upload.scmOrganization")
    private String scmOrganization;

    @Parameter(property = "upload.scmPersonalAccessToken")
    private String scmPersonalAccessToken;

    @Parameter(property = "upload.scmUrl")
    private String scmUrl;

    @Parameter(property = "upload.scmProjectCollection")
    private String scmProjectCollection;

    @Parameter(property = "upload.scmHost")
    private String scmHost;

    @Parameter( defaultValue = "${settings}", readonly = true )
    private Settings settings;

    private SwaggerHubClient swaggerHubClient;

    @Override
    public void execute() throws MojoExecutionException {

        swaggerHubClient = new SwaggerHubClient(host, port, protocol, token, getLog(), basepath, settings.getActiveProxy());

        getLog().info("Uploading to " + host
                + ", basepath: " + basepath
                + ", definitionType: " + DefinitionType.getByParamValue(definitionType).getFriendlyName()
                + ", api: " + api
                + ", owner: " + owner
                + ", version: " + version
                + ", inputFile: " + inputFile
                + ", format: " + format
                + ", isPrivate: " + isPrivate
                + ", definitionDirectory: " + definitionDirectory
                + ", definitionFileNameRegex: " + definitionFileNameRegex
                + ", uploadType: " + uploadType
                + ", skipFailures: " + skipFailures
                + ", token: " + StringModificationService.obfuscateSensitiveString(token, "*"));


        /*
        Verify that the upload type has been set and that the fields required for that upload type are available.
        If the upload type is unrecognised or the the required fields aren't set, stop the upload
         */
        Optional<DefinitionUploadType> definitionUploadType = DefinitionUploadType.getByParamValue(uploadType);
        definitionUploadType.orElseThrow(() -> new UploadParametersException(String.format("Unknown uploadType [%s] specified. Supported types are inputFile and directory.", uploadType)));

        List<String> requiredEmptyUploadFields = returnEmptyRequiredFields(definitionUploadType.get().getRequiredFields(), this);
        if(!requiredEmptyUploadFields.isEmpty()){
            throw new UploadParametersException(String.format("The following required fields aren't set for %s upload: %s", uploadType,
                    StringUtils.join(returnEmptyRequiredFields(definitionUploadType.get().getRequiredFields(), this),", ")));
        }

        definitionUploadType.ifPresent(ExceptionThrowingConsumer.RuntimeThrowingConsumerWrapper(type -> {
            executeUpload(type, inputFile, format, owner, isPrivate, api, version, definitionDirectory, definitionFileNameRegex);
        }));

        if(StringUtils.isNotEmpty(scmProvider)){

            getLog().info("Creating SCM integration using the following.."
                    + " scmProvider: " + scmProvider
                    + ", scmToken: " + StringModificationService.obfuscateSensitiveString(scmToken, "*")
                    + ", repository: " + repository
                    + ", repositoryOwner: " + repositoryOwner
                    + ", branch: " + branch
                    + ", enableScmIntegration: " + enableScmIntegration
                    + ", scmUsername: " + scmUsername
                    + ", scmPassword: " + StringModificationService.obfuscateSensitiveString(scmPassword, "*")
                    + ", scmOrganization: " + scmOrganization
                    + ", scmPersonalAccessToken: " + StringModificationService.obfuscateSensitiveString(scmPersonalAccessToken, "*")
                    + ", scmUrl: " + scmUrl
                    + ", scmHost: " + scmHost
                    + ", scmProject: " + scmProject);

            //Preemptive setup
            SaveSCMPluginConfigRequest saveSCMPluginConfigRequest = new SaveSCMPluginConfigRequest.Builder(owner, api, version)
                    .scmProvider(scmProvider)
                    .branch(branch)
                    .enabled(enableScmIntegration)
                    .repositoryOwner(repositoryOwner)
                    .repository(repository)
                    .token(scmToken)
                    .scmPassword(scmPassword)
                    .scmUsername(scmUsername)
                    .name(SWAGGERHUB_PLUGIN_CONFIGURATION_NAME)
                    .project(scmProject)
                    .personalAccessToken(scmPersonalAccessToken)
                    .account(scmOrganization)
                    .url(scmUrl)
                    .host(scmHost)
                    .projectCollection(scmProjectCollection)
                    .build();

            definitionUploadType.ifPresent(ExceptionThrowingConsumer.RuntimeThrowingConsumerWrapper(type -> {
                executeSCMIntegrationPluginCreation(saveSCMPluginConfigRequest, inputFile, format, definitionDirectory, definitionFileNameRegex, type);
            }));
        }
    }

    private void executeUpload(DefinitionUploadType definitionUploadType, String inputFile, String format, String owner,
                               Boolean isPrivate, String api, String version, String definitionDirectory, String definitionFileNameRegex) throws MojoExecutionException {

        switch (definitionUploadType){
            case INPUT_FILE:
                getLog().debug("Executing input file based upload...");
                executeInputFileBasedUpload(inputFile, format, owner, isPrivate, api, version);
                break;
            case DIRECTORY:
                getLog().debug("Executing definition directory based upload...");
                executeDirectoryBasedUpload(definitionDirectory, definitionFileNameRegex, owner, isPrivate);
                break;
        }
    }

    private void executeInputFileBasedUpload(String inputFile, String format, String owner, Boolean isPrivate, String api, String version) throws MojoExecutionException {

        getLog().info(String.format("Uploading %s name %s version %s", DefinitionType.getByParamValue(definitionType).getFriendlyName(), api, version));
        try {
            String content = new String(Files.readAllBytes(Paths.get(inputFile)), Charset.forName(UTF_8));
            String oasVersion = DefinitionParserService.getOASVersion(DefinitionFileFormat.valueOf(format.toUpperCase()).getMapper().readTree(content));
            SwaggerHubRequest swaggerHubRequest = createSwaggerHubRequest(content, owner, isPrivate, api, version, oasVersion, DefinitionFileFormat.getByFileExtensionType(format).get());
            swaggerHubClient.saveDefinition(swaggerHubRequest)
                    .filter(shouldErrorFailBuild(skipFailures))
                    .orElseThrow(returnMojoExceptionForBuildFailure(String.format("Error when attempting to save API %s .", swaggerHubRequest.getApi())));
        } catch (DefinitionParsingException | IOException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }

    private void executeDirectoryBasedUpload(String definitionDirectory, String definitionFileNameRegex, String owner, Boolean isPrivate) throws MojoExecutionException {

        try {
            DefinitionFileFinder.findDefinitionFiles(definitionDirectory, Optional.ofNullable(definitionFileNameRegex))
                    .stream()
                    .forEach(ExceptionThrowingConsumer.RuntimeThrowingConsumerWrapper(file -> {
                        SwaggerHubRequest swaggerHubRequest = createSwaggerHubRequest(file, owner, isPrivate);
                        getLog().info(String.format("Uploading %s definition file %s. %s name %s version %s",
                                DefinitionType.getByParamValue(definitionType).getFriendlyName(), DefinitionType.getByParamValue(definitionType).getFriendlyName(),
                                file.getName(), swaggerHubRequest.getApi(), swaggerHubRequest.getVersion()));
                        swaggerHubClient.saveDefinition(swaggerHubRequest)
                                .filter(shouldErrorFailBuild(skipFailures))
                                .orElseThrow(returnMojoExceptionForBuildFailure(String.format("Error when attempting to save API %s.", swaggerHubRequest.getApi())));
                    }));
        } catch (IOException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }

    private SwaggerHubRequest createSwaggerHubRequest(File file, String owner, Boolean isPrivate) throws IOException, DefinitionParsingException {
        DefinitionFileFormat definitionFileFormat = DefinitionFileFormat.getByFileExtensionType(FilenameUtils.getExtension(file.getName())).get();
        ObjectMapper mapper = definitionFileFormat.getMapper();
        String fileContent = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), Charset.forName(UTF_8));
        String api = DefinitionParserService.getApiId(mapper.readTree(fileContent));
        String oasVersion = DefinitionParserService.getOASVersion(mapper.readTree(fileContent));
        String version = DefinitionParserService.getVersion(mapper.readTree(fileContent));

        return createSwaggerHubRequest(fileContent, owner, isPrivate, api, version, oasVersion, definitionFileFormat);
    }


    private SwaggerHubRequest createSwaggerHubRequest(String fileContent, String owner, Boolean isPrivate, String api, String version, String oasVersion,
                                                      DefinitionFileFormat definitionFileFormat){

        SwaggerHubRequest swaggerHubRequest = new SwaggerHubRequest.Builder(
                DefinitionType.getByParamValue(definitionType), api, owner, version)
                .swagger(fileContent)
                .format(definitionFileFormat.getFileFormat())
                .isPrivate(isPrivate)
                .oas(oasVersion)
                .build();

        return swaggerHubRequest;
    }


    private void executeSCMIntegrationPluginCreation(SaveSCMPluginConfigRequest saveSCMPluginConfigRequest, String inputFile, String inputFileFormat, String definitionDirectory, String definitionFileNameRegex, DefinitionUploadType definitionUploadType) throws MojoExecutionException {

        switch (definitionUploadType){
            case INPUT_FILE:
                executeInputFileBasedSCMPluginCreation(saveSCMPluginConfigRequest, inputFile, inputFileFormat);
                break;
            case DIRECTORY:
                executeDirectoryBasedSCMPluginCreation(saveSCMPluginConfigRequest, definitionFileNameRegex, definitionDirectory);
                break;
        }
    }

    private void executeInputFileBasedSCMPluginCreation(SaveSCMPluginConfigRequest saveSCMPluginConfigRequestInput, String inputFile, String format) throws MojoExecutionException {

        try {
            String content = new String(Files.readAllBytes(Paths.get(inputFile)), Charset.forName(UTF_8));
            String oasVersion = DefinitionParserService.getOASVersion(DefinitionFileFormat.valueOf(format.toUpperCase()).getMapper().readTree(content));
            String languageTarget = DefinitionFileFormat.valueOf(format.toUpperCase()).getLanguageTarget();
            SaveSCMPluginConfigRequest saveSCMPluginConfigRequest = createSaveSCMPluginConfigRequest(saveSCMPluginConfigRequestInput, inputFile, oasVersion, languageTarget);

            logSaveSCMPluginConfigRequestDetailsPriorToRequest(saveSCMPluginConfigRequest);

            swaggerHubClient.saveIntegrationPluginOfType(saveSCMPluginConfigRequest)
                    .filter(shouldErrorFailBuild(skipFailures))
                    .orElseThrow(returnMojoExceptionForBuildFailure("Error when attempting to save plugin integration."));

        }catch (DefinitionParsingException | IOException e){
            throw new MojoExecutionException(e.getMessage(), e);
        }

    }

    private void executeDirectoryBasedSCMPluginCreation(SaveSCMPluginConfigRequest scmPluginConfigRequest, String definitionFileNameRegex, String definitionDirectory)  throws MojoExecutionException {

        try {
            DefinitionFileFinder.findDefinitionFiles(definitionDirectory, Optional.ofNullable(definitionFileNameRegex))
                    .stream()
                    .forEach(ExceptionThrowingConsumer.RuntimeThrowingConsumerWrapper(file -> {
                        SaveSCMPluginConfigRequest saveSCMPluginConfigRequest = createSaveSCMPluginConfigRequest(scmPluginConfigRequest, file);
                        logSaveSCMPluginConfigRequestDetailsPriorToRequest(saveSCMPluginConfigRequest);
                        swaggerHubClient.saveIntegrationPluginOfType(saveSCMPluginConfigRequest)
                                .filter(shouldErrorFailBuild(skipFailures))
                                .orElseThrow( returnMojoExceptionForBuildFailure("Error when attempting to save plugin integration."));
                    }));
        } catch (IOException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }

    }


    private SaveSCMPluginConfigRequest createSaveSCMPluginConfigRequest(SaveSCMPluginConfigRequest input, File file) throws IOException, DefinitionParsingException {

        DefinitionFileFormat definitionFileFormat = DefinitionFileFormat.getByFileExtensionType(FilenameUtils.getExtension(file.getName())).get();
        ObjectMapper mapper = definitionFileFormat.getMapper();
        String fileContent = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), Charset.forName(UTF_8));
        String api = DefinitionParserService.getApiId(mapper.readTree(fileContent));
        String oasVersion = DefinitionParserService.getOASVersion(mapper.readTree(fileContent));
        String version = DefinitionParserService.getVersion(mapper.readTree(fileContent));
        String outputFolder = getOutputFolder(file.getPath());

        return new SaveSCMPluginConfigRequest.Builder(input.getOwner(), api, version)
                .saveSCMPluginConfigRequest(input)
                .oas(oasVersion)
                .outputFolder(outputFolder)
                .managedPaths(new String[]{file.getName()})
                .outputFile(file.getName())
                .target(definitionFileFormat.getLanguageTarget())
                .build();
    }

    /**
     * This version of createSaveSCMPluginConfigRequest is for input file based upload
     * @param input
     * @param oasVersion
     * @return
     */
    private SaveSCMPluginConfigRequest createSaveSCMPluginConfigRequest(SaveSCMPluginConfigRequest input, String inputFile, String oasVersion, String languageTarget){

        String outputFolder = FilenameUtils.getFullPath(inputFile);
        outputFolder = getOutputFolder(outputFolder);

        SaveSCMPluginConfigRequest saveSCMPluginConfigRequest = new SaveSCMPluginConfigRequest.Builder(input.getOwner(), input.getApi(), input.getVersion())
                .saveSCMPluginConfigRequest(input)
                .oas(oasVersion)
                .target(languageTarget)
                .outputFolder(outputFolder)
                .managedPaths(new String[]{FilenameUtils.getName(inputFile)})
                .outputFile(FilenameUtils.getName(inputFile))
                .build();

        return saveSCMPluginConfigRequest;
    }

    /**
     * Used to format the output folder as SwaggerHub expects it.
     * @param path
     * @return
     */
    private String getOutputFolder(String path){

        //Remove the portion of the path which is prior to our repositories file path
        path = StringUtils.removeStart(FilenameUtils.getFullPath(path), REPOSITORY_LOCATION);
        //Return the path without leading and ending / and ensures the file path uses forward slashes instead of backslashes
        return StringUtils.strip(StringUtils.strip(path,"/"),"\\").replace("\\", "/");
    }

    /**
     * Check the object and return a list of fields that are empty from the list of requiredFields
     * @param requiredFields
     * @param givenObject
     * @return
     */
    private List<String> returnEmptyRequiredFields(List<String> requiredFields, Object givenObject){
        return requiredFields.stream()
                .filter(x -> {
                    try {
                        return StringUtils.isEmpty((String) givenObject.getClass().getDeclaredField(x).get(givenObject));
                    } catch (Exception e) {
                        //Not going to try and scmOrganization for exceptions; if there is an exception we probably have greater issues than an empty/null field
                        getLog().debug(String.format("Unable to ascertain if %s is null/empty",x));
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }

    /**
     * Log out request details prior to making a request to SwaggerHub to save a SCMPluginConfig
     * @param saveSCMPluginConfigRequest
     */
    private void logSaveSCMPluginConfigRequestDetailsPriorToRequest(SaveSCMPluginConfigRequest saveSCMPluginConfigRequest){
        getLog().info(String.format("Provisioning %s Plugin Integration for %s", scmProvider, saveSCMPluginConfigRequest.getApi()));
        getLog().debug(new SCMIntegrationPluginConfiguration(saveSCMPluginConfigRequest).toString());
    }

    /**
     * Returns a function that will return if a build should fail due to user configuration and the response to a request made
     * @return
     */
    private Predicate<Response> shouldErrorFailBuild(boolean skipFailures){
        return  x -> skipFailures || x.isSuccessful();
    }

    private Supplier<MojoExecutionException> returnMojoExceptionForBuildFailure(String errorMessage){
        return () -> new MojoExecutionException(errorMessage + " Build unable to continue.");
    }

}
