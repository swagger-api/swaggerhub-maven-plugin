package io.swagger.swaggerhub.plugin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.squareup.okhttp.Response;
import io.swagger.swaggerhub.plugin.requests.SaveSCMPluginConfigRequest;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.testing.SilentLog;
import org.junit.After;
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

    private static String OWNER = "owner";
    private static String API = "api";
    private static String VERSION = "1.0.0";
    private static String OAS = "3.0.0";
    private static String BRANCH = "branch-test";
    private static boolean ENABLED = true;
    private static String OUTPUT_FILE = "outputFile";
    private static String REPOSITORY = "repository";
    private static String REPOSITORY_OWNER = "repositoryOwner";
    private static String SCM_PROVIDER = "GITHUB";
    private static String TOKEN = "token";
    private static String TARGET = "JSON (Unresolved)";
    private static String SYNC_METHOD = "Advanced Sync";
    private static String NAME = "Integration Name";
    private static String OUTPUT_FOLDER = "output/folder";
    private static String SCM_USERNAME = "scmUsername";
    private static String SCM_PASSOWRD = "P455m07d";

    @BeforeClass
    public static void setUpWiremockInstance(){
        wireMockServer = new WireMockServer(options().port(8089));
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
    }

    @Before
    public void setUpTest(){
        wireMockServer.resetMappings();
    }

    @After
    public void cleanUptest(){
        swaggerHubClient = null;
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
        swaggerHubClient = buildSwaggerHubClient( null);

        SaveSCMPluginConfigRequest.Builder requestBuilder = requestBuilder();

        SaveSCMPluginConfigRequest saveSCMPluginConfigRequest = requestBuilder.build();

        String requestUrl = String.format("/plugins/configurations/%s/%s/%s/%s?oas=%s", OWNER, API, VERSION, SCM_PROVIDER, OAS);
        stubFor(put(requestUrl).willReturn(created()));

        RequestPatternBuilder putRequestPattern = putRequestPattern("/plugins/configurations/%s/%s/%s/%s");

        //When
        Optional<Response> response = swaggerHubClient.saveIntegrationPluginOfType(saveSCMPluginConfigRequest);

        //Then
        verify(1, putRequestPattern);
        response.ifPresent( x -> assertEquals(201, response.get().code()));
        if(!response.isPresent()){
            fail();
        }
    }

    /**
     * This test defines the expected request format to be made to SwaggerHub to create an Integration plugin
     * The values defined as part of the request matcher are what is expected to be set due to the users configuration.
     * @throws JsonProcessingException
     */
    @Test
    public void verifySaveIntegrationPluginOfType_postsExpectedRequestBodyWithBasePath() throws JsonProcessingException {
        swaggerHubClient = buildSwaggerHubClient("basePath");

        SaveSCMPluginConfigRequest.Builder requestBuilder = requestBuilder();

        SaveSCMPluginConfigRequest saveSCMPluginConfigRequest = requestBuilder.build();

        String requestUrl = String.format("/basePath/plugins/configurations/%s/%s/%s/%s?oas=%s", OWNER, API, VERSION, SCM_PROVIDER, OAS);
        stubFor(put(requestUrl).willReturn(created()));

        RequestPatternBuilder putRequestPattern = putRequestPattern("/basePath/plugins/configurations/%s/%s/%s/%s");

        //When
        Optional<Response> response = swaggerHubClient.saveIntegrationPluginOfType(saveSCMPluginConfigRequest);

        //Then
        verify(1, putRequestPattern);
        response.ifPresent( x -> assertEquals(201, response.get().code()));
        if(!response.isPresent()){
            fail();
        }
    }

    private SwaggerHubClient buildSwaggerHubClient(String basePath){
        return new SwaggerHubClient("localhost", wireMockServer.port(), "http", "fake_token", new SilentLog(), basePath);
    }

    private SaveSCMPluginConfigRequest.Builder requestBuilder(){
        return new SaveSCMPluginConfigRequest.Builder(OWNER, API, VERSION)
                .oas(OAS)
                .branch(BRANCH)
                .enabled(ENABLED)
                .outputFile(OUTPUT_FILE)
                .repository(REPOSITORY)
                .repositoryOwner(REPOSITORY_OWNER)
                .scmProvider(SCM_PROVIDER)
                .target(TARGET)
                .token(TOKEN)
                .outputFolder(OUTPUT_FOLDER)
                .managedPaths(new String[]{OUTPUT_FILE})
                .name(NAME)
                .scmUsername(SCM_USERNAME)
                .scmPassword(SCM_PASSOWRD);
    }

    private RequestPatternBuilder putRequestPattern(String url){
        return putRequestedFor(urlPathEqualTo(String.format(url, OWNER, API, VERSION, SCM_PROVIDER)))
                .withQueryParam("oas", equalTo(OAS))
                .withRequestBody(matchingJsonPath("$.branch", equalTo(BRANCH)))
                .withRequestBody(matchingJsonPath("$.enabled", equalTo("true")))
                .withRequestBody(matchingJsonPath("$.outputFile", equalTo(OUTPUT_FILE)))
                .withRequestBody(matchingJsonPath("$.repository", equalTo(REPOSITORY)))
                .withRequestBody(matchingJsonPath("$.owner", equalTo(REPOSITORY_OWNER)))
                .withRequestBody(matchingJsonPath("$.syncMethod", equalTo(SYNC_METHOD)))
                .withRequestBody(matchingJsonPath("$.target", equalTo(TARGET)))
                .withRequestBody(matchingJsonPath("$.outputFolder", equalTo(OUTPUT_FOLDER)))
                .withRequestBody(matchingJsonPath("$.managedPaths", equalToJson("[\""+OUTPUT_FILE+"\"]")))
                .withRequestBody(matchingJsonPath("$.name", equalTo(NAME)))
                .withRequestBody(matchingJsonPath("$.username", equalTo(SCM_USERNAME)))
                .withRequestBody(matchingJsonPath("$.password", equalTo(SCM_PASSOWRD)));
    }

}
