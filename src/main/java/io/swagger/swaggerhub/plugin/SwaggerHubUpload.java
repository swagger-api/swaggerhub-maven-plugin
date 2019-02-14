package io.swagger.swaggerhub.plugin;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.swaggerhub.interfaces.ExceptionThrowingConsumer;
import io.swagger.swaggerhub.plugin.exceptions.DefinitionParsingException;
import io.swagger.swaggerhub.plugin.services.DefinitionFileFinder;
import io.swagger.swaggerhub.plugin.services.DefinitionFileFormat;
import io.swagger.swaggerhub.plugin.services.DefinitionParserService;
import org.apache.commons.io.FilenameUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Uploads API definition to SwaggerHub
 */
@Mojo(name = "upload")
public class SwaggerHubUpload extends AbstractMojo {

    private static final String UTF_8 = "UTF-8";

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

    private SwaggerHubClient swaggerHubClient;

    @Override
    public void execute() throws MojoExecutionException {

        swaggerHubClient = new SwaggerHubClient(host, port, protocol, token);

        getLog().info("Uploading to " + host
                + ": api: " + api
                + ", owner: " + owner
                + ", version: " + version
                + ", inputFile: " + inputFile
                + ", format: " + format
                + ", isPrivate: " + isPrivate
                + ", definitionDirectory: " + definitionDirectory
                + ", definitionFileNameRegex: " + definitionFileNameRegex
                + ", uploadType: " + uploadType);

        Optional<DefinitionUploadType> definitionUploadType = DefinitionUploadType.getByParamValue(uploadType);
        definitionUploadType.ifPresent(ExceptionThrowingConsumer.RuntimeThrowingConsumerWrapper(type -> {
            executeUpload(type, inputFile, format, owner, isPrivate, api, version, definitionDirectory, definitionFileNameRegex);
        }));

        definitionUploadType.orElseThrow(() -> new MojoExecutionException(String.format("Unknown uploadType [%s] specified. Supported types are inputFile and directory.", uploadType)));
    }

    private void executeUpload(DefinitionUploadType definitionUploadType, String inputFile, String format, String owner,
                               Boolean isPrivate, String api, String version, String definitionDirectory, String definitionFileNameRegex) throws MojoExecutionException {

        switch (definitionUploadType){
            case INPUT_FILE:
                executeInputFileBasedUpload(inputFile, format, owner, isPrivate, api, version);
                break;
            case DIRECTORY:
                executeDirectoryBasedUpload(definitionDirectory, definitionFileNameRegex, owner, isPrivate);
                break;
        }
    }

    private void executeInputFileBasedUpload(String inputFile, String format, String owner, Boolean isPrivate, String api, String version) throws MojoExecutionException {

        getLog().info(String.format("Uploading API name [%s]", api));
        try {
            String content = new String(Files.readAllBytes(Paths.get(inputFile)), Charset.forName(UTF_8));
            String oasVersion = DefinitionParserService.getOASVersion(DefinitionFileFormat.valueOf(format.toUpperCase()).getMapper().readTree(content));
            SwaggerHubRequest swaggerHubRequest = createSwaggerHubRequest(content, owner, isPrivate, api, version, oasVersion, DefinitionFileFormat.getByFileExtensionType(format).get());
            swaggerHubClient.saveDefinition(swaggerHubRequest);
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
                        getLog().info(String.format("Uploading API definition file [%s]. API name [%s]",file.getName(), swaggerHubRequest.getApi()));
                        swaggerHubClient.saveDefinition(swaggerHubRequest);
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

        SwaggerHubRequest swaggerHubRequest = new SwaggerHubRequest.Builder(api, owner, version)
                .swagger(fileContent)
                .format(definitionFileFormat.getFileFormat())
                .isPrivate(isPrivate)
                .oas(oasVersion)
                .build();

        return swaggerHubRequest;
    }


}