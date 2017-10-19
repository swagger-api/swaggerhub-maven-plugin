# swagger-java-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-java-client</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.swagger:swagger-java-client:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/swagger-java-client-1.0.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.APIsApi;

import java.io.File;
import java.util.*;

public class APIsApiExample {

    public static void main(String[] args) {
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
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *https://api.swaggerhub.com*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*APIsApi* | [**deleteApi**](docs/APIsApi.md#deleteApi) | **DELETE** /apis/{owner}/{api} | Deletes the specified API
*APIsApi* | [**deleteApiVersion**](docs/APIsApi.md#deleteApiVersion) | **DELETE** /apis/{owner}/{api}/{version} | Deletes a particular version of the specified API
*APIsApi* | [**deleteCollaboration**](docs/APIsApi.md#deleteCollaboration) | **DELETE** /apis/{owner}/{api}/.collaboration | Deletes API&#39;s collaboration
*APIsApi* | [**getApiVersions**](docs/APIsApi.md#getApiVersions) | **GET** /apis/{owner}/{api} | Retrieves an APIs.json listing for all API versions for this owner and API
*APIsApi* | [**getCollaboration**](docs/APIsApi.md#getCollaboration) | **GET** /apis/{owner}/{api}/.collaboration | Gets API&#39;s collaboration
*APIsApi* | [**getDefinition**](docs/APIsApi.md#getDefinition) | **GET** /apis/{owner}/{api}/{version} | Retrieves the Swagger definition for the specified API and version
*APIsApi* | [**getJsonDefinition**](docs/APIsApi.md#getJsonDefinition) | **GET** /apis/{owner}/{api}/{version}/swagger.json | Retrieves the Swagger definition for the specified API and version in JSON format
*APIsApi* | [**getOwnerApis**](docs/APIsApi.md#getOwnerApis) | **GET** /apis/{owner} | Retrieves an APIs.json listing of all APIs defined for this owner
*APIsApi* | [**getYamlDefinition**](docs/APIsApi.md#getYamlDefinition) | **GET** /apis/{owner}/{api}/{version}/swagger.yaml | Retrieves the Swagger definition for the specified API and version in YAML format
*APIsApi* | [**saveDefinition**](docs/APIsApi.md#saveDefinition) | **POST** /apis/{owner}/{api} | Saves the provided Swagger definition
*APIsApi* | [**searchApis**](docs/APIsApi.md#searchApis) | **GET** /apis | Retrieves a list of currently defined APIs in APIs.json format.
*APIsApi* | [**searchApisAndDomains**](docs/APIsApi.md#searchApisAndDomains) | **GET** /specs | Retrieves a list of currently defined APIs and Domains in APIs.json format
*APIsApi* | [**updateCollaboration**](docs/APIsApi.md#updateCollaboration) | **PUT** /apis/{owner}/{api}/.collaboration | Updates API&#39;s collaboration
*DomainsApi* | [**deleteDomain**](docs/DomainsApi.md#deleteDomain) | **DELETE** /domains/{owner}/{domain} | Deletes the specified domain
*DomainsApi* | [**deleteDomainVersion**](docs/DomainsApi.md#deleteDomainVersion) | **DELETE** /domains/{owner}/{domain}/{version} | Deletes a particular version of the specified domain
*DomainsApi* | [**getDomainDefinition**](docs/DomainsApi.md#getDomainDefinition) | **GET** /domains/{owner}/{domain}/{version} | Retrieves the Swagger definition for the specified domain and version
*DomainsApi* | [**getDomainJsonDefinition**](docs/DomainsApi.md#getDomainJsonDefinition) | **GET** /domains/{owner}/{domain}/{version}/domain.json | Retrieves the definition for the specified domain and version in JSON format
*DomainsApi* | [**getDomainVersions**](docs/DomainsApi.md#getDomainVersions) | **GET** /domains/{owner}/{domain} | Retrieves an APIs.json listing for all domain versions for this owner and domain
*DomainsApi* | [**getDomainYamlDefinition**](docs/DomainsApi.md#getDomainYamlDefinition) | **GET** /domains/{owner}/{domain}/{version}/domain.yaml | Retrieves the definition for the specified domain and version in YAML format
*DomainsApi* | [**getOwnerDomains**](docs/DomainsApi.md#getOwnerDomains) | **GET** /domains/{owner} | Retrieves an APIs.json listing of all domains defined for this owner
*DomainsApi* | [**saveDomainDefinition**](docs/DomainsApi.md#saveDomainDefinition) | **POST** /domains/{owner}/{domain} | Saves the provided Swagger definition of a domain
*DomainsApi* | [**searchApisAndDomains**](docs/DomainsApi.md#searchApisAndDomains) | **GET** /specs | Retrieves a list of currently defined APIs and Domains in APIs.json format
*DomainsApi* | [**searchDomains**](docs/DomainsApi.md#searchDomains) | **GET** /domains | Retrieves a list of currently defined domains in APIs.json format


## Documentation for Models

 - [AccessToken](docs/AccessToken.md)
 - [ApiMetadata](docs/ApiMetadata.md)
 - [ApiMetadataLink](docs/ApiMetadataLink.md)
 - [ApisJson](docs/ApisJson.md)
 - [ApisJsonApi](docs/ApisJsonApi.md)
 - [ApisJsonProperty](docs/ApisJsonProperty.md)
 - [ApisJsonUrlProperty](docs/ApisJsonUrlProperty.md)
 - [ApisJsonValueProperty](docs/ApisJsonValueProperty.md)
 - [ClosableComment](docs/ClosableComment.md)
 - [ClosableCommentPatch](docs/ClosableCommentPatch.md)
 - [CodegenLanguage](docs/CodegenLanguage.md)
 - [CodegenSettings](docs/CodegenSettings.md)
 - [Collaboration](docs/Collaboration.md)
 - [CollaborationHint](docs/CollaborationHint.md)
 - [CollaborationMember](docs/CollaborationMember.md)
 - [CollaborationMembership](docs/CollaborationMembership.md)
 - [CollaborationMembershipList](docs/CollaborationMembershipList.md)
 - [CollaborationRoles](docs/CollaborationRoles.md)
 - [CollaborationTeamMembership](docs/CollaborationTeamMembership.md)
 - [Comment](docs/Comment.md)
 - [CommentPatch](docs/CommentPatch.md)
 - [CommentsBatch](docs/CommentsBatch.md)
 - [Comparison](docs/Comparison.md)
 - [ComparisonDetail](docs/ComparisonDetail.md)
 - [ComparisonPart](docs/ComparisonPart.md)
 - [EntryId](docs/EntryId.md)
 - [GitHubExportSettings](docs/GitHubExportSettings.md)
 - [LifecycleSettings](docs/LifecycleSettings.md)
 - [MissingSpecMembers](docs/MissingSpecMembers.md)
 - [ModelPrivate](docs/ModelPrivate.md)
 - [NewComment](docs/NewComment.md)
 - [NewReply](docs/NewReply.md)
 - [NotCollaboratorProjectMembers](docs/NotCollaboratorProjectMembers.md)
 - [Page](docs/Page.md)
 - [PluginConfiguration](docs/PluginConfiguration.md)
 - [PluginDefinition](docs/PluginDefinition.md)
 - [Position](docs/Position.md)
 - [ProjectEntry](docs/ProjectEntry.md)
 - [ProjectMember](docs/ProjectMember.md)
 - [ProjectMemberList](docs/ProjectMemberList.md)
 - [ProjectsJson](docs/ProjectsJson.md)
 - [SimpleSpec](docs/SimpleSpec.md)
 - [SpecId](docs/SpecId.md)
 - [SystemPluginConfiguration](docs/SystemPluginConfiguration.md)
 - [Template](docs/Template.md)
 - [TemplateCatalog](docs/TemplateCatalog.md)
 - [User](docs/User.md)
 - [UserCredentials](docs/UserCredentials.md)
 - [VersionMetadata](docs/VersionMetadata.md)


## Documentation for Authorization

Authentication schemes defined for the API:
### TokenSecured

- **Type**: API key
- **API key parameter name**: Authorization
- **Location**: HTTP header


## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author

info@swaggerhub.com

