package io.swagger.swaggerhub.plugin;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.github.tomakehurst.wiremock.matching.UrlPathPattern;
import io.swagger.swaggerhub.plugin.exceptions.UploadParametersException;
import io.swagger.swaggerhub.plugin.services.DefinitionFileFormat;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.configuration.PlexusConfiguration;
import org.junit.Test;

import java.io.File;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

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
import static io.swagger.swaggerhub.plugin.utils.SwaggerHubUploadTestConstants.*;

@RunWith(JUnit4.class)
public class SwaggerHubUploadTest extends BetterAbstractMojoTestCase {

    private WireMockServer wireMockServer;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        startMockServer(WIREMOCK_PORT);
        wireMockServer.resetMappings();
    }

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
        boolean executionFailure = false;

        //When
        try {
            getSwaggerUpload("src/test/resources/testProjects/incorrect-upload-type.xml").execute();
        }catch (MojoExecutionException me){
            executionFailure = true;
        } // @Test(expected = MojoExecutionException.class) is not working as expected. This catch is a workaround

        //Then
        assertTrue(executionFailure);
    }

    @Test
    public void testMultiDefinitionsAreUploaded_WhenSpecifyingDirectory() throws Exception {
        //Given
        UrlPathPattern definition1Request = stubSaveDefinitionRequest(API_OWNER, MULTI_UPLOAD_API_1_TITLE, MULTI_UPLOAD_API_1_VERSION, IS_PRIVATE, OAS3, YAML, SWAGGERHUB_API_TOKEN);
        UrlPathPattern definition2Request = stubSaveDefinitionRequest(API_OWNER, MULTI_UPLOAD_API_2_TITLE, MULTI_UPLOAD_API_2_VERSION, IS_PRIVATE, OAS2, JSON, SWAGGERHUB_API_TOKEN);
        UrlPathPattern definition3Request = stubSaveDefinitionRequest(API_OWNER, MULTI_UPLOAD_API_3_TITLE, MULTI_UPLOAD_API_3_VERSION, IS_PRIVATE, OAS3, YAML, SWAGGERHUB_API_TOKEN);

        //When
        getSwaggerUpload("src/test/resources/testProjects/upload-multi-definitions.xml").execute();

        //Then
        verify(1, postRequestedFor(definition1Request));
        verify(1, postRequestedFor(definition2Request));
        verify(1, postRequestedFor(definition3Request));
    }

    @Test
    public void testMultiDefinitionsAreUploaded_WhenSpecifyingDirectoryAndRegexPattern() throws Exception {
        //Given
        UrlPathPattern definition1Request = stubSaveDefinitionRequest(API_OWNER, MULTI_UPLOAD_API_1_TITLE, MULTI_UPLOAD_API_1_VERSION, IS_PRIVATE, OAS3, YAML, SWAGGERHUB_API_TOKEN);
        UrlPathPattern definition2Request = stubSaveDefinitionRequest(API_OWNER, MULTI_UPLOAD_API_2_TITLE, MULTI_UPLOAD_API_2_VERSION, IS_PRIVATE, OAS2, JSON, SWAGGERHUB_API_TOKEN);
        UrlPathPattern definition3Request = stubSaveDefinitionRequest(API_OWNER, MULTI_UPLOAD_API_3_TITLE, MULTI_UPLOAD_API_3_VERSION, IS_PRIVATE, OAS3, YAML, SWAGGERHUB_API_TOKEN);

        //When
        getSwaggerUpload("src/test/resources/testProjects/upload-multi-definitions-with-regex.xml").execute();

        //Then
        verify(1, postRequestedFor(definition1Request));
        verify(1, postRequestedFor(definition2Request));
        verify(0, postRequestedFor(definition3Request));
    }

    @Test
    public void testInputFileIsUploaded_andSCMSaveRequestMadeWithToken() throws Exception {
        //Given
        UrlPathPattern saveDefinitionRequest = stubSaveDefinitionRequest(API_OWNER, INPUT_FILE_API, INPUT_FILE_API_VERSION, IS_PRIVATE, OAS2, JSON, SWAGGERHUB_API_TOKEN);
        UrlPathPattern saveSCMPluginConfigurationRequest = stubSaveSCMPluginConfigurationRequest(API_OWNER, INPUT_FILE_API, INPUT_FILE_API_VERSION, OAS2, SWAGGERHUB_API_TOKEN);

        //Add test for output file value
        RequestPatternBuilder putRequestPattern1 = createPutSCMConfigRequestPatternWithToken(API_OWNER, INPUT_FILE_API, INPUT_FILE_API_VERSION, OAS2, SCM_BRANCH,
                SCM_ENABLE_INTEGRATION, SCM_REPOSITORY, SCM_REPOSITORY_OWNER, INPUT_FILE_FILENAME, DefinitionFileFormat.JSON.getLanguageTarget(), TEST_RESOURCES_DIRECTORY, SCM_TOKEN);

        //When
        getSwaggerUpload("src/test/resources/testProjects/upload-input-file-save-github-plugin.xml").execute();

        //Then
        verify(1, postRequestedFor(saveDefinitionRequest));
        verify(1, putRequestedFor(saveSCMPluginConfigurationRequest));
        verify(1, putRequestPattern1);
    }

    @Test
    public void testInputFileIsUploaded_andSCMSaveRequestMadeWithUsernamePassword() throws Exception {
        //Given
        UrlPathPattern saveDefinitionRequest = stubSaveDefinitionRequest(API_OWNER, INPUT_FILE_API, INPUT_FILE_API_VERSION, IS_PRIVATE, OAS2, JSON, SWAGGERHUB_API_TOKEN);
        UrlPathPattern saveSCMPluginConfigurationRequest = stubSaveSCMPluginConfigurationRequest(API_OWNER, INPUT_FILE_API, INPUT_FILE_API_VERSION, OAS2, SWAGGERHUB_API_TOKEN);

        //Add test for output file value
        RequestPatternBuilder saveSCMConfigRequest = createPutSCMConfigRequestPatternWithUsernamePassword(API_OWNER, INPUT_FILE_API, INPUT_FILE_API_VERSION, OAS2, SCM_BRANCH,
                SCM_ENABLE_INTEGRATION, SCM_REPOSITORY, SCM_REPOSITORY_OWNER, INPUT_FILE_FILENAME,
                DefinitionFileFormat.JSON.getLanguageTarget(), TEST_RESOURCES_DIRECTORY, SCM_USERNAME,SCM_PASSWORD);

        //When
        getSwaggerUpload("src/test/resources/testProjects/upload-input-file-save-scm-plugin-with-username-password.xml").execute();

        //Then
        verify(1, postRequestedFor(saveDefinitionRequest));
        verify(1, putRequestedFor(saveSCMPluginConfigurationRequest));
        verify(1, saveSCMConfigRequest);
    }

    @Test
    public void testMultiDefinitionsAreUploaded_andSCMSaveRequestMadeWithTokenMade() throws Exception {
        //Given
        UrlPathPattern uploadDefinitionRequest1 = stubSaveDefinitionRequest(API_OWNER, MULTI_UPLOAD_API_1_TITLE, MULTI_UPLOAD_API_1_VERSION, IS_PRIVATE, OAS3, YAML, SWAGGERHUB_API_TOKEN);
        UrlPathPattern uploadDefinitionRequest2 = stubSaveDefinitionRequest(API_OWNER, MULTI_UPLOAD_API_2_TITLE, MULTI_UPLOAD_API_2_VERSION, IS_PRIVATE, OAS2, JSON, SWAGGERHUB_API_TOKEN);
        UrlPathPattern uploadDefinitionRequest3 = stubSaveDefinitionRequest(API_OWNER, MULTI_UPLOAD_API_3_TITLE, MULTI_UPLOAD_API_3_VERSION, IS_PRIVATE, OAS3, YAML, SWAGGERHUB_API_TOKEN);

        UrlPathPattern saveSCMPluginConfigurationRequest1 = stubSaveSCMPluginConfigurationRequest(API_OWNER, MULTI_UPLOAD_API_1_TITLE, MULTI_UPLOAD_API_1_VERSION, OAS3, SWAGGERHUB_API_TOKEN);
        UrlPathPattern saveSCMPluginConfigurationRequest2 = stubSaveSCMPluginConfigurationRequest(API_OWNER, MULTI_UPLOAD_API_2_TITLE, MULTI_UPLOAD_API_2_VERSION, OAS2, SWAGGERHUB_API_TOKEN);
        UrlPathPattern saveSCMPluginConfigurationRequest3 = stubSaveSCMPluginConfigurationRequest(API_OWNER, MULTI_UPLOAD_API_3_TITLE, MULTI_UPLOAD_API_3_VERSION, OAS3, SWAGGERHUB_API_TOKEN);

        RequestPatternBuilder putRequestPattern1 = createPutSCMConfigRequestPatternWithToken(API_OWNER, MULTI_UPLOAD_API_1_TITLE, MULTI_UPLOAD_API_1_VERSION, OAS3, SCM_BRANCH,
                SCM_ENABLE_INTEGRATION, SCM_REPOSITORY, SCM_REPOSITORY_OWNER, MULTI_UPLOAD_API_1_FILENAME, DefinitionFileFormat.YAML.getLanguageTarget(), FILE_FINDER_DIRECTORY, SCM_TOKEN);
        RequestPatternBuilder putRequestPattern2 = createPutSCMConfigRequestPatternWithToken(API_OWNER, MULTI_UPLOAD_API_2_TITLE, MULTI_UPLOAD_API_2_VERSION, OAS2, SCM_BRANCH,
                SCM_ENABLE_INTEGRATION, SCM_REPOSITORY, SCM_REPOSITORY_OWNER, MULTI_UPLOAD_API_2_FILENAME, DefinitionFileFormat.JSON.getLanguageTarget(), FILE_FINDER_DIRECTORY, SCM_TOKEN);
        RequestPatternBuilder putRequestPattern3 = createPutSCMConfigRequestPatternWithToken(API_OWNER, MULTI_UPLOAD_API_3_TITLE, MULTI_UPLOAD_API_3_VERSION, OAS3, SCM_BRANCH,
                SCM_ENABLE_INTEGRATION, SCM_REPOSITORY, SCM_REPOSITORY_OWNER, MULTI_UPLOAD_API_3_FILENAME, DefinitionFileFormat.YAML.getLanguageTarget(), FILE_FINDER_DIRECTORY, SCM_TOKEN);

        //When
        getSwaggerUpload("src/test/resources/testProjects/upload-multi-definitions-save-github-plugins.xml").execute();

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
    public void testMultiDefinitionsAreUploaded_andSCMSaveRequestMadeWithUsernamePassword() throws Exception {
        //Given
        UrlPathPattern uploadDefinitionRequest1 = stubSaveDefinitionRequest(API_OWNER, MULTI_UPLOAD_API_1_TITLE, MULTI_UPLOAD_API_1_VERSION, IS_PRIVATE, OAS3, YAML, SWAGGERHUB_API_TOKEN);
        UrlPathPattern uploadDefinitionRequest2 = stubSaveDefinitionRequest(API_OWNER, MULTI_UPLOAD_API_2_TITLE, MULTI_UPLOAD_API_2_VERSION, IS_PRIVATE, OAS2, JSON, SWAGGERHUB_API_TOKEN);
        UrlPathPattern uploadDefinitionRequest3 = stubSaveDefinitionRequest(API_OWNER, MULTI_UPLOAD_API_3_TITLE, MULTI_UPLOAD_API_3_VERSION, IS_PRIVATE, OAS3, YAML, SWAGGERHUB_API_TOKEN);

        UrlPathPattern saveSCMPluginConfigurationRequest1 = stubSaveSCMPluginConfigurationRequest(API_OWNER, MULTI_UPLOAD_API_1_TITLE, MULTI_UPLOAD_API_1_VERSION, OAS3, SWAGGERHUB_API_TOKEN);
        UrlPathPattern saveSCMPluginConfigurationRequest2 = stubSaveSCMPluginConfigurationRequest(API_OWNER, MULTI_UPLOAD_API_2_TITLE, MULTI_UPLOAD_API_2_VERSION, OAS2, SWAGGERHUB_API_TOKEN);
        UrlPathPattern saveSCMPluginConfigurationRequest3 = stubSaveSCMPluginConfigurationRequest(API_OWNER, MULTI_UPLOAD_API_3_TITLE, MULTI_UPLOAD_API_3_VERSION, OAS3, SWAGGERHUB_API_TOKEN);

        RequestPatternBuilder saveSCMPluginRequestPattern1 = createPutSCMConfigRequestPatternWithUsernamePassword(API_OWNER, MULTI_UPLOAD_API_1_TITLE, MULTI_UPLOAD_API_1_VERSION, OAS3, SCM_BRANCH,
                SCM_ENABLE_INTEGRATION, SCM_REPOSITORY, SCM_REPOSITORY_OWNER, MULTI_UPLOAD_API_1_FILENAME, DefinitionFileFormat.YAML.getLanguageTarget(), FILE_FINDER_DIRECTORY, SCM_USERNAME, SCM_PASSWORD);
        RequestPatternBuilder saveSCMPluginRequestPattern2 = createPutSCMConfigRequestPatternWithUsernamePassword(API_OWNER, MULTI_UPLOAD_API_2_TITLE, MULTI_UPLOAD_API_2_VERSION, OAS2, SCM_BRANCH,
                SCM_ENABLE_INTEGRATION, SCM_REPOSITORY, SCM_REPOSITORY_OWNER, MULTI_UPLOAD_API_2_FILENAME, DefinitionFileFormat.JSON.getLanguageTarget(), FILE_FINDER_DIRECTORY, SCM_USERNAME, SCM_PASSWORD);
        RequestPatternBuilder saveSCMPluginRequestPattern3 = createPutSCMConfigRequestPatternWithUsernamePassword(API_OWNER, MULTI_UPLOAD_API_3_TITLE, MULTI_UPLOAD_API_3_VERSION, OAS3, SCM_BRANCH,
                SCM_ENABLE_INTEGRATION, SCM_REPOSITORY, SCM_REPOSITORY_OWNER, MULTI_UPLOAD_API_3_FILENAME, DefinitionFileFormat.YAML.getLanguageTarget(), FILE_FINDER_DIRECTORY, SCM_USERNAME, SCM_PASSWORD);

        //When
        getSwaggerUpload("src/test/resources/testProjects/upload-multi-definitions-save-scm-plugins-with-username-password.xml").execute();

        //Then
        verify(1, postRequestedFor(uploadDefinitionRequest1));
        verify(1, postRequestedFor(uploadDefinitionRequest2));
        verify(1, postRequestedFor(uploadDefinitionRequest3));

        verify(1, putRequestedFor(saveSCMPluginConfigurationRequest1));
        verify(saveSCMPluginRequestPattern1);

        verify(1, putRequestedFor(saveSCMPluginConfigurationRequest2));
        verify(saveSCMPluginRequestPattern2);

        verify(1, putRequestedFor(saveSCMPluginConfigurationRequest3));
        verify(saveSCMPluginRequestPattern3);
    }

    @Test
    public void testMultiSaveDefinitionRequestMade_despiteSaveFailures() throws Exception {
        //Given
        UrlPathPattern uploadDefinitionRequest1 = stubSaveDefinitionRequest(API_OWNER, MULTI_UPLOAD_API_1_TITLE, MULTI_UPLOAD_API_1_VERSION, IS_PRIVATE, OAS3, YAML, SWAGGERHUB_API_TOKEN);
        UrlPathPattern uploadDefinitionRequest2 = stubSaveDefinitionRequest(API_OWNER, MULTI_UPLOAD_API_2_TITLE, MULTI_UPLOAD_API_2_VERSION, IS_PRIVATE, OAS2, JSON, SWAGGERHUB_API_TOKEN);
        UrlPathPattern uploadDefinitionRequest3 = stubSaveDefinitionRequest(API_OWNER, MULTI_UPLOAD_API_3_TITLE, MULTI_UPLOAD_API_3_VERSION, IS_PRIVATE, OAS3, YAML,SWAGGERHUB_API_TOKEN);

        //When
        getSwaggerUpload("src/test/resources/testProjects/upload-multi-definitions-save-github-plugins.xml").execute();

        //Then
        verify(1, postRequestedFor(uploadDefinitionRequest1));
        verify(1, postRequestedFor(uploadDefinitionRequest2));
        verify(1, postRequestedFor(uploadDefinitionRequest3));

    }

    @Test(expected = HttpHostConnectException.class)
    public void testBuildFails_whenSaveDefinitionFails_andFailuresArentSkipped() throws Exception {
        //Given
        stubSaveDefinitionRequest(SWAGGERHUB_API_TOKEN, API_OWNER, MULTI_UPLOAD_API_1_TITLE, MULTI_UPLOAD_API_1_VERSION, IS_PRIVATE, OAS3, YAML, badRequest());
        UrlPathPattern uploadDefinitionRequest2 = stubSaveDefinitionRequest(API_OWNER, MULTI_UPLOAD_API_2_TITLE, MULTI_UPLOAD_API_2_VERSION, IS_PRIVATE, OAS2, JSON, SWAGGERHUB_API_TOKEN);

        //When
        getSwaggerUpload("src/test/resources/testProjects/fail_build_on_failed_requests.xml").execute();

    }

    @Test
    public void testBuildFailsWhenInputFileUploadParametersArentSet() throws Exception {

        //Given

        //When
        boolean executionFailure = false;
        try {
            getSwaggerUpload("src/test/resources/testProjects/upload-input-file-missing-api-param.xml").execute();
        }catch (UploadParametersException e){
            e.printStackTrace();
            executionFailure = true;
        } catch (MojoExecutionException e) {
            executionFailure = false;
        }

        //Then
        assertTrue(executionFailure);
    }

    @Test
    public void testBuildFailsWhenDefinitionDirectoryUploadParametersArentSet() throws Exception {
        //Given

        //When
        boolean executionFailure = false;
        try {
            getSwaggerUpload("src/test/resources/testProjects/upload-multi-definitions-missing-directory-param.xml").execute();
        }catch (UploadParametersException e){
            e.printStackTrace();
            executionFailure = true;
        } catch (MojoExecutionException e) {
            executionFailure = false;
        }

        //Then
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
        String format = config.getChild("format").getValue();
        String isPrivate = config.getChild("isPrivate").getValue();

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

    private RequestPatternBuilder createPutSCMConfigRequestPatternWithToken(String owner, String api, String version, String oasVersion, String branch,
                                                                            String enabled, String repository, String repositoryOwner, String outputFile,
                                                                            String target, String outputFolder, String scmToken){

        return createPutSCMConfigRequestPatternBase(owner, api, version, oasVersion, branch, enabled, repository, repositoryOwner, outputFile, target, outputFolder)
                .withRequestBody(matchingJsonPath("$.token", equalTo(scmToken)));
    }

    private RequestPatternBuilder createPutSCMConfigRequestPatternWithUsernamePassword(String owner, String api, String version, String oasVersion, String branch,
                                                                            String enabled, String repository, String repositoryOwner, String outputFile,
                                                                            String target, String outputFolder, String username, String password){

        return createPutSCMConfigRequestPatternBase(owner, api, version, oasVersion, branch, enabled, repository, repositoryOwner, outputFile, target, outputFolder)
                .withRequestBody(matchingJsonPath("$.username", equalTo(username)))
                .withRequestBody(matchingJsonPath("$.password", equalTo(password)));

    }

    private RequestPatternBuilder createPutSCMConfigRequestPatternBase(String owner, String api, String version, String oasVersion, String branch,
                                                                                       String enabled, String repository, String repositoryOwner, String outputFile,
                                                                                       String target, String outputFolder){

        return putRequestedFor(urlPathEqualTo(String.format("/plugins/configurations/%s/%s/%s/GITHUB", owner, api, version)))
                .withQueryParam("oas", equalTo(oasVersion))
                .withRequestBody(matchingJsonPath("$.branch", equalTo(branch)))
                .withRequestBody(matchingJsonPath("$.enabled", equalTo(enabled)))
                .withRequestBody(matchingJsonPath("$.repository", equalTo(repository)))
                .withRequestBody(matchingJsonPath("$.owner", equalTo(repositoryOwner)))
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

    private SwaggerHubUpload getSwaggerUpload(String filePath) throws Exception {
        File pom = getTestFile(filePath);
        return (SwaggerHubUpload) lookupConfiguredMojo(pom, "upload");
    }
}
