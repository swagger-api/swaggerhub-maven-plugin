package ie.jfrench;


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
    @Parameter(property = "upload.owner")
    private String owner;
    @Parameter(property = "upload.api")
    private String api;
    @Parameter(property = "upload.version")
    private String version;
    @Parameter(property = "upload.token")
    private String token;
    @Parameter(property = "upload.inputFile")
    private String inputFile;

    private SwaggerHubClient swaggerHubClient;

    public void execute() throws MojoExecutionException {
        swaggerHubClient = new SwaggerHubClient(token);

        getLog().info("Uploading to app.swaggerhub.com"
                + ": api-" + api
                + ", owner-" + owner
                + ", version-" + version
                + ", inputFile-" + inputFile);

        try {
            String content = new String(Files.readAllBytes(Paths.get(inputFile)), Charset.forName("UTF-8"));
            swaggerHubClient.saveDefinition(owner, api, version, content);
        } catch (IOException e) {
            getLog().error(e);
            throw new MojoExecutionException("Failed to upload API definition", e);
        }
    }
}