package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.model.ApisJson;
import io.swagger.client.model.Collaboration;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for APIsApi
 */
public class APIsApiTest {

    private final APIsApi api = new APIsApi();

    
    /**
     * Deletes the specified API
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteApiTest() throws ApiException {
        String owner = null;
        String api = null;
        // api.deleteApi(owner, api);

        // TODO: test validations
    }
    
    /**
     * Deletes a particular version of the specified API
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteApiVersionTest() throws ApiException {
        String owner = null;
        String api = null;
        String version = null;
        // api.deleteApiVersion(owner, api, version);

        // TODO: test validations
    }
    
    /**
     * Deletes API&#39;s collaboration
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteCollaborationTest() throws ApiException {
        String owner = null;
        String api = null;
        // api.deleteCollaboration(owner, api);

        // TODO: test validations
    }
    
    /**
     * Retrieves an APIs.json listing for all API versions for this owner and API
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getApiVersionsTest() throws ApiException {
        String owner = null;
        String api = null;
        // ApisJson response = api.getApiVersions(owner, api);

        // TODO: test validations
    }
    
    /**
     * Gets API&#39;s collaboration
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getCollaborationTest() throws ApiException {
        String owner = null;
        String api = null;
        Boolean expandTeams = null;
        // Collaboration response = api.getCollaboration(owner, api, expandTeams);

        // TODO: test validations
    }
    
    /**
     * Retrieves the Swagger definition for the specified API and version
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getDefinitionTest() throws ApiException {
        String owner = null;
        String api = null;
        String version = null;
        // Object response = api.getDefinition(owner, api, version);

        // TODO: test validations
    }
    
    /**
     * Retrieves the Swagger definition for the specified API and version in JSON format
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getJsonDefinitionTest() throws ApiException {
        String owner = null;
        String api = null;
        String version = null;
        // Object response = api.getJsonDefinition(owner, api, version);

        // TODO: test validations
    }
    
    /**
     * Retrieves an APIs.json listing of all APIs defined for this owner
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getOwnerApisTest() throws ApiException {
        String owner = null;
        Integer page = null;
        Integer limit = null;
        String sort = null;
        String order = null;
        // ApisJson response = api.getOwnerApis(owner, page, limit, sort, order);

        // TODO: test validations
    }
    
    /**
     * Retrieves the Swagger definition for the specified API and version in YAML format
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getYamlDefinitionTest() throws ApiException {
        String owner = null;
        String api = null;
        String version = null;
        // Object response = api.getYamlDefinition(owner, api, version);

        // TODO: test validations
    }
    
    /**
     * Saves the provided Swagger definition
     *
     * Saves the provided Swagger definition; the owner must match the token owner. The version will be extracted from the Swagger definitions itself.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void saveDefinitionTest() throws ApiException {
        String owner = null;
        String api = null;
        String definition = null;
        Boolean isPrivate = null;
        String version = null;
        Boolean force = null;
        // api.saveDefinition(owner, api, definition, isPrivate, version, force);

        // TODO: test validations
    }
    
    /**
     * Retrieves a list of currently defined APIs in APIs.json format.
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void searchApisTest() throws ApiException {
        String query = null;
        String state = null;
        List<String> tag = null;
        Integer page = null;
        Integer limit = null;
        String sort = null;
        String order = null;
        // api.searchApis(query, state, tag, page, limit, sort, order);

        // TODO: test validations
    }
    
    /**
     * Retrieves a list of currently defined APIs and Domains in APIs.json format
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void searchApisAndDomainsTest() throws ApiException {
        String specType = null;
        String visibility = null;
        String state = null;
        String owner = null;
        String query = null;
        Integer page = null;
        Integer limit = null;
        String sort = null;
        String order = null;
        // ApisJson response = api.searchApisAndDomains(specType, visibility, state, owner, query, page, limit, sort, order);

        // TODO: test validations
    }
    
    /**
     * Updates API&#39;s collaboration
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateCollaborationTest() throws ApiException {
        String owner = null;
        String api = null;
        Collaboration body = null;
        // api.updateCollaboration(owner, api, body);

        // TODO: test validations
    }
    
}
