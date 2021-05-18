package io.swagger.swaggerhub.plugin;


import io.swagger.swaggerhub.plugin.requests.SwaggerHubRequest;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.settings.Proxy;
import org.apache.maven.settings.Settings;

import java.io.File;
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
    @Parameter(property = "download.port", defaultValue = "443")
    private int port;
    @Parameter(property = "download.protocol", defaultValue = "https")
    private String protocol;
    @Parameter(property = "download.token")
    private String token;
    @Parameter(property = "download.definitionType", defaultValue = "API")
    private String definitionType;
    @Parameter(property = "download.outputFile", required = true)
    private String outputFile;
    @Parameter(property = "download.basepath")
    private String basepath;

    @Parameter( defaultValue = "${settings}", readonly = true )
    private Settings settings;


    public void execute() throws MojoExecutionException {
        SwaggerHubClient swaggerHubClient = new SwaggerHubClient(host, port, protocol, token, getLog(), basepath, settings.getActiveProxy());
        getLog().info("Downloading from " + host
                + ": basepath-" + basepath
                + ": api-" + api
                + ", owner-" + owner
                + ", version-" + version
                + ", format-" + format
                + ", outputFile-" + outputFile);

        SwaggerHubRequest swaggerHubRequest = new SwaggerHubRequest.Builder(
                DefinitionType.getByParamValue(definitionType), api, owner, version)
                .format(format)
                .build();

        String swaggerJson = swaggerHubClient.getDefinition(swaggerHubRequest);
        try {
            File file = new File(outputFile);

            final File parentFile = file.getParentFile();
            if (parentFile != null) {
                parentFile.mkdirs();
            }
            Files.write(Paths.get(outputFile), swaggerJson.getBytes(Charset.forName("UTF-8")));
        } catch (IOException e) {
            throw new MojoExecutionException("Failed to download API definition", e);
        }
    }
}
