package io.swagger.swaggerhub.plugin.utils;

/**
 * Constant values to be used for Upload scenario testing.
 * Values here should match values using in test resources (filenames, test project configs, etc.)
 */
public class SwaggerHubUploadTestConstants {

    public static final int WIREMOCK_PORT = 8089;

    public static final String API_OWNER = "swaggerhubuser";
    public static final String API_NAME = "api";
    public static final String API_VERSION = "1.0.0";


    public static final String IS_PRIVATE = "false";
    public static final String SWAGGERHUB_API_TOKEN = "dUmMyTokEn.1234abc";

    public static final String SCM_BRANCH = "test-branch";
    public static final String SCM_ENABLE_INTEGRATION = "true";
    public static final String SCM_REPOSITORY = "repo";
    public static final String SCM_REPOSITORY_OWNER = "repo_owner";
    public static final String SCM_TOKEN = "scm-token";
    public static final String SCM_USERNAME = "scm-username";
    public static final String SCM_PASSWORD = "scm-password";
    public static final String SCM_ACCOUNT= "scm-account";
    public static final String SCM_PERSONAL_ACCESS_TOKEN = "scm-personal-access-token";
    public static final String SCM_PROJECT = "scm-project";
    public static final String SCM_URL = "http://localhost:1234";
    public static final String SCM_PROJECT_COLLECTION = "DefaultCollection";
    public static final String SCM_HOST = "default_host";

    public static final boolean SCM_INTEGRATION_ENABLED = true;
    public static final String SCM_INTEGRATION_OUTPUT_FILE = "outputFile";
    public static final String SCM_INTEGRATION_REPOSITORY = "repository";
    public static final String SCM_INTEGRATION_PROVIDER_GITHUB = "GITHUB";
    public static final String SCM_INTEGRATION_TARGET = "JSON (Unresolved)";
    public static final String SCM_INTEGRATION_SYNC_METHOD = "Advanced Sync";
    public static final String SCM_INTEGRATION_NAME = "Integration Name";
    public static final String SCM_INTEGRATION_OUTPUT_FOLDER = "output/folder";



    public static final String INPUT_FILE_API = "TestAPI";
    public static final String INPUT_FILE_API_VERSION = "1.1.0";
    public static final String INPUT_FILE_FILENAME = "TestAPI.json";

    public static final String MULTI_UPLOAD_API_1_TITLE = "Test_API_1_Title_YAML";
    public static final String MULTI_UPLOAD_API_2_TITLE = "Test_API_2_JSON";
    public static final String MULTI_UPLOAD_API_3_TITLE = "TEST_API_3_YML";
    public static final String MULTI_UPLOAD_API_1_VERSION = "1.0.1-SNAPSHOT";
    public static final String MULTI_UPLOAD_API_2_VERSION = "1.0.0";
    public static final String MULTI_UPLOAD_API_3_VERSION = "1.0.2-SNAPSHOT";
    public static final String MULTI_UPLOAD_API_1_FILENAME = "api-definition1.yaml";
    public static final String MULTI_UPLOAD_API_2_FILENAME = "api-definition2.json";
    public static final String MULTI_UPLOAD_API_3_FILENAME = "definition3.yml";

    public static final String OAS3 = "3.0.0";
    public static final String OAS2 = "2.0";
    public static final String YAML = "yaml";
    public static final String JSON = "json";

    public static final String FILE_FINDER_DIRECTORY = "src/test/resources/file-finder-test-definitions";
    public static final String TEST_RESOURCES_DIRECTORY = "src/test/resources";

}
