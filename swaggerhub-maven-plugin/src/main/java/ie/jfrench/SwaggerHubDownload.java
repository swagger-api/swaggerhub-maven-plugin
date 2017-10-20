package ie.jfrench;


import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.APIsApi;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    private APIsApi swaggerHubClient;

    public void execute() throws MojoExecutionException {
        swaggerHubClient = getSwaggerHubClient();

        getLog().info("Downloading from app.swaggerhub.com: " + api);
        getLog().info("Downloading from app.swaggerhub.com: " + owner);
        getLog().info("Downloading from app.swaggerhub.com: " + version);
        getLog().info("Downloading from app.swaggerhub.com: " + token);

        getLog().info(swaggerHubClient.getApiClient().getBasePath());

        Object swagger = null;
        try {
            swagger = swaggerHubClient.getDefinition(owner, api, version);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        getLog().info(swagger.toString());

        Path path = Paths.get(outputFile);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(swagger.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private APIsApi getSwaggerHubClient() {
        final APIsApi apiClient = new APIsApi();

        apiClient.getApiClient().setBasePath("https://api.swaggerhub.com");
        apiClient.getApiClient().addDefaultHeader("Authorization", token);
        return apiClient;
    }
}