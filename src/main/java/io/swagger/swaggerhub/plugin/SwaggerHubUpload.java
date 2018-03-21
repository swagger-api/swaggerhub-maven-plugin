package io.swagger.swaggerhub.plugin;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;



/**
 * Uploads API definition to SwaggerHub
 */
@Mojo(name = "upload")
public class SwaggerHubUpload extends AbstractMojo {
    @Parameter(property = "upload.owner", required = true)
    private String owner;
    @Parameter(property = "upload.api", required = true)
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
    @Parameter(property = "upload.inputFile", required = true)
    private String inputFile;
    @Parameter(property = "upload.isPrivate", defaultValue = "false")
    private Boolean isPrivate;
    @Parameter(property = "upload.force", defaultValue = "false")
    private Boolean force;

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
                + ", force: " + force);

        try {
            String content = new String(Files.readAllBytes(Paths.get(inputFile)), Charset.forName("UTF-8"));

            SwaggerHubRequest swaggerHubRequest = new SwaggerHubRequest.Builder(api, owner, version)
                    .swagger(content)
                    .format(format)
                    .isPrivate(isPrivate)
                    .force(force)
                    .build();

            swaggerHubClient.saveDefinition(swaggerHubRequest);
        } catch (IOException e) {
            getLog().error(e);
            throw new MojoExecutionException("Failed to upload API definition", e);
        }
    }
}