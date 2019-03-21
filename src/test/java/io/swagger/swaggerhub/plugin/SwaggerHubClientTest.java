package io.swagger.swaggerhub.plugin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.squareup.okhttp.Response;
import io.swagger.swaggerhub.plugin.requests.SaveSCMPluginConfigRequest;
import org.apache.maven.plugin.testing.SilentLog;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.created;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.matchingJsonPath;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.putRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SwaggerHubClientTest {

    private static WireMockServer wireMockServer;

    private SwaggerHubClient swaggerHubClient;

    @BeforeClass
    public static void setUpWiremockInstance(){
        wireMockServer = new WireMockServer(options().port(8089));
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
    }

    @Before
    public void setUpTest(){
        wireMockServer.resetMappings();
        swaggerHubClient = new SwaggerHubClient("localhost",wireMockServer.port(), "http", "fake_token", new SilentLog());
    }

    @AfterClass
    public static void tearDown(){
        if (null!= wireMockServer && wireMockServer.isRunning()) {
            wireMockServer.stop();
        }
    }

    /**
     * This test defines the expected request format to be made to SwaggerHub to create an Integration plugin
     * The values defined as part of the request matcher are what is expected to be set due to the users configuration.
     * @throws JsonProcessingException
     */
    @Test
    public void verifySaveIntegrationPluginOfType_postsExpectedRequestBody() throws JsonProcessingException {
        //Given
        String owner = "owner";
        String api = "api";
        String version = "1.0.0";
        String oas = "3.0.0";
        String branch = "branch-test";
        boolean enabled = true;
        String outputFile = "outputFile";
        String repository = "repository";
        String repositoryOwner = "repositoryOwner";
        String scmProvider = "GITHUB";
        String token = "token";
        String target = "JSON (Unresolved)";
        String syncMethod = "Advanced Sync";
        String name = "Integration Name";
        String outputFolder = "output/folder";
        String scmUsername = "scmUsername";
        String scmPassword = "P455m07d";

        SaveSCMPluginConfigRequest.Builder requestBuilder = new SaveSCMPluginConfigRequest.Builder(owner, api, version)
                .oas(oas)
                .branch(branch)
                .enabled(enabled)
                .outputFile(outputFile)
                .repository(repository)
                .repositoryOwner(repositoryOwner)
                .scmProvider(scmProvider)
                .target(target)
                .token(token)
                .outputFolder(outputFolder)
                .managedPaths(new String[]{outputFile})
                .name(name)
                .scmUsername(scmUsername)
                .scmPassword(scmPassword);

        SaveSCMPluginConfigRequest saveSCMPluginConfigRequest = requestBuilder.build();

        String requestUrl = String.format("/plugins/configurations/%s/%s/%s/%s?oas=%s", owner, api, version, scmProvider, oas);
        stubFor(put(requestUrl).willReturn(created()));

        RequestPatternBuilder putRequestPattern = putRequestedFor(urlPathEqualTo(String.format("/plugins/configurations/%s/%s/%s/%s", owner, api, version, scmProvider)))
                .withQueryParam("oas", equalTo(oas))
                .withRequestBody(matchingJsonPath("$.branch", equalTo(branch)))
                .withRequestBody(matchingJsonPath("$.enabled", equalTo("true")))
                .withRequestBody(matchingJsonPath("$.outputFile", equalTo(outputFile)))
                .withRequestBody(matchingJsonPath("$.repository", equalTo(repository)))
                .withRequestBody(matchingJsonPath("$.owner", equalTo(repositoryOwner)))
                .withRequestBody(matchingJsonPath("$.syncMethod", equalTo(syncMethod)))
                .withRequestBody(matchingJsonPath("$.target", equalTo(target)))
                .withRequestBody(matchingJsonPath("$.outputFolder", equalTo(outputFolder)))
                .withRequestBody(matchingJsonPath("$.managedPaths", equalToJson("[\""+outputFile+"\"]")))
                .withRequestBody(matchingJsonPath("$.name", equalTo(name)))
                .withRequestBody(matchingJsonPath("$.username", equalTo(scmUsername)))
                .withRequestBody(matchingJsonPath("$.password", equalTo(scmPassword)));

        //When
        Optional<Response> response = swaggerHubClient.saveIntegrationPluginOfType(saveSCMPluginConfigRequest);

        //Then
        verify(1, putRequestPattern);
        response.ifPresent( x -> assertEquals(201, response.get().code()));
        if(!response.isPresent()){
            fail();
        }
    }
}
