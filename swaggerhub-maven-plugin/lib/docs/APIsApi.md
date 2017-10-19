# APIsApi

All URIs are relative to *https://api.swaggerhub.com*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteApi**](APIsApi.md#deleteApi) | **DELETE** /apis/{owner}/{api} | Deletes the specified API
[**deleteApiVersion**](APIsApi.md#deleteApiVersion) | **DELETE** /apis/{owner}/{api}/{version} | Deletes a particular version of the specified API
[**deleteCollaboration**](APIsApi.md#deleteCollaboration) | **DELETE** /apis/{owner}/{api}/.collaboration | Deletes API&#39;s collaboration
[**getApiVersions**](APIsApi.md#getApiVersions) | **GET** /apis/{owner}/{api} | Retrieves an APIs.json listing for all API versions for this owner and API
[**getCollaboration**](APIsApi.md#getCollaboration) | **GET** /apis/{owner}/{api}/.collaboration | Gets API&#39;s collaboration
[**getDefinition**](APIsApi.md#getDefinition) | **GET** /apis/{owner}/{api}/{version} | Retrieves the Swagger definition for the specified API and version
[**getJsonDefinition**](APIsApi.md#getJsonDefinition) | **GET** /apis/{owner}/{api}/{version}/swagger.json | Retrieves the Swagger definition for the specified API and version in JSON format
[**getOwnerApis**](APIsApi.md#getOwnerApis) | **GET** /apis/{owner} | Retrieves an APIs.json listing of all APIs defined for this owner
[**getYamlDefinition**](APIsApi.md#getYamlDefinition) | **GET** /apis/{owner}/{api}/{version}/swagger.yaml | Retrieves the Swagger definition for the specified API and version in YAML format
[**saveDefinition**](APIsApi.md#saveDefinition) | **POST** /apis/{owner}/{api} | Saves the provided Swagger definition
[**searchApis**](APIsApi.md#searchApis) | **GET** /apis | Retrieves a list of currently defined APIs in APIs.json format.
[**searchApisAndDomains**](APIsApi.md#searchApisAndDomains) | **GET** /specs | Retrieves a list of currently defined APIs and Domains in APIs.json format
[**updateCollaboration**](APIsApi.md#updateCollaboration) | **PUT** /apis/{owner}/{api}/.collaboration | Updates API&#39;s collaboration


<a name="deleteApi"></a>
# **deleteApi**
> deleteApi(owner, api)

Deletes the specified API



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.APIsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

APIsApi apiInstance = new APIsApi();
String owner = "owner_example"; // String | API or Domaion owner identifier
String api = "api_example"; // String | API identifier
try {
    apiInstance.deleteApi(owner, api);
} catch (ApiException e) {
    System.err.println("Exception when calling APIsApi#deleteApi");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **owner** | **String**| API or Domaion owner identifier |
 **api** | **String**| API identifier |

### Return type

null (empty response body)

### Authorization

[TokenSecured](../README.md#TokenSecured)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="deleteApiVersion"></a>
# **deleteApiVersion**
> deleteApiVersion(owner, api, version)

Deletes a particular version of the specified API



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.APIsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

APIsApi apiInstance = new APIsApi();
String owner = "owner_example"; // String | API or Domaion owner identifier
String api = "api_example"; // String | API identifier
String version = "version_example"; // String | version identifier
try {
    apiInstance.deleteApiVersion(owner, api, version);
} catch (ApiException e) {
    System.err.println("Exception when calling APIsApi#deleteApiVersion");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **owner** | **String**| API or Domaion owner identifier |
 **api** | **String**| API identifier |
 **version** | **String**| version identifier |

### Return type

null (empty response body)

### Authorization

[TokenSecured](../README.md#TokenSecured)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="deleteCollaboration"></a>
# **deleteCollaboration**
> deleteCollaboration(owner, api)

Deletes API&#39;s collaboration

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.APIsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

APIsApi apiInstance = new APIsApi();
String owner = "owner_example"; // String | API or Domaion owner identifier
String api = "api_example"; // String | API identifier
try {
    apiInstance.deleteCollaboration(owner, api);
} catch (ApiException e) {
    System.err.println("Exception when calling APIsApi#deleteCollaboration");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **owner** | **String**| API or Domaion owner identifier |
 **api** | **String**| API identifier |

### Return type

null (empty response body)

### Authorization

[TokenSecured](../README.md#TokenSecured)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getApiVersions"></a>
# **getApiVersions**
> ApisJson getApiVersions(owner, api)

Retrieves an APIs.json listing for all API versions for this owner and API

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.APIsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

APIsApi apiInstance = new APIsApi();
String owner = "owner_example"; // String | API or Domaion owner identifier
String api = "api_example"; // String | API identifier
try {
    ApisJson result = apiInstance.getApiVersions(owner, api);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling APIsApi#getApiVersions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **owner** | **String**| API or Domaion owner identifier |
 **api** | **String**| API identifier |

### Return type

[**ApisJson**](ApisJson.md)

### Authorization

[TokenSecured](../README.md#TokenSecured)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getCollaboration"></a>
# **getCollaboration**
> Collaboration getCollaboration(owner, api, expandTeams)

Gets API&#39;s collaboration

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.APIsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

APIsApi apiInstance = new APIsApi();
String owner = "owner_example"; // String | API or Domaion owner identifier
String api = "api_example"; // String | API identifier
Boolean expandTeams = false; // Boolean | 
try {
    Collaboration result = apiInstance.getCollaboration(owner, api, expandTeams);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling APIsApi#getCollaboration");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **owner** | **String**| API or Domaion owner identifier |
 **api** | **String**| API identifier |
 **expandTeams** | **Boolean**|  | [optional] [default to false]

### Return type

[**Collaboration**](Collaboration.md)

### Authorization

[TokenSecured](../README.md#TokenSecured)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getDefinition"></a>
# **getDefinition**
> Object getDefinition(owner, api, version)

Retrieves the Swagger definition for the specified API and version



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.APIsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

APIsApi apiInstance = new APIsApi();
String owner = "owner_example"; // String | API or Domaion owner identifier
String api = "api_example"; // String | API identifier
String version = "version_example"; // String | version identifier
try {
    Object result = apiInstance.getDefinition(owner, api, version);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling APIsApi#getDefinition");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **owner** | **String**| API or Domaion owner identifier |
 **api** | **String**| API identifier |
 **version** | **String**| version identifier |

### Return type

**Object**

### Authorization

[TokenSecured](../README.md#TokenSecured)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, application/yaml

<a name="getJsonDefinition"></a>
# **getJsonDefinition**
> Object getJsonDefinition(owner, api, version)

Retrieves the Swagger definition for the specified API and version in JSON format



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.APIsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

APIsApi apiInstance = new APIsApi();
String owner = "owner_example"; // String | API or Domaion owner identifier
String api = "api_example"; // String | API identifier
String version = "version_example"; // String | version identifier
try {
    Object result = apiInstance.getJsonDefinition(owner, api, version);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling APIsApi#getJsonDefinition");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **owner** | **String**| API or Domaion owner identifier |
 **api** | **String**| API identifier |
 **version** | **String**| version identifier |

### Return type

**Object**

### Authorization

[TokenSecured](../README.md#TokenSecured)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getOwnerApis"></a>
# **getOwnerApis**
> ApisJson getOwnerApis(owner, page, limit, sort, order)

Retrieves an APIs.json listing of all APIs defined for this owner



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.APIsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

APIsApi apiInstance = new APIsApi();
String owner = "owner_example"; // String | API or Domaion owner identifier
Integer page = 0; // Integer | page to return
Integer limit = 10; // Integer | number of results per page
String sort = "NAME"; // String | sort criteria or result set * NAME - * UPATED * CREATED * OWNER 
String order = "ASC"; // String | sort order
try {
    ApisJson result = apiInstance.getOwnerApis(owner, page, limit, sort, order);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling APIsApi#getOwnerApis");
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

<a name="getYamlDefinition"></a>
# **getYamlDefinition**
> Object getYamlDefinition(owner, api, version)

Retrieves the Swagger definition for the specified API and version in YAML format



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.APIsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

APIsApi apiInstance = new APIsApi();
String owner = "owner_example"; // String | API or Domaion owner identifier
String api = "api_example"; // String | API identifier
String version = "version_example"; // String | version identifier
try {
    Object result = apiInstance.getYamlDefinition(owner, api, version);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling APIsApi#getYamlDefinition");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **owner** | **String**| API or Domaion owner identifier |
 **api** | **String**| API identifier |
 **version** | **String**| version identifier |

### Return type

**Object**

### Authorization

[TokenSecured](../README.md#TokenSecured)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/yaml

<a name="saveDefinition"></a>
# **saveDefinition**
> saveDefinition(owner, api, definition, isPrivate, version, force)

Saves the provided Swagger definition

Saves the provided Swagger definition; the owner must match the token owner. The version will be extracted from the Swagger definitions itself.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.APIsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

APIsApi apiInstance = new APIsApi();
String owner = "owner_example"; // String | API or Domaion owner identifier
String api = "api_example"; // String | API identifier
String definition = "definition_example"; // String | the Swagger definition of this API
Boolean isPrivate = false; // Boolean | Defines whether the API has to be private
String version = "version_example"; // String | api version
Boolean force = true; // Boolean | force update
try {
    apiInstance.saveDefinition(owner, api, definition, isPrivate, version, force);
} catch (ApiException e) {
    System.err.println("Exception when calling APIsApi#saveDefinition");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **owner** | **String**| API or Domaion owner identifier |
 **api** | **String**| API identifier |
 **definition** | **String**| the Swagger definition of this API |
 **isPrivate** | **Boolean**| Defines whether the API has to be private | [optional] [default to false]
 **version** | **String**| api version | [optional]
 **force** | **Boolean**| force update | [optional]

### Return type

null (empty response body)

### Authorization

[TokenSecured](../README.md#TokenSecured)

### HTTP request headers

 - **Content-Type**: application/json, application/yaml
 - **Accept**: application/json

<a name="searchApis"></a>
# **searchApis**
> searchApis(query, state, tag, page, limit, sort, order)

Retrieves a list of currently defined APIs in APIs.json format.



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.APIsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

APIsApi apiInstance = new APIsApi();
String query = "query_example"; // String | free text query to match
String state = "ALL"; // String | matches against published state of the spec * UNPUBLISHED - spec is a draft, a work in progress * PUBLISHED - spec is a stable version ready for consuming from client applications * ANY - either PUBLISHED or UNPUBLISHED 
List<String> tag = Arrays.asList("tag_example"); // List<String> | matches against tags associated with an API
Integer page = 0; // Integer | page to return
Integer limit = 10; // Integer | number of results per page
String sort = "NAME"; // String | sort criteria or result set * NAME - * UPATED * CREATED * OWNER 
String order = "ASC"; // String | sort order
try {
    apiInstance.searchApis(query, state, tag, page, limit, sort, order);
} catch (ApiException e) {
    System.err.println("Exception when calling APIsApi#searchApis");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **query** | **String**| free text query to match | [optional]
 **state** | **String**| matches against published state of the spec * UNPUBLISHED - spec is a draft, a work in progress * PUBLISHED - spec is a stable version ready for consuming from client applications * ANY - either PUBLISHED or UNPUBLISHED  | [optional] [default to ALL] [enum: ALL, PUBLISHED, UNPUBLISHED]
 **tag** | [**List&lt;String&gt;**](String.md)| matches against tags associated with an API | [optional]
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
//import io.swagger.client.api.APIsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

APIsApi apiInstance = new APIsApi();
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
    System.err.println("Exception when calling APIsApi#searchApisAndDomains");
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

<a name="updateCollaboration"></a>
# **updateCollaboration**
> updateCollaboration(owner, api, body)

Updates API&#39;s collaboration

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.APIsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: TokenSecured
ApiKeyAuth TokenSecured = (ApiKeyAuth) defaultClient.getAuthentication("TokenSecured");
TokenSecured.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//TokenSecured.setApiKeyPrefix("Token");

APIsApi apiInstance = new APIsApi();
String owner = "owner_example"; // String | API or Domaion owner identifier
String api = "api_example"; // String | API identifier
Collaboration body = new Collaboration(); // Collaboration | 
try {
    apiInstance.updateCollaboration(owner, api, body);
} catch (ApiException e) {
    System.err.println("Exception when calling APIsApi#updateCollaboration");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **owner** | **String**| API or Domaion owner identifier |
 **api** | **String**| API identifier |
 **body** | [**Collaboration**](Collaboration.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

[TokenSecured](../README.md#TokenSecured)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

