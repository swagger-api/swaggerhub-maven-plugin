package io.swagger.swaggerhub.plugin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import io.swagger.swaggerhub.plugin.requests.SaveSCMPluginConfigRequest;

import okhttp3.Response;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.testing.SilentLog;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;

import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.API_NAME;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.API_VERSION;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.OAS3;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.SCM_ACCOUNT;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.SCM_HOST;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.SCM_PASSWORD;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.SCM_PERSONAL_ACCESS_TOKEN;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.SCM_PROJECT;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.SCM_PROJECT_COLLECTION;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.SCM_URL;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.SCM_USERNAME;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.SCM_REPOSITORY_OWNER;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.SCM_INTEGRATION_ENABLED;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.SCM_INTEGRATION_NAME;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.SCM_INTEGRATION_OUTPUT_FILE;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.SCM_INTEGRATION_OUTPUT_FOLDER;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.SCM_INTEGRATION_SYNC_METHOD;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.SCM_INTEGRATION_TARGET;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.SCM_INTEGRATION_PROVIDER_GITHUB;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.SCM_INTEGRATION_REPOSITORY;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.API_OWNER;
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.SCM_BRANCH;

import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.SWAGGERHUB_API_TOKEN;
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
        //Given
        swaggerHubClient = buildSwaggerHubClient( null);
        SaveSCMPluginConfigRequest.Builder requestBuilder = requestBuilder();
        SaveSCMPluginConfigRequest saveSCMPluginConfigRequest = requestBuilder.build();
        String requestUrl = String.format("/plugins/configurations/%s/%s/%s/%s?oas=%s", API_OWNER, API_NAME, API_VERSION, SCM_INTEGRATION_PROVIDER_GITHUB, OAS3);
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
        //Given
        swaggerHubClient = buildSwaggerHubClient("basePath");
        SaveSCMPluginConfigRequest.Builder requestBuilder = requestBuilder();
        SaveSCMPluginConfigRequest saveSCMPluginConfigRequest = requestBuilder.build();
        String requestUrl = String.format("/basePath/plugins/configurations/%s/%s/%s/%s?oas=%s", API_OWNER, API_NAME, API_VERSION, SCM_INTEGRATION_PROVIDER_GITHUB, OAS3);
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
        return new SwaggerHubClient("localhost", wireMockServer.port(), "http", "fake_token", new SilentLog(), basePath, null);
    }

    private SaveSCMPluginConfigRequest.Builder requestBuilder(){
        return new SaveSCMPluginConfigRequest.Builder(API_OWNER, API_NAME, API_VERSION)
                .oas(OAS3)
                .branch(SCM_BRANCH)
                .enabled(SCM_INTEGRATION_ENABLED)
                .outputFile(SCM_INTEGRATION_OUTPUT_FILE)
                .repository(SCM_INTEGRATION_REPOSITORY)
                .repositoryOwner(SCM_REPOSITORY_OWNER)
                .scmProvider(SCM_INTEGRATION_PROVIDER_GITHUB)
                .target(SCM_INTEGRATION_TARGET)
                .token(SWAGGERHUB_API_TOKEN)
                .outputFolder(SCM_INTEGRATION_OUTPUT_FOLDER)
                .managedPaths(new String[]{SCM_INTEGRATION_OUTPUT_FILE})
                .name(SCM_INTEGRATION_NAME)
                .scmUsername(SCM_USERNAME)
                .scmPassword(SCM_PASSWORD)
                .project(SCM_PROJECT)
                .account(SCM_ACCOUNT)
                .personalAccessToken(SCM_PERSONAL_ACCESS_TOKEN)
                .url(SCM_URL)
                .projectCollection(SCM_PROJECT_COLLECTION)
                .host(SCM_HOST);
    }

    private RequestPatternBuilder putRequestPattern(String url){
        return putRequestedFor(urlPathEqualTo(String.format(url, API_OWNER, API_NAME, API_VERSION, SCM_INTEGRATION_PROVIDER_GITHUB)))
                .withQueryParam("oas", equalTo(OAS3))
                .withRequestBody(matchingJsonPath("$.branch", equalTo(SCM_BRANCH)))
                .withRequestBody(matchingJsonPath("$.enabled", equalTo("true")))
                .withRequestBody(matchingJsonPath("$.outputFile", equalTo(SCM_INTEGRATION_OUTPUT_FILE)))
                .withRequestBody(matchingJsonPath("$.repository", equalTo(SCM_INTEGRATION_REPOSITORY)))
                .withRequestBody(matchingJsonPath("$.owner", equalTo(SCM_REPOSITORY_OWNER)))
                .withRequestBody(matchingJsonPath("$.syncMethod", equalTo(SCM_INTEGRATION_SYNC_METHOD)))
                .withRequestBody(matchingJsonPath("$.target", equalTo(SCM_INTEGRATION_TARGET)))
                .withRequestBody(matchingJsonPath("$.outputFolder", equalTo(SCM_INTEGRATION_OUTPUT_FOLDER)))
                .withRequestBody(matchingJsonPath("$.managedPaths", equalToJson("[\"" + SCM_INTEGRATION_OUTPUT_FILE + "\"]")))
                .withRequestBody(matchingJsonPath("$.name", equalTo(SCM_INTEGRATION_NAME)))
                .withRequestBody(matchingJsonPath("$.username", equalTo(SCM_USERNAME)))
                .withRequestBody(matchingJsonPath("$.password", equalTo(SCM_PASSWORD)))
                .withRequestBody(matchingJsonPath("$.account", equalTo(SCM_ACCOUNT)))
                .withRequestBody(matchingJsonPath("$.project", equalTo(SCM_PROJECT)))
                .withRequestBody(matchingJsonPath("$.personalAccessToken", equalTo(SCM_PERSONAL_ACCESS_TOKEN)))
                .withRequestBody(matchingJsonPath("$.url", equalTo(SCM_URL)))
                .withRequestBody(matchingJsonPath("$.projectCollection", equalTo(SCM_PROJECT_COLLECTION)))
                .withRequestBody(matchingJsonPath("$.host", equalTo(SCM_HOST)));
    }

}
