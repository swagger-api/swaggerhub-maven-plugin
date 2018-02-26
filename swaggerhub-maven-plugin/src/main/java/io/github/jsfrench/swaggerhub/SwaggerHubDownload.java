package io.github.jsfrench.swaggerhub;


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
    @Parameter(property = "download.owner", required = true)
    private String owner;
    @Parameter(property = "download.api", required = true)
    private String api;
    @Parameter(property = "download.version", required = true)
    private String version;
    @Parameter(property = "download.format", defaultValue = "json")
    private String format;
    @Parameter(property = "download.host", defaultValue = "api.swaggerhub.com")
    private String host;
    @Parameter(property = "download.token")
    private String token;
    @Parameter(property = "download.outputFile", required = true)
    private String outputFile;


    public void execute() throws MojoExecutionException {
        SwaggerHubClient swaggerHubClient = new SwaggerHubClient(host, token);

        getLog().info("Downloading from " + host
                + ": api-" + api
                + ", owner-" + owner
                + ", version-" + version
                + ", format-" + format
                + ", outputFile-" + outputFile);

        String swaggerJson = swaggerHubClient.getDefinition(owner, api, version, format);
        try {
            Files.write(Paths.get(outputFile), swaggerJson.getBytes(Charset.forName("UTF-8")));
        } catch (IOException e) {
            throw new MojoExecutionException("Failed to download API definition", e);
        }
    }
}