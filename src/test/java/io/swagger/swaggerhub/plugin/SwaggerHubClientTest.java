package io.swagger.swaggerhub.plugin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.squareup.okhttp.Response;
import io.swagger.swaggerhub.plugin.requests.SaveSCMPluginConfigRequest;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.created;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
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
        swaggerHubClient = new SwaggerHubClient("localhost",wireMockServer.port(), "http", "fake_token");
    }

    @AfterClass
    public static void tearDown(){
        if (null!= wireMockServer && wireMockServer.isRunning()) {
            wireMockServer.stop();
        }
    }

    @Test
    public void verifySaveIntegrationPluginOfType_postsExpectedRequestBody() throws JsonProcessingException {
        //Given
        String owner = "owner";
        String api = "api";
        String version = "1.0.0";
        String oas = "3.0.0";
        String branch = "branch-test";
        String enabled = "true";
        String outputFile = "outputFile";
        String repository = "repository";
        String repositoryOwner = "repositoryOwner";
        String scmProvider = "GITHUB";
        String token = "token";
        String target = "JSON(Resolved)";
        String syncMethod = "Basic";

        SaveSCMPluginConfigRequest.Builder requestBuilder = new SaveSCMPluginConfigRequest.Builder(owner, api, version)
                .oas(oas)
                .branch(branch)
                .enabled(enabled)
                .outputFile(outputFile)
                .repository(repository)
                .repositoryOwner(repositoryOwner)
                .scmProvider(scmProvider)
                .syncMethod(syncMethod)
                .target(target)
                .token(token);

        SaveSCMPluginConfigRequest saveSCMPluginConfigRequest = requestBuilder.build();

        String requestUrl = String.format("/plugins/configurations/%s/%s/%s/%s?oas=%s", owner, api, version, scmProvider, oas);
        stubFor(put(requestUrl).willReturn(created()));

        RequestPatternBuilder postRequestPattern = putRequestedFor(urlPathEqualTo(String.format("/plugins/configurations/%s/%s/%s/%s", owner, api, version, scmProvider)))
                .withQueryParam("oas", equalTo(oas))
                .withRequestBody(matchingJsonPath("$.branch", equalTo(branch)))
                .withRequestBody(matchingJsonPath("$.enabled", equalTo(enabled)))
                .withRequestBody(matchingJsonPath("$.outputFile", equalTo(outputFile)))
                .withRequestBody(matchingJsonPath("$.repository", equalTo(repository)))
                .withRequestBody(matchingJsonPath("$.owner", equalTo(repositoryOwner)))
                .withRequestBody(matchingJsonPath("$.syncMethod", equalTo(syncMethod)))
                .withRequestBody(matchingJsonPath("$.target", equalTo(target)))
                .withRequestBody(matchingJsonPath("$.token", equalTo(token)));

        //When
        Optional<Response> response = swaggerHubClient.saveIntegrationPluginOfType(saveSCMPluginConfigRequest);

        //Then
        verify(1, postRequestPattern);
        response.ifPresent( x -> assertEquals(201, response.get().code()));
        if(!response.isPresent()){
            fail();
        }
    }
}
