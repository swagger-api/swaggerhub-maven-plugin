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
 * Downloads API definition from SwaggerHub and writes it to {@code outputFile}, creating missing parent directories.
 */
@Mojo(name = "download")
public class SwaggerHubDownload extends AbstractMojo {
    /** SwaggerHub owner (user or organization) of the definition. */
    @Parameter(property = "download.owner", required = true)
    private String owner;
    /** Name of the API or domain to download. */
    @Parameter(property = "download.api", required = true)
    private String api;
    /** Version to download. */
    @Parameter(property = "download.version", required = true)
    private String version;
    /** Format to download, {@code json} or {@code yaml}. */
    @Parameter(property = "download.format", defaultValue = "json")
    private String format;
    /** SwaggerHub host name. */
    @Parameter(property = "download.host", defaultValue = "api.swaggerhub.com")
    private String host;
    /** SwaggerHub port. */
    @Parameter(property = "download.port", defaultValue = "443")
    private int port;
    /** Protocol used to connect, {@code http} or {@code https}. */
    @Parameter(property = "download.protocol", defaultValue = "https")
    private String protocol;
    /** SwaggerHub API key. Only needed for private definitions. */
    @Parameter(property = "download.token")
    private String token;
    /** Kind of definition to download, {@code API} or {@code domain} (case insensitive). Unrecognised values mean {@code API}. */
    @Parameter(property = "download.definitionType", defaultValue = "API")
    private String definitionType;
    /** File the definition is written to. Parent directories are created if missing. */
    @Parameter(property = "download.outputFile", required = true)
    private String outputFile;
    /** Optional path prefix in front of the SwaggerHub API paths, e.g. for on-premise installations. */
    @Parameter(property = "download.basepath")
    private String basepath;

    /** Maven settings, used to pick up the active proxy. */
    @Parameter( defaultValue = "${settings}", readonly = true )
    private Settings settings;


    /**
     * @throws MojoExecutionException if the definition cannot be downloaded or written
     */
    @Override
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
