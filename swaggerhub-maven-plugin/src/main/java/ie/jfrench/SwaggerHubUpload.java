package ie.jfrench;


import com.google.gson.Gson;
import io.swagger.client.ApiException;
import io.swagger.client.api.APIsApi;
import io.swagger.util.Json;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.swagger.parser.SwaggerParser;
import io.swagger.models.Swagger;


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

    private APIsApi swaggerHubClient;

    public void execute() throws MojoExecutionException {
        swaggerHubClient = getSwaggerHubClient();

        getLog().info("Uploading to app.swaggerhub.com"
                + ": api-" + api
                + ", owner-" + owner
                + ", version-" + version
                + ", inputFile-" + inputFile);

        try {
            Swagger swagger = new SwaggerParser().read(inputFile);

            String swaggerString = String.valueOf(Files.readAllLines(Paths.get(inputFile)));
//            String swaggerJson = Json.pretty(swagger);
            getLog().info(swaggerString);

//            JsonNode jsonNodeTree = new ObjectMapper().readTree(swaggerString);
//            String swaggerYaml = new YAMLMapper().writeValueAsString(jsonNodeTree);
//            new YAMLMapper().

//            getLog().info(swaggerYaml);

//            getLog().info(jsonNodeTree.toString());

            swaggerHubClient.saveDefinition(owner, api, swaggerString, false, version, false);

        } catch (ApiException | IOException e) {
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