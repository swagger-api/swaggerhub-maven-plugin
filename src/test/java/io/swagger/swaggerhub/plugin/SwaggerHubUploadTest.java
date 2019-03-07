package io.swagger.swaggerhub.plugin;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.github.tomakehurst.wiremock.matching.UrlPathPattern;
import io.swagger.swaggerhub.plugin.services.DefinitionFileFormat;
import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.configuration.PlexusConfiguration;
import org.junit.Test;

import java.io.File;

import static com.github.tomakehurst.wiremock.client.WireMock.badRequest;
import static com.github.tomakehurst.wiremock.client.WireMock.created;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToIgnoreCase;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.matchingJsonPath;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.putRequestedFor;
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
    private static final String FILE_FINDER_DIRECTORY = "src/test/resources/file-finder-test-definitions";
    private static final String API_1_FILENAME = "api-definition1.yaml";
    private static final String API_2_FILENAME = "api-definition2.json";
    private static final String API_3_FILENAME = "definition3.yml";
    private static final String TEST_RESOURCES_DIRECTORY = "src/test/resources";
    private static final String INPUT_FILE_FILENAME = "TestAPI.json";

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

    @Test
    public void testInputFileIsUploaded_andGitHubPluginSaveRequestMade() throws Exception {
        //Given
        File pom = getTestFile("src/test/resources/testProjects/upload-input-file-save-github-plugin.xml");
        SwaggerHubUpload swaggerHubUpload = (SwaggerHubUpload) lookupConfiguredMojo(pom, "upload");
        PlexusConfiguration config = extractPluginConfiguration("swaggerhub-maven-plugin", pom);

        String owner = config.getChild("owner").getValue();
        String api = config.getChild("api").getValue();
        String version = config.getChild("version").getValue();
        String isPrivate = config.getChild("isPrivate").getValue();
        String token = config.getChild("token").getValue();

        String branch = config.getChild("branch").getValue();
        String enabled = config.getChild("enableScmIntegration").getValue();
        String repository = config.getChild("repository").getValue();
        String repositoryOwner = config.getChild("repositoryOwner").getValue();
        String scmToken = config.getChild("scmToken").getValue();

        int port = Integer.parseInt(config.getChild("port").getValue());
        startMockServer(port);

        UrlPathPattern saveDefinitionRequest = stubSaveDefinitionRequest(owner, api, version, isPrivate, OAS2, JSON, token);
        UrlPathPattern saveSCMPluginConfigurationRequest = stubSaveSCMPluginConfigurationRequest(owner, api, version, OAS2, token);

        //Add test for output file value
        RequestPatternBuilder putRequestPattern1 = createPutSCMPluginConfigurationRequestPatternBuilder(owner, api, version, OAS2, branch,
                enabled, repository, repositoryOwner, scmToken, DefinitionFileFormat.JSON.getLanguageTarget(),
                TEST_RESOURCES_DIRECTORY, INPUT_FILE_FILENAME);

        //When
        swaggerHubUpload.execute();

        //Then
        verify(1, postRequestedFor(saveDefinitionRequest));
        verify(1, putRequestedFor(saveSCMPluginConfigurationRequest));
        verify(1, putRequestPattern1);
    }

    @Test
    public void testMultiDefinitionsAreUploaded_andGitHubPluginSaveRequestsMade() throws Exception {

        //Given
        File pom = getTestFile("src/test/resources/testProjects/upload-multi-definitions-save-github-plugins.xml");
        SwaggerHubUpload swaggerHubUpload = (SwaggerHubUpload) lookupConfiguredMojo(pom, "upload");
        PlexusConfiguration config = extractPluginConfiguration("swaggerhub-maven-plugin", pom);

        String isPrivate = config.getChild("isPrivate").getValue();
        String token = config.getChild("token").getValue();
        String owner = config.getChild("owner").getValue();
        String branch = config.getChild("branch").getValue();
        String enableScmIntegration = config.getChild("enableScmIntegration").getValue();
        String repository = config.getChild("repository").getValue();
        String repositoryOwner = config.getChild("repositoryOwner").getValue();
        String scmToken = config.getChild("scmToken").getValue();


        int port = Integer.parseInt(config.getChild("port").getValue());
        startMockServer(port);

        UrlPathPattern uploadDefinitionRequest1 = stubSaveDefinitionRequest(owner, API_1_TITLE, API_1_VERSION, isPrivate, OAS3, YAML,token);
        UrlPathPattern uploadDefinitionRequest2 = stubSaveDefinitionRequest(owner, API_2_TITLE, API_2_VERSION, isPrivate, OAS2, JSON,token);
        UrlPathPattern uploadDefinitionRequest3 = stubSaveDefinitionRequest(owner, API_3_TITLE, API_3_VERSION, isPrivate, OAS3, YAML,token);

        UrlPathPattern saveSCMPluginConfigurationRequest1 = stubSaveSCMPluginConfigurationRequest(owner, API_1_TITLE, API_1_VERSION, OAS3, token);
        UrlPathPattern saveSCMPluginConfigurationRequest2 = stubSaveSCMPluginConfigurationRequest(owner, API_2_TITLE, API_2_VERSION, OAS2, token);
        UrlPathPattern saveSCMPluginConfigurationRequest3 = stubSaveSCMPluginConfigurationRequest(owner, API_3_TITLE, API_3_VERSION, OAS3, token);

        RequestPatternBuilder putRequestPattern1 = createPutSCMPluginConfigurationRequestPatternBuilder(owner, API_1_TITLE, API_1_VERSION, OAS3, branch,
                enableScmIntegration, repository, repositoryOwner, scmToken, DefinitionFileFormat.YAML.getLanguageTarget(), FILE_FINDER_DIRECTORY, API_1_FILENAME);
        RequestPatternBuilder putRequestPattern2 = createPutSCMPluginConfigurationRequestPatternBuilder(owner, API_2_TITLE, API_2_VERSION, OAS2, branch,
                enableScmIntegration, repository, repositoryOwner, scmToken, DefinitionFileFormat.JSON.getLanguageTarget(), FILE_FINDER_DIRECTORY, API_2_FILENAME);
        RequestPatternBuilder putRequestPattern3 = createPutSCMPluginConfigurationRequestPatternBuilder(owner, API_3_TITLE, API_3_VERSION, OAS3, branch,
                enableScmIntegration, repository, repositoryOwner, scmToken, DefinitionFileFormat.YAML.getLanguageTarget(), FILE_FINDER_DIRECTORY, API_3_FILENAME);

        //When
        swaggerHubUpload.execute();

        //Then
        verify(1, postRequestedFor(uploadDefinitionRequest1));
        verify(1, postRequestedFor(uploadDefinitionRequest2));
        verify(1, postRequestedFor(uploadDefinitionRequest3));

        verify(1, putRequestedFor(saveSCMPluginConfigurationRequest1));
        verify(putRequestPattern1);

        verify(1, putRequestedFor(saveSCMPluginConfigurationRequest2));
        verify(putRequestPattern2);

        verify(1, putRequestedFor(saveSCMPluginConfigurationRequest3));
        verify(putRequestPattern3);

    }

    @Test
    public void testMultiSaveDefinitionRequestMade_despiteSaveFailures() throws Exception {

        //Given
        File pom = getTestFile("src/test/resources/testProjects/upload-multi-definitions-save-github-plugins.xml");
        SwaggerHubUpload swaggerHubUpload = (SwaggerHubUpload) lookupConfiguredMojo(pom, "upload");
        PlexusConfiguration config = extractPluginConfiguration("swaggerhub-maven-plugin", pom);

        String isPrivate = config.getChild("isPrivate").getValue();
        String token = config.getChild("token").getValue();
        String owner = config.getChild("owner").getValue();

        int port = Integer.parseInt(config.getChild("port").getValue());
        startMockServer(port);

        UrlPathPattern uploadDefinitionRequest1 = stubSaveDefinitionRequest(owner, API_1_TITLE, API_1_VERSION, isPrivate, OAS3, YAML,token);
        UrlPathPattern uploadDefinitionRequest2 = stubSaveDefinitionRequest(owner, API_2_TITLE, API_2_VERSION, isPrivate, OAS2, JSON,token);
        UrlPathPattern uploadDefinitionRequest3 = stubSaveDefinitionRequest(owner, API_3_TITLE, API_3_VERSION, isPrivate, OAS3, YAML,token);

        //When
        swaggerHubUpload.execute();

        //Then
        verify(1, postRequestedFor(uploadDefinitionRequest1));
        verify(1, postRequestedFor(uploadDefinitionRequest2));
        verify(1, postRequestedFor(uploadDefinitionRequest3));

    }

    @Test
    public void testBuildFails_whenSaveDefinitionFails_andFailuresArentSkipped() throws Exception {

        //Given
        File pom = getTestFile("src/test/resources/testProjects/fail_build_on_failed_requests.xml");
        SwaggerHubUpload swaggerHubUpload = (SwaggerHubUpload) lookupConfiguredMojo(pom, "upload");
        PlexusConfiguration config = extractPluginConfiguration("swaggerhub-maven-plugin", pom);

        String isPrivate = config.getChild("isPrivate").getValue();
        String token = config.getChild("token").getValue();
        String owner = config.getChild("owner").getValue();

        int port = Integer.parseInt(config.getChild("port").getValue());
        startMockServer(port);


        stubSaveDefinitionRequest(token, owner, API_1_TITLE, API_1_VERSION, isPrivate, OAS3, YAML, badRequest());
        UrlPathPattern uploadDefinitionRequest2 = stubSaveDefinitionRequest(owner, API_2_TITLE, API_2_VERSION, isPrivate, OAS2, JSON,token);

        //When
        boolean executionFailure = false;
        try {
            swaggerHubUpload.execute();
        }catch (Exception e){
            executionFailure = true;
            verify(0, postRequestedFor(uploadDefinitionRequest2));
        } // @Test(expected = MojoExecutionException.class) is not working as expected. This catch is a workaround

        assertTrue(executionFailure);

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
        return stubSaveDefinitionRequest(token, owner, api, version, isPrivate, oasVersion, format, created());
    }


    private UrlPathPattern stubSaveDefinitionRequest(String token, String owner, String api, String version, String isPrivate, String oasVersion, String format, ResponseDefinitionBuilder responseDefinitionBuilder){
        UrlPathPattern url = urlPathEqualTo("/apis/" + owner + "/" + api);
        stubFor(post(url)
                .withQueryParam("version", equalTo(version))
                .withQueryParam("isPrivate", equalTo(isPrivate != null ? isPrivate : "false"))
                .withQueryParam("oas", equalTo(oasVersion))
                .withHeader("Content-Type", equalToIgnoreCase(
                        String.format("application/%s; charset=UTF-8", format != null ? format : "json")))
                .withHeader("Authorization", equalTo(token))
                .withHeader("User-Agent", equalTo("swaggerhub-maven-plugin"))
                .willReturn(responseDefinitionBuilder));
        return url;
    }

    private UrlPathPattern stubSaveSCMPluginConfigurationRequest(String owner, String api, String version, String oasVersion, String token){
        UrlPathPattern url = urlPathEqualTo("/plugins/configurations/" + owner + "/" + api +"/"+version+"/GITHUB");
        stubFor(put(url)
                .withQueryParam("oas", equalTo(oasVersion))
                .withHeader("Content-Type", equalToIgnoreCase(
                        String.format("application/%s; charset=UTF-8", "json")))
                .withHeader("Authorization", equalTo(token))
                .withHeader("User-Agent", equalTo("swaggerhub-maven-plugin"))
                .willReturn(created()));
        return url;
    }

    private RequestPatternBuilder createPutSCMPluginConfigurationRequestPatternBuilder(String owner, String api, String version, String oasVersion, String branch,
                                                                                       String enabled, String repository, String repositoryOwner, String scmToken,
                                                                                       String target, String outputFolder, String outputFile){

        return putRequestedFor(urlPathEqualTo(String.format("/plugins/configurations/%s/%s/%s/GITHUB", owner, api, version)))
                .withQueryParam("oas", equalTo(oasVersion))
                .withRequestBody(matchingJsonPath("$.branch", equalTo(branch)))
                .withRequestBody(matchingJsonPath("$.enabled", equalTo(enabled)))
                .withRequestBody(matchingJsonPath("$.repository", equalTo(repository)))
                .withRequestBody(matchingJsonPath("$.owner", equalTo(repositoryOwner)))
                .withRequestBody(matchingJsonPath("$.token", equalTo(scmToken)))
                .withRequestBody(matchingJsonPath("$.syncMethod", equalTo("Advanced Sync")))
                .withRequestBody(matchingJsonPath("$.target", equalTo(target)))
                .withRequestBody(matchingJsonPath("$.outputFolder", equalTo(outputFolder)))
                .withRequestBody(matchingJsonPath("$.managedPaths", equalToJson("[\""+outputFile+"\"]")))
                .withRequestBody(matchingJsonPath("$.providedPaths", equalToJson("[]")))
                .withRequestBody(matchingJsonPath("$.ignoredPaths", equalToJson("[]")));

    }

    private void startMockServer(int port) {
        wireMockServer = new WireMockServer(options().port(port));
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
    }
}
