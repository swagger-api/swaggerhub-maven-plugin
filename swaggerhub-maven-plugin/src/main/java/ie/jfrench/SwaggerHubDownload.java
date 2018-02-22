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
 * Downloads API definition from SwaggerHub
 */
@Mojo(name = "download")
public class SwaggerHubDownload extends AbstractMojo {
    @Parameter(property = "download.owner")
    private String owner;
    @Parameter(property = "download.api")
    private String api;
    @Parameter(property = "download.version")
    private String version;
    @Parameter(property = "download.token")
    private String token;
    @Parameter(property = "download.outputFile")
    private String outputFile;


    public void execute() throws MojoExecutionException {
        SwaggerHubClient swaggerHubClient = new SwaggerHubClient(token);

        getLog().info("Downloading from app.swaggerhub.com"
                + ": api-" + api
                + ", owner-" + owner
                + ", version-" + version
                + ", outputFile-" + outputFile);

        String swaggerJson = swaggerHubClient.getDefinition(owner, api, version);
        try {
            Files.write(Paths.get(outputFile), swaggerJson.getBytes(Charset.forName("UTF-8")));
        } catch (IOException e) {
            throw new MojoExecutionException("Failed to download API definition", e);
        }
    }
}