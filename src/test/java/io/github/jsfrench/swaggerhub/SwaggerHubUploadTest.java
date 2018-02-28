package io.github.jsfrench.swaggerhub;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.github.tomakehurst.wiremock.matching.UrlPathPattern;
import org.codehaus.plexus.configuration.PlexusConfiguration;

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
    private WireMockServer wireMockServer;

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        wireMockServer.stop();
    }

    public void testUpload() throws Exception {
        File pom = getTestFile("src/test/resources/testProjects/upload.xml");
        runTest(pom);
    }

    public void testUploadYaml() throws Exception {
        File pom = getTestFile("src/test/resources/testProjects/upload-yaml.xml");
        runTest(pom);
    }

    private void runTest(File pom) throws Exception {
        assertNotNull(pom);
        assertTrue(pom.exists());

        SwaggerHubUpload swaggerHubUpload = (SwaggerHubUpload) lookupConfiguredMojo(pom, "upload");
        assertNotNull(swaggerHubUpload);

        final PlexusConfiguration config = extractPluginConfiguration("swaggerhub-maven-plugin", pom);
        UrlPathPattern url = setupServerMocking(config);

        swaggerHubUpload.execute();
        verify(1, postRequestedFor(url));
    }

    private UrlPathPattern setupServerMocking(PlexusConfiguration config) {
        String api = config.getChild("api").getValue();
        String owner = config.getChild("owner").getValue();
        String version = config.getChild("version").getValue();
        String token = config.getChild("token").getValue();
        int port = Integer.parseInt(config.getChild("port").getValue());
        String format = config.getChild("format").getValue();

        startMockServer(port);

        UrlPathPattern url = urlPathEqualTo("/apis/" + owner + "/" + api);

        stubFor(post(url)
                .withQueryParam("version", equalTo(version))
                .withQueryParam("isPrivate", equalTo("false"))
                .withQueryParam("force", equalTo("false"))
                .withHeader("Content-Type", equalToIgnoreCase(String.format("application/%s; charset=UTF-8", format)))
                .withHeader("Authorization", equalTo(token))
                .willReturn(created()));

        return url;
    }

    private void startMockServer(int port) {
        wireMockServer = new WireMockServer(options().port(port));
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
    }
}
