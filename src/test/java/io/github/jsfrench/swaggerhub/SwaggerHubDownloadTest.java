package io.github.jsfrench.swaggerhub;

import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import org.codehaus.plexus.configuration.PlexusConfiguration;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SwaggerHubDownloadTest extends BetterAbstractMojoTestCase {


    public void testDownloadYaml() throws Exception {
        File pom = getTestFile("src/test/resources/testProjects/download-yaml.xml");
        runTest(pom);
    }

    public void testDownload() throws Exception {
        File pom = getTestFile("src/test/resources/testProjects/download.xml");
        runTest(pom);
    }

    private void runTest(File pom) throws Exception {
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
