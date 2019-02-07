package io.swagger.swaggerhub.plugin;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.function.Consumer;

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
    @Parameter(property = "upload.token")
    private String token;
    @Parameter(property = "upload.inputFile")
    private String inputFile;
    @Parameter(property = "upload.isPrivate", defaultValue = "false")
    private Boolean isPrivate;
    @Parameter(property = "upload.definitionDirectory")
    private String definitionDirectory;
    @Parameter(property = "upload.definitionFileNameRegex")
    private String definitionFileNameRegex;
    @Parameter(property = "upload.uploadType")
    private String uploadType;

    private SwaggerHubClient swaggerHubClient;

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
        definitionUploadType.orElseThrow(() -> new MojoExecutionException(String.format("Unknown uploadType [%s] specified. Supported types are inputFile or directory.", uploadType)));

        try {
            if(definitionUploadType.get().equals(DefinitionUploadType.INPUT_FILE)) {
                String content = new String(Files.readAllBytes(Paths.get(inputFile)), Charset.forName(UTF_8));
                String oasVersion = DefinitionParserService.getOASVersion(DefinitionFileFormat.valueOf(format.toUpperCase()).getMapper().readTree(content));

                SwaggerHubRequest swaggerHubRequest = new SwaggerHubRequest.Builder(api, owner, version)
                        .swagger(content)
                        .format(format)
                        .isPrivate(isPrivate)
                        .oas(oasVersion)
                        .build();

                swaggerHubClient.saveDefinition(swaggerHubRequest);

            }else if(definitionUploadType.get().equals(DefinitionUploadType.DIRECTORY)) {
                DefinitionFileFinder.findDefinitionFiles(definitionDirectory, Optional.ofNullable(definitionFileNameRegex))
                        .stream()
                        .forEach(uploadFileDefinitionFromDirectory());
            }

        } catch (IOException | DefinitionParsingException e) {
            getLog().error(e);
            throw new MojoExecutionException("Failed to upload API definition", e);
        }
    }

    private Consumer<File> uploadFileDefinitionFromDirectory(){
        return file -> {
            try {
                SwaggerHubRequest swaggerHubRequest = createSwaggerHubRequest(file);
                getLog().info(String.format("Uploading API definition file [%s]. API name [%s]",file.getName(), swaggerHubRequest.getApi()));
                swaggerHubClient.saveDefinition(swaggerHubRequest);
            } catch (MojoExecutionException | IOException | DefinitionParsingException e ) {
                getLog().error(String.format("Error when attempting to upload API definition [%s]", file.getName()), e);
            }
        };
    }

    private SwaggerHubRequest createSwaggerHubRequest(File file) throws IOException, DefinitionParsingException {

        DefinitionFileFormat definitionFileFormat = DefinitionFileFormat.getByFileExtensionType(FilenameUtils.getExtension(file.getName())).get();
        ObjectMapper mapper = definitionFileFormat.getMapper();
        String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), Charset.forName(UTF_8));
        String api = DefinitionParserService.getApiId(mapper.readTree(content));
        String oasVersion = DefinitionParserService.getOASVersion(mapper.readTree(content));
        String version = DefinitionParserService.getVersion(mapper.readTree(content));
        SwaggerHubRequest swaggerHubRequest = new SwaggerHubRequest.Builder(api, owner, version)
                .swagger(content)
                .format(definitionFileFormat.getFileFormat())
                .isPrivate(isPrivate)
                .oas(oasVersion)
                .build();

        return swaggerHubRequest;
    }
}