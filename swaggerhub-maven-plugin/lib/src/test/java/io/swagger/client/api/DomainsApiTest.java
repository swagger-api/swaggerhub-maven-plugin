package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.model.ApisJson;
import io.swagger.client.model.Page;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for DomainsApi
 */
public class DomainsApiTest {

    private final DomainsApi api = new DomainsApi();

    
    /**
     * Deletes the specified domain
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteDomainTest() throws ApiException {
        String owner = null;
        String domain = null;
        Boolean force = null;
        // api.deleteDomain(owner, domain, force);

        // TODO: test validations
    }
    
    /**
     * Deletes a particular version of the specified domain
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteDomainVersionTest() throws ApiException {
        String owner = null;
        String domain = null;
        String version = null;
        Boolean force = null;
        // api.deleteDomainVersion(owner, domain, version, force);

        // TODO: test validations
    }
    
    /**
     * Retrieves the Swagger definition for the specified domain and version
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getDomainDefinitionTest() throws ApiException {
        String owner = null;
        String domain = null;
        String version = null;
        // Object response = api.getDomainDefinition(owner, domain, version);

        // TODO: test validations
    }
    
    /**
     * Retrieves the definition for the specified domain and version in JSON format
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getDomainJsonDefinitionTest() throws ApiException {
        String owner = null;
        String domain = null;
        String version = null;
        // Object response = api.getDomainJsonDefinition(owner, domain, version);

        // TODO: test validations
    }
    
    /**
     * Retrieves an APIs.json listing for all domain versions for this owner and domain
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getDomainVersionsTest() throws ApiException {
        String owner = null;
        String domain = null;
        // ApisJson response = api.getDomainVersions(owner, domain);

        // TODO: test validations
    }
    
    /**
     * Retrieves the definition for the specified domain and version in YAML format
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getDomainYamlDefinitionTest() throws ApiException {
        String owner = null;
        String domain = null;
        String version = null;
        // Object response = api.getDomainYamlDefinition(owner, domain, version);

        // TODO: test validations
    }
    
    /**
     * Retrieves an APIs.json listing of all domains defined for this owner
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getOwnerDomainsTest() throws ApiException {
        String owner = null;
        Integer page = null;
        Integer limit = null;
        String sort = null;
        String order = null;
        // ApisJson response = api.getOwnerDomains(owner, page, limit, sort, order);

        // TODO: test validations
    }
    
    /**
     * Saves the provided Swagger definition of a domain
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void saveDomainDefinitionTest() throws ApiException {
        String owner = null;
        String domain = null;
        String version = null;
        Boolean isPrivate = null;
        String definition = null;
        Boolean force = null;
        // api.saveDomainDefinition(owner, domain, version, isPrivate, definition, force);

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
     * Retrieves a list of currently defined domains in APIs.json format
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void searchDomainsTest() throws ApiException {
        String query = null;
        String state = null;
        List<String> tag = null;
        Integer page = null;
        Integer limit = null;
        String sort = null;
        String order = null;
        // api.searchDomains(query, state, tag, page, limit, sort, order);

        // TODO: test validations
    }
    
}
