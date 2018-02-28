package io.github.jsfrench.swaggerhub;

import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.codehaus.plexus.configuration.PlexusConfiguration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SwaggerHubDownloadTest extends BetterAbstractMojoTestCase {

    protected void setup() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDownload() throws Exception {
        File pom = getTestFile("src/test/resources/testProjects/download.xml");
        assertNotNull(pom);
        assertTrue( pom.exists() );

        SwaggerHubDownload swaggerHubDownload = (SwaggerHubDownload) lookupConfiguredMojo(pom, "download");
        assertNotNull( swaggerHubDownload );

        swaggerHubDownload.execute();
        final PlexusConfiguration config = extractPluginConfiguration("swaggerhub-maven-plugin", pom);
        String file = config.getChild("outputFile").getValue();

        assertTrue(Files.isRegularFile(Paths.get(file)));
        Swagger swagger = new SwaggerParser().read(file);
        assertNotNull(swagger);
    }

    public void testDownloadYaml() throws Exception {
        File pom = getTestFile("src/test/resources/testProjects/download-yaml.xml");
        assertNotNull(pom);
        assertTrue( pom.exists() );

        SwaggerHubDownload swaggerHubDownload = (SwaggerHubDownload) lookupConfiguredMojo(pom, "download");
        assertNotNull( swaggerHubDownload );

        swaggerHubDownload.execute();
        final PlexusConfiguration config = extractPluginConfiguration("swaggerhub-maven-plugin", pom);
        String file = config.getChild("outputFile").getValue();

        assertTrue(Files.isRegularFile(Paths.get(file)));
        Swagger swagger = new SwaggerParser().read(file);
        assertNotNull(swagger);
    }
}
