package io.swagger.swaggerhub.plugin;

import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import org.codehaus.plexus.configuration.PlexusConfiguration;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class SwaggerHubDownloadTest {

    @Rule
    public BetterMojoRule rule = new BetterMojoRule()
    {
        @Override
        protected void before() throws Throwable{
            super.before();
        }

        @Override
        protected void after(){
            super.after();
        }
    };

    @Test
    public void testDownloadYaml() throws Exception {
        File pom = rule.getTestFile("src/test/resources/testProjects/download-yaml.xml");
        runTest(pom);
    }

    @Test
    public void testDownload() throws Exception {
        File pom = rule.getTestFile("src/test/resources/testProjects/download.xml");
        runTest(pom);
    }

    private void runTest(File pom) throws Exception {
        assertNotNull(pom);
        assertTrue( pom.exists() );

        SwaggerHubDownload swaggerHubDownload = (SwaggerHubDownload) rule.lookupConfiguredMojo(pom, "download");
        assertNotNull( swaggerHubDownload );

        swaggerHubDownload.execute();
        final PlexusConfiguration config = rule.extractPluginConfiguration("swaggerhub-maven-plugin", pom);
        String file = config.getChild("outputFile").getValue();

        assertTrue(Files.isRegularFile(Paths.get(file)));
        Swagger swagger = new SwaggerParser().read(file);
        assertNotNull(swagger);
    }
}
