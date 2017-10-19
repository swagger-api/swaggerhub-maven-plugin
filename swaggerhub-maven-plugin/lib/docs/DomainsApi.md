# DomainsApi

All URIs are relative to *https://api.swaggerhub.com*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteDomain**](DomainsApi.md#deleteDomain) | **DELETE** /domains/{owner}/{domain} | Deletes the specified domain
[**deleteDomainVersion**](DomainsApi.md#deleteDomainVersion) | **DELETE** /domains/{owner}/{domain}/{version} | Deletes a particular version of the specified domain
[**getDomainDefinition**](DomainsApi.md#getDomainDefinition) | **GET** /domains/{owner}/{domain}/{version} | Retrieves the Swagger definition for the specified domain and version
[**getDomainJsonDefinition**](DomainsApi.md#getDomainJsonDefinition) | **GET** /domains/{owner}/{domain}/{version}/domain.json | Retrieves the definition for the specified domain and version in JSON format
[**getDomainVersions**](DomainsApi.md#getDomainVersions) | **GET** /domains/{owner}/{domain} | Retrieves an APIs.json listing for all domain versions for this owner and domain
[**getDomainYamlDefinition**](DomainsApi.md#getDomainYamlDefinition) | **GET** /domains/{owner}/{domain}/{version}/domain.yaml | Retrieves the definition for the specified domain and version in YAML format
[**getOwnerDomains**](DomainsApi.md#getOwnerDomains) | **GET** /domains/{owner} | Retrieves an APIs.json listing of all domains defined for this owner
[**saveDomainDefinition**](DomainsApi.md#saveDomainDefinition) | **POST** /domains/{owner}/{domain} | Saves the provided Swagger definition of a domain
[**searchApisAndDomains**](DomainsApi.md#searchApisAndDomains) | **GET** /specs | Retrieves a list of currently defined APIs and Domains in APIs.json format
[**searchDomains**](DomainsApi.md#searchDomains) | **GET** /domains | Retrieves a list of currently defined domains in APIs.json format


<a name="deleteDomain"></a>
# **deleteDomain**
> deleteDomain(owner, domain, force)

Deletes the specified domain

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DomainsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

DomainsApi apiInstance = new DomainsApi();
String owner = "owner_example"; // String | API or Domaion owner identifier
String domain = "domain_example"; // String | domain identifier
Boolean force = true; // Boolean | force update
try {
    apiInstance.deleteDomain(owner, domain, force);
} catch (ApiException e) {
    System.err.println("Exception when calling DomainsApi#deleteDomain");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **owner** | **String**| API or Domaion owner identifier |
 **domain** | **String**| domain identifier |
 **force** | **Boolean**| force update | [optional]

### Return type

null (empty response body)

### Authorization

[TokenSecured](../README.md#TokenSecured)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="deleteDomainVersion"></a>
# **deleteDomainVersion**
> deleteDomainVersion(owner, domain, version, force)

Deletes a particular version of the specified domain

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DomainsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

DomainsApi apiInstance = new DomainsApi();
String owner = "owner_example"; // String | API or Domaion owner identifier
String domain = "domain_example"; // String | domain identifier
String version = "version_example"; // String | version identifier
Boolean force = true; // Boolean | force update
try {
    apiInstance.deleteDomainVersion(owner, domain, version, force);
} catch (ApiException e) {
    System.err.println("Exception when calling DomainsApi#deleteDomainVersion");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **owner** | **String**| API or Domaion owner identifier |
 **domain** | **String**| domain identifier |
 **version** | **String**| version identifier |
 **force** | **Boolean**| force update | [optional]

### Return type

null (empty response body)

### Authorization

[TokenSecured](../README.md#TokenSecured)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getDomainDefinition"></a>
# **getDomainDefinition**
> Object getDomainDefinition(owner, domain, version)

Retrieves the Swagger definition for the specified domain and version

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DomainsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

DomainsApi apiInstance = new DomainsApi();
String owner = "owner_example"; // String | API or Domaion owner identifier
String domain = "domain_example"; // String | domain identifier
String version = "version_example"; // String | version identifier
try {
    Object result = apiInstance.getDomainDefinition(owner, domain, version);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DomainsApi#getDomainDefinition");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **owner** | **String**| API or Domaion owner identifier |
 **domain** | **String**| domain identifier |
 **version** | **String**| version identifier |

### Return type

**Object**

### Authorization

[TokenSecured](../README.md#TokenSecured)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, application/yaml

<a name="getDomainJsonDefinition"></a>
# **getDomainJsonDefinition**
> Object getDomainJsonDefinition(owner, domain, version)

Retrieves the definition for the specified domain and version in JSON format

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DomainsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

DomainsApi apiInstance = new DomainsApi();
String owner = "owner_example"; // String | API or Domaion owner identifier
String domain = "domain_example"; // String | domain identifier
String version = "version_example"; // String | version identifier
try {
    Object result = apiInstance.getDomainJsonDefinition(owner, domain, version);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DomainsApi#getDomainJsonDefinition");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **owner** | **String**| API or Domaion owner identifier |
 **domain** | **String**| domain identifier |
 **version** | **String**| version identifier |

### Return type

**Object**

### Authorization

[TokenSecured](../README.md#TokenSecured)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getDomainVersions"></a>
# **getDomainVersions**
> ApisJson getDomainVersions(owner, domain)

Retrieves an APIs.json listing for all domain versions for this owner and domain

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DomainsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

DomainsApi apiInstance = new DomainsApi();
String owner = "owner_example"; // String | API or Domaion owner identifier
String domain = "domain_example"; // String | domain identifier
try {
    ApisJson result = apiInstance.getDomainVersions(owner, domain);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DomainsApi#getDomainVersions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **owner** | **String**| API or Domaion owner identifier |
 **domain** | **String**| domain identifier |

### Return type

[**ApisJson**](ApisJson.md)

### Authorization

[TokenSecured](../README.md#TokenSecured)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getDomainYamlDefinition"></a>
# **getDomainYamlDefinition**
> Object getDomainYamlDefinition(owner, domain, version)

Retrieves the definition for the specified domain and version in YAML format

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DomainsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

DomainsApi apiInstance = new DomainsApi();
String owner = "owner_example"; // String | API or Domaion owner identifier
String domain = "domain_example"; // String | domain identifier
String version = "version_example"; // String | version identifier
try {
    Object result = apiInstance.getDomainYamlDefinition(owner, domain, version);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DomainsApi#getDomainYamlDefinition");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **owner** | **String**| API or Domaion owner identifier |
 **domain** | **String**| domain identifier |
 **version** | **String**| version identifier |

### Return type

**Object**

### Authorization

[TokenSecured](../README.md#TokenSecured)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/yaml

<a name="getOwnerDomains"></a>
# **getOwnerDomains**
> ApisJson getOwnerDomains(owner, page, limit, sort, order)

Retrieves an APIs.json listing of all domains defined for this owner

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DomainsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

DomainsApi apiInstance = new DomainsApi();
String owner = "owner_example"; // String | API or Domaion owner identifier
Integer page = 0; // Integer | page to return
Integer limit = 10; // Integer | number of results per page
String sort = "NAME"; // String | sort criteria or result set * NAME - * UPATED * CREATED * OWNER 
String order = "ASC"; // String | sort order
try {
    ApisJson result = apiInstance.getOwnerDomains(owner, page, limit, sort, order);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DomainsApi#getOwnerDomains");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **owner** | **String**| API or Domaion owner identifier |
 **page** | **Integer**| page to return | [optional] [default to 0]
 **limit** | **Integer**| number of results per page | [optional] [default to 10]
 **sort** | **String**| sort criteria or result set * NAME - * UPATED * CREATED * OWNER  | [optional] [default to NAME] [enum: NAME, UPDATED, CREATED, OWNER]
 **order** | **String**| sort order | [optional] [default to ASC] [enum: ASC, DESC]

### Return type

[**ApisJson**](ApisJson.md)

### Authorization

[TokenSecured](../README.md#TokenSecured)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="saveDomainDefinition"></a>
# **saveDomainDefinition**
> saveDomainDefinition(owner, domain, version, isPrivate, definition, force)

Saves the provided Swagger definition of a domain

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DomainsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

DomainsApi apiInstance = new DomainsApi();
String owner = "owner_example"; // String | API or Domaion owner identifier
String domain = "domain_example"; // String | domain identifier
String version = "version_example"; // String | domain version
Boolean isPrivate = false; // Boolean | Defines whether the API has to be private
String definition = "definition_example"; // String | the Swagger definition of this Domain
Boolean force = true; // Boolean | force update
try {
    apiInstance.saveDomainDefinition(owner, domain, version, isPrivate, definition, force);
} catch (ApiException e) {
    System.err.println("Exception when calling DomainsApi#saveDomainDefinition");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **owner** | **String**| API or Domaion owner identifier |
 **domain** | **String**| domain identifier |
 **version** | **String**| domain version |
 **isPrivate** | **Boolean**| Defines whether the API has to be private | [optional] [default to false]
 **definition** | **String**| the Swagger definition of this Domain | [optional]
 **force** | **Boolean**| force update | [optional]

### Return type

null (empty response body)

### Authorization

[TokenSecured](../README.md#TokenSecured)

### HTTP request headers

 - **Content-Type**: application/json, application/yaml
 - **Accept**: application/json

<a name="searchApisAndDomains"></a>
# **searchApisAndDomains**
> ApisJson searchApisAndDomains(specType, visibility, state, owner, query, page, limit, sort, order)

Retrieves a list of currently defined APIs and Domains in APIs.json format



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DomainsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

DomainsApi apiInstance = new DomainsApi();
String specType = "ANY"; // String | Type of Swagger specs to search * API - APIs only * DOMAIN - Domains only * ANY - Both APIs and Domains 
String visibility = "ANY"; // String | The visibility of a spec in SwaggerHub * PUBLIC - can be viewed by anyone * PRIVATE - can only be viewed by you or your Org and those that you are collaborating with or have shared it with * ANY - either PUBLIC or PRIVATE 
String state = "ALL"; // String | matches against published state of the spec * UNPUBLISHED - spec is a draft, a work in progress * PUBLISHED - spec is a stable version ready for consuming from client applications * ANY - either PUBLISHED or UNPUBLISHED 
String owner = "owner_example"; // String | API or Domaion owner identifier. Can be username or Organization name
String query = "query_example"; // String | free text query to match
Integer page = 0; // Integer | page to return
Integer limit = 10; // Integer | number of results per page
String sort = "NAME"; // String | sort criteria or result set * NAME - * UPATED * CREATED * OWNER 
String order = "ASC"; // String | sort order
try {
    ApisJson result = apiInstance.searchApisAndDomains(specType, visibility, state, owner, query, page, limit, sort, order);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DomainsApi#searchApisAndDomains");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **specType** | **String**| Type of Swagger specs to search * API - APIs only * DOMAIN - Domains only * ANY - Both APIs and Domains  | [optional] [default to ANY] [enum: API, DOMAIN, ANY]
 **visibility** | **String**| The visibility of a spec in SwaggerHub * PUBLIC - can be viewed by anyone * PRIVATE - can only be viewed by you or your Org and those that you are collaborating with or have shared it with * ANY - either PUBLIC or PRIVATE  | [optional] [default to ANY] [enum: PUBLIC, PRIVATE, ANY]
 **state** | **String**| matches against published state of the spec * UNPUBLISHED - spec is a draft, a work in progress * PUBLISHED - spec is a stable version ready for consuming from client applications * ANY - either PUBLISHED or UNPUBLISHED  | [optional] [default to ALL] [enum: ALL, PUBLISHED, UNPUBLISHED]
 **owner** | **String**| API or Domaion owner identifier. Can be username or Organization name | [optional]
 **query** | **String**| free text query to match | [optional]
 **page** | **Integer**| page to return | [optional] [default to 0]
 **limit** | **Integer**| number of results per page | [optional] [default to 10]
 **sort** | **String**| sort criteria or result set * NAME - * UPATED * CREATED * OWNER  | [optional] [default to NAME] [enum: NAME, UPDATED, CREATED, OWNER]
 **order** | **String**| sort order | [optional] [default to ASC] [enum: ASC, DESC]

### Return type

[**ApisJson**](ApisJson.md)

### Authorization

[TokenSecured](../README.md#TokenSecured)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="searchDomains"></a>
# **searchDomains**
> searchDomains(query, state, tag, page, limit, sort, order)

Retrieves a list of currently defined domains in APIs.json format

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DomainsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

DomainsApi apiInstance = new DomainsApi();
String query = "query_example"; // String | free text query to match
String state = "ALL"; // String | matches against published state of the spec * UNPUBLISHED - spec is a draft, a work in progress * PUBLISHED - spec is a stable version ready for consuming from client applications * ANY - either PUBLISHED or UNPUBLISHED 
List<String> tag = Arrays.asList("tag_example"); // List<String> | matches against tags associated with a domain
Integer page = 0; // Integer | page to return
Integer limit = 10; // Integer | number of results per page
String sort = "NAME"; // String | sort criteria or result set * NAME - * UPATED * CREATED * OWNER 
String order = "ASC"; // String | sort order
try {
    apiInstance.searchDomains(query, state, tag, page, limit, sort, order);
} catch (ApiException e) {
    System.err.println("Exception when calling DomainsApi#searchDomains");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **query** | **String**| free text query to match | [optional]
 **state** | **String**| matches against published state of the spec * UNPUBLISHED - spec is a draft, a work in progress * PUBLISHED - spec is a stable version ready for consuming from client applications * ANY - either PUBLISHED or UNPUBLISHED  | [optional] [default to ALL] [enum: ALL, PUBLISHED, UNPUBLISHED]
 **tag** | [**List&lt;String&gt;**](String.md)| matches against tags associated with a domain | [optional]
 **page** | **Integer**| page to return | [optional] [default to 0]
 **limit** | **Integer**| number of results per page | [optional] [default to 10]
 **sort** | **String**| sort criteria or result set * NAME - * UPATED * CREATED * OWNER  | [optional] [default to NAME] [enum: NAME, UPDATED, CREATED, OWNER]
 **order** | **String**| sort order | [optional] [default to ASC] [enum: ASC, DESC]

### Return type

null (empty response body)

### Authorization

[TokenSecured](../README.md#TokenSecured)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

