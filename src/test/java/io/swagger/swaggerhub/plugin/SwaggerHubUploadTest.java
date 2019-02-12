package io.swagger.swaggerhub.plugin;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.UrlPathPattern;
import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.configuration.PlexusConfiguration;
import org.junit.Test;

import java.io.File;

import static com.github.tomakehurst.wiremock.client.WireMock.created;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToIgnoreCase;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

public class SwaggerHubUploadTest extends BetterAbstractMojoTestCase {

    private static final String API_1_TITLE = "Test_API_1_Title_YAML";
    private static final String API_2_TITLE = "Test_API_2_JSON";
    private static final String API_3_TITLE = "TEST_API_3_YML";
    private static final String API_1_VERSION = "1.0.1-SNAPSHOT";
    private static final String API_2_VERSION = "1.0.0";
    private static final String API_3_VERSION = "1.0.2-SNAPSHOT";
    private static final String OAS3 = "3.0.0";
    private static final String OAS2 = "2.0";
    private static final String YAML = "yaml";
    private static final String JSON = "json";

    private WireMockServer wireMockServer;

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if (null!= wireMockServer && wireMockServer.isRunning()) {
            wireMockServer.stop();
        }
    }

    @Test
    public void testUpload() throws Exception {
        File pom = getTestFile(
                "src/test/resources/testProjects/upload.xml");
        runTest(pom, OAS2);
    }

    @Test
    public void testUploadYaml() throws Exception {
        File pom = getTestFile("src/test/resources/testProjects/upload-yaml.xml");
        runTest(pom, OAS2);
    }

    @Test
    public void testUploadOAS3Yaml() throws Exception {
        File pom = getTestFile("src/test/resources/testProjects/upload-oas3-yaml.xml");
        runTest(pom, OAS3);
    }

    @Test
    public void testUploadPrivate() throws Exception {
        File pom = getTestFile("src/test/resources/testProjects/upload-private.xml");
        runTest(pom, OAS2);
    }

    @Test
    public void testUploadFails_whenUploadTypeIsUnknown() throws Exception {
        //Given
        File pom = getTestFile("src/test/resources/testProjects/incorrect-upload-type.xml");
        SwaggerHubUpload swaggerHubUpload = (SwaggerHubUpload) lookupConfiguredMojo(pom, "upload");
        boolean executionFailure = false;

        //When
        try {
            swaggerHubUpload.execute();
        }catch (MojoExecutionException me){
            executionFailure = true;
        } // @Test(expected = MojoExecutionException.class) is not working as expected. This catch is a workaround

        //Then
        assertTrue(executionFailure);
    }

    @Test
    public void testMultiDefinitionsAreUploaded_WhenSpecifyingDirectory() throws Exception {
        //Given
        String owner = "swaggerhubuser";
        File pom = getTestFile("src/test/resources/testProjects/upload-multi-definitions.xml");
        SwaggerHubUpload swaggerHubUpload = (SwaggerHubUpload) lookupConfiguredMojo(pom, "upload");
        PlexusConfiguration config = extractPluginConfiguration("swaggerhub-maven-plugin", pom);

        String isPrivate = config.getChild("isPrivate").getValue();
        String token = config.getChild("token").getValue();
        int port = Integer.parseInt(config.getChild("port").getValue());

        startMockServer(port);

        UrlPathPattern definition1Request = stubSaveDefinitionRequest(owner, API_1_TITLE, API_1_VERSION, isPrivate, OAS3, YAML,token);
        UrlPathPattern definition2Request = stubSaveDefinitionRequest(owner, API_2_TITLE, API_2_VERSION, isPrivate, OAS2, JSON,token);
        UrlPathPattern definition3Request = stubSaveDefinitionRequest(owner, API_3_TITLE, API_3_VERSION, isPrivate, OAS3, YAML,token);

        //When
        swaggerHubUpload.execute();

        //Then
        verify(1, postRequestedFor(definition1Request));
        verify(1, postRequestedFor(definition2Request));
        verify(1, postRequestedFor(definition3Request));
    }

    @Test
    public void testMultiDefinitionsAreUploaded_WhenSpecifyingDirectoryAndRegexPattern() throws Exception {
        //Given
        String owner = "swaggerhubuser";
        File pom = getTestFile("src/test/resources/testProjects/upload-multi-definitions-with-regex.xml");
        SwaggerHubUpload swaggerHubUpload = (SwaggerHubUpload) lookupConfiguredMojo(pom, "upload");
        PlexusConfiguration config = extractPluginConfiguration("swaggerhub-maven-plugin", pom);

        String isPrivate = config.getChild("isPrivate").getValue();
        String token = config.getChild("token").getValue();
        int port = Integer.parseInt(config.getChild("port").getValue());

        startMockServer(port);

        UrlPathPattern definition1Request = stubSaveDefinitionRequest(owner, API_1_TITLE, API_1_VERSION, isPrivate, OAS3, YAML,token);
        UrlPathPattern definition2Request = stubSaveDefinitionRequest(owner, API_2_TITLE, API_2_VERSION, isPrivate, OAS2, JSON,token);
        UrlPathPattern definition3Request = stubSaveDefinitionRequest(owner, API_3_TITLE, API_3_VERSION, isPrivate, OAS3, YAML,token);

        //When
        swaggerHubUpload.execute();

        //Then
        verify(1, postRequestedFor(definition1Request));
        verify(1, postRequestedFor(definition2Request));
        verify(0, postRequestedFor(definition3Request));
    }

    private void runTest(File pom, String expectedOasVersion) throws Exception {
        assertNotNull(pom);
        assertTrue(pom.exists());

        SwaggerHubUpload swaggerHubUpload = (SwaggerHubUpload) lookupConfiguredMojo(pom, "upload");
        assertNotNull(swaggerHubUpload);

        final PlexusConfiguration config = extractPluginConfiguration("swaggerhub-maven-plugin", pom);
        UrlPathPattern url = setupServerMocking(config, expectedOasVersion);

        swaggerHubUpload.execute();
        verify(1, postRequestedFor(url));
    }

    private UrlPathPattern setupServerMocking(PlexusConfiguration config, String oasVersion) {
        String api = config.getChild("api").getValue();
        String owner = config.getChild("owner").getValue();
        String version = config.getChild("version").getValue();
        String token = config.getChild("token").getValue();
        int port = Integer.parseInt(config.getChild("port").getValue());
        String format = config.getChild("format").getValue();
        String isPrivate = config.getChild("isPrivate").getValue();

        startMockServer(port);

        UrlPathPattern url = stubSaveDefinitionRequest(owner, api, version, isPrivate, oasVersion, format, token);

        return url;
    }

    private UrlPathPattern stubSaveDefinitionRequest(String owner, String api, String version, String isPrivate, String oasVersion, String format, String token){
        UrlPathPattern url = urlPathEqualTo("/apis/" + owner + "/" + api);
        stubFor(post(url)
                .withQueryParam("version", equalTo(version))
                .withQueryParam("isPrivate", equalTo(isPrivate != null ? isPrivate : "false"))
                .withQueryParam("oas", equalTo(oasVersion))
                .withHeader("Content-Type", equalToIgnoreCase(
                        String.format("application/%s; charset=UTF-8", format != null ? format : "json")))
                .withHeader("Authorization", equalTo(token))
                .withHeader("User-Agent", equalTo("swaggerhub-maven-plugin"))
                .willReturn(created()));
        return url;
    }

    private void startMockServer(int port) {
        wireMockServer = new WireMockServer(options().port(port));
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
    }
}
