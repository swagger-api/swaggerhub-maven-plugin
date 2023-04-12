[![Build Status](https://img.shields.io/jenkins/build.svg?jobUrl=https://jenkins.swagger.io/job/oss-swaggerhub-maven-plugin)](https://jenkins.swagger.io/view/OSS%20-%20Java/job/oss-swaggerhub-maven-plugin)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.swagger/swaggerhub-maven-plugin/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/io.swagger/swaggerhub-maven-plugin)
# swaggerhub-maven-plugin <img src="https://raw.githubusercontent.com/swagger-api/swagger.io/wordpress/images/assets/SW-logo-clr.png" height="50" align="right">
A simple Maven plugin to access [SwaggerHub](https://swaggerhub.com) hosting of [OpenAPI/Swagger](https://swagger.io/specification/) definitions within a Maven build process, using the [SwaggerHub API](https://api.swaggerhub.com).

## Features
* Download/upload API and domain definitions from/to SwaggerHub.
* Upload multiple API or domains at once.
* Authenticate with an API key for restricted operations (for example, to download private definitions).
* Automatically provision an SCM integration to update source control with changes made to definitions.
* Supports YAML and JSON format for definitions.
* Connects to SwaggerHub SaaS by default, with an optional configuration to point to a local SwaggerHub On-Premise instance.

SwaggerHub On-Premise users need v. 1.20.0 to provision SCM integrations via SwaggerHub Maven plugin.

## Table of contents
* [Example use cases](#example-use-cases)
  * [Code-first](#code-first)
  * [Design-first](#design-first)
* [Dependencies](#dependencies)
  * [Stable version](#stable-version)
  * [Snapshots](#snapshots)
* [Goals](#goals)
  * [`download`](#download)
    * [Parameters](#parameters)
  * [`upload`](#upload)
    * [Parameters](#parameters-1)
    * [Multi-upload considerations](#multi-upload-considerations)
    * [SCM integration provisioning considerations](#scm-integration-provisioning-considerations)
    * [Examples](#examples)

## Example use cases

The usage pattern depends on whether you use the [code-first or design-first](https://swaggerhub.com/blog/api-design/design-first-or-code-first-api-development/) approach.

### Code-first
1. Code your API implementation.
2. Automatically generate the API definition from the source code, for example, using [Swagger Core](https://github.com/swagger-api/swagger-core) [annotations](https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations) and [Swagger Maven plugin](https://github.com/swagger-api/swagger-core/tree/master/modules/swagger-maven-plugin). See the [Swagger Core wiki](https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Getting-started) to learn more.
3. Upload the generated API definition to SwaggerHub using the SwaggerHub Maven plugin.

### Design-first
1. Write your API definition on SwaggerHub.
2. Download the API definition using the SwaggerHub Maven plugin.
3. Pass the API definition to another Swagger tool, for example:
    - Use [Swagger Codegen Maven plugin](https://github.com/swagger-api/swagger-codegen/tree/master/modules/swagger-codegen-maven-plugin) to generate API server and client code.
    - Use [Swagger Inflector](https://github.com/swagger-api/swagger-inflector) to automatically wire up the API definition to the implementation and provide out-of-the-box mocking.

## Dependencies

### Stable version

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.swagger/swaggerhub-maven-plugin/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/io.swagger/swaggerhub-maven-plugin)

```xml
  <dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swaggerhub-maven-plugin</artifactId>
    <version>1.0.10</version>
  </dependency>
```

### Snapshots
Snapshots are available from the [Sonatype Nexus Snapshots](https://oss.sonatype.org/content/repositories/snapshots) repository. To use a snapshot version, add the following to the `<repositories>` section of your `pom.xml`:

```xml
<repository>
	<id>sonatype-snapshots</id>
	<name>Sonatype Snapshots</name>
	<url>https://oss.sonatype.org/content/repositories/snapshots</url>
	<snapshots>
		<enabled>true</enabled>
	</snapshots>
</repository>
```

## Goals
SwaggerHub Maven plugin provides two goals, `download` and `upload`.

### download

This goal downloads an API or domain definition from SwaggerHub to a local file as part of the default Maven build lifecycle.

```xml
    <plugin>
        <groupId>io.swagger</groupId>
        <artifactId>swaggerhub-maven-plugin</artifactId>
        <version>1.0.10</version>
        <executions>
            <execution>
                <phase>generate-resources</phase>
                <goals>
                    <goal>download</goal>
                </goals>
                <configuration>
                    <api>PetStoreAPI</api>
                    <owner>jsfrench</owner>
                    <version>1.0.0</version>
                    <outputFile>target/petStoreAPI.json</outputFile>
                </configuration>
            </execution>
        </executions>
    </plugin>
```

#### Parameters

Parameter | Description | Required? | Default
--------- | ----------- | -------- | -------
**`api`** | API or domain name (case-sensitive) | yes  | -
**`owner`** | Owner name (case-sensitive) | yes | -
**`version`** | API or domain version (case-sensitive) | yes | -
**`outputFile`** | The definition will be saved to this file | yes | -
**`token`** | SwaggerHub API key, required to access private definitions | no | -
**`definitionType`** | Definition type, `API` or `domain` | no | `API`
**`format`** | File format to download, `json` or `yaml` | no | `json`
**`host`** | SwaggerHub hostname. Use `api.swaggerhub.com` for SwaggerHub SaaS. | no | `api.swaggerhub.com`
**`protocol`** | SwaggerHub server protocol, `http` or `https` | no | `https`
**`port`** | SwaggerHub server port, typically 80 for `http` and 443 for `https` | no | 443
**`basepath`** | Base path prefix for SwaggerHub Registry API calls. Leave blank for SwaggerHub SaaS, use `v1` for SwaggerHub On-Premise. | no | -

### upload

This goal creates or updates one or more API or domain definitions on SwaggerHub. All definitions are saved in the `owner` account (organization or user), and the `token` owner must have permissions to create and update definitions in this account.

Additionally, there is the option of provisioning a SwaggerHub SCM integration which will allow changes made in SwaggerHub to be pushed back to source control.

There are two `uploadType` modes:

* `inputFile` - Upload a single API or domain definition.
* `directory` - Upload one or more definitions from the specified `definitionDirectory`, optionally filtered by a regular expression. All definitions must be of the same type - either all APIs or all domains.

#### Parameters

Common parameters:

Parameter | Description | Required? | Default
--------- | ----------- | -------- | -------
**`uploadType`** | Possible values: `inputFile` - upload a single API or domain; `directory` - upload multiple definitions stored in a directory | yes | -
**`owner`** | The account name (case-sensitive) to upload the definitions to | yes | -
**`token`** | SwaggerHub API key. The API key owner must have permissions to create and update definitions in the `owner` account | yes | -
**`definitionType`** | Definition type, `API` or `domain` | no | `API`
**`isPrivate`** | Specifies whether the uploaded definitions will be made public (`false`) or private (`true`) | no | `false`
**`skipFailures`** | Specifies whether a build should fail when errors are encountered | no | false
**`host`** | SwaggerHub hostname. Use `api.swaggerhub.com` for SwaggerHub SaaS. | no | `api.swaggerhub.com`
**`protocol`** | SwaggerHub server protocol, `http` or `https` | no | `https`
**`port`** | SwaggerHub server port, typically 80 for `http` and 443 for `https` | no | 443
**`basepath`** | Base path prefix for SwaggerHub Registry API calls. Leave blank for SwaggerHub SaaS, use `v1` for SwaggerHub On-Premise. | no | -

Additional parameters for `uploadType`=`inputFile`:

Parameter | Description | Required? | Default
--------- | ----------- | -------- | -------
**`api`** | The name of the API or domain to create or update (case-sensitive) | yes | -
**`version`** | Version to create or update (case-sensitive). If this version already exists, it must not be [published](https://app.swaggerhub.com/help/apis/publishing-api). | yes | -
**`inputFile`** | Local file containing the API or domain definition in the JSON or YAML format | yes | -
**`format`** | Definition format, `json` or `yaml` | no | `json`

Additional parameters for `uploadType`=`directory`:

Parameter | Description | Required? | Default
--------- | ----------- | -------- | -------
**`definitionDirectory`** | Directory containing the definitions to be uploaded to SwaggerHub. Note that subdirectories are not included in the upload. | yes | -
**`definitionFileNameRegex`** | [Regular expression](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html#sum) that specifies the files to be uploaded. This regex matches against file names without extensions. If not specified, all .json, .yaml and .yml files from the `definitionDirectory` will be uploaded. | no | -

Additional parameters for [SCM integration provisioning](#scm-integration-provisioning-considerations):

Parameter | Description | Required? | SCM Specific? | Default
--------- | ----------- | --------- | ---------------- | -------
**`scmProvider`** | SCM to create a SwaggerHub Integration for. | yes | - | -
**`scmToken`** | User generated API token to be used for SCM requests | no | `GITHUB` | - 
**`scmPersonalAccessToken`** | Similar to the above | no | `AZURE_DEVOPS_SERVICES` | - 
**`scmUsername`** | The account-name (case-sensitive) with which to authenticate | no | `BITBUCKET` | - 
**`scmPassword`** | The password to be used in conjunction with the above `scmUsername` | no | `BITBUCKET` | - 
**`repository`** | The repository to push SwaggerHub changes to | yes | - | - 
**`repositoryOwner`** | The SCM account which owns the above repository | no | `GITHUB`. `BITBUCKET` | - 
**`scmProject`** | Team Project which contains the target repository | no | `AZURE_DEVOPS_SERVICES` | - 
**`scmOrganization`** | Specific to Azure DevOps Services, the organization with which to synchronize | no | `AZURE_DEVOPS_SERVICES` | -
**`scmUrl`** | Host URL of the SCM | no | `AZURE_DEVOPS_SERVER` | -
**`scmHost`** | Similar to the above | no | `GITLAB` | -
**`scmProjectCollection`** | Project collection which contains the target repositories project | no | `AZURE_DEVOPS_SERVER` | DefaultCollection
**`enableScmIntegration`** | Specifies whether to enable the SCM integration. If enabled, SwaggerHub changes will be pushed automatically on save | no | - |true 
**`branch`** | The repository branch to push SwaggerHub changes to | no | - | SWAGGERHUB 

#### Multi-upload considerations

When using `uploadType`=`directory`, all definitions to be uploaded must be stored in the `definitionDirectory` (the directory itself, not subdirectories). The `definitionType` parameter specifies whether the files will be uploaded as APIs (default) or domains.

The plugin only processes files with the following extensions: `.yaml`, `.yml`, `.json`. Files with other extensions are ignored. The files must be valid JSON or YAML files.

By default, the plugin uploads all JSON and YAML files from the specified directory, but you can use `definitionFileNameRegex` to narrow down the files to be uploaded. The regular expression matches against file names *without file extensions*. The matching is partial unless the regex contains the `^` (beginning of line) and `$` (end of line) anchors. To make the matching case-insensitive, include `(?i)` at the beginning, or `(?iu)` for Unicode-aware case-insensitive matching. Examples:

* `acme` matches file names that contain "acme" in lower case.
* `^acme` matches file names that begin with "acme" in lower case.
* `^(?i)acme` matches file names that begin with "acme" in any letter case.

API/domain names and versions are generated by parsing the `info` section of the definitions. The `info` section must include non-empty `title` and `version` keys.

* Names are generated based on the `info.title`, with characters other than `A-Za-z0-9_` replaced with underscores. For example, a definition with `title: Sample API` will be saved under the name `Sample_API` on SwaggerHub.

* Versions are extracted from the `info.version` key. If this version already exists in SwaggerHub, it will be updated with the new definition (unless the version is published - in this case the update will be rejected).

If an error occurs while uploading any definition, the build will fail and subsequent definitions will not be uploaded.

### SCM integration provisioning considerations


* Supported SCM's include: `GITHUB`, `BITBUCKET`, `AZURE_DEVOPS_SERVICES`, `AZURE_DEVOPS_SERVER`, `GITLAB`

* SwaggerHub On-Premise supports this method of SCM integration provisioning since v. 1.20.0.

* Care should be taken when specifying SCM parameters. Validation does not take place prior to making the request to SwaggerHub and issues can arise due to incorrectly configured integrations

* Use only the parameters that are required for the SCM of your choice. For example `BITBUCKET` relies on `scmUsername` and `scmPassword`;
 if an `scmToken` is also included, the Bitbucket integration will attempt to authenticate with the token. This is not possible and will cause integration errors.
 
* `BITBUCKET` can use app passwords to authenticate. App passwords are substitute passwords for a user account which you can use for scripts and integrating tools to avoid putting your real password into configuration files.
App password permissions required are: 
    * **Account** : _Email, Read_
    * **Repositories** : _Read, Write_
    
    Documentation on how to generate an app password can be found [here](https://confluence.atlassian.com/bitbucket/app-passwords-828781300.html).

* `AZURE_DEVOPS_SERVICES` and `AZURE_DEVOPS_SERVER` use personal access tokens which will eventually expire. In the event of token expiring, the integration will need to be deleted and recreated (either manually or via the plugin).

    See Microsoft documentation here for details on creating a Personal Access Token. Please ensure that the token has at minimum the scope Code (read and write) for the organization you wish to access

* A list of `AZURE_DEVOPS_SERVICES` organizations that you currently have access to can be found [here](https://aex.dev.azure.com/me). Also listed are the projects for each organization.

    Enter just the organization, without ‘dev.azure.com’ at the beginning or ‘.visualstudio.com’ at the end. For example, if your organization is ‘dev.azure.com/example-user’ or 'example-user.visualstudio.com’, enter just 'example-user’.

* Due to `GITLAB`'s nested group support, when specifying `repository`  it is required to specify the full path. For example `<repository>root-level-group/sub-level-group/repo</repository>`

* `scmHost` will default to _https://gitlab.com_ for `GITLAB`

Further documentation of SwaggerHub Integrations can be found [here](https://app.swaggerhub.com/help/integrations/index).

#### Examples

##### Upload a single API definition

This example uploads the specified API definition in JSON format as a public API in SwaggerHub.

```xml
    <plugin>
        <groupId>io.swagger</groupId>
        <artifactId>swaggerhub-maven-plugin</artifactId>
        <version>1.0.10</version>
        <executions>
            <execution>
                <phase>deploy</phase>
                <goals>
                    <goal>upload</goal>
                </goals>
                <configuration>
                    <api>PetStoreAPI</api>
                    <owner>jsfrench</owner>
                    <version>1.0.0/version>
                    <inputFile>target/petStoreAPI.json</inputFile>
                    <token>${SWAGGERHUB_APIKEY}</token>
                    <uploadType>inputFile</uploadType>
                </configuration>
            </execution>
        </executions>
    </plugin>
```

##### Usage together with `swagger-maven-plugin` (code-first)
This example uses the [Swagger Maven plugin](https://github.com/swagger-api/swagger-core/tree/master/modules/swagger-maven-plugin) to generate the API definition from source code, and then uploads this definition to SwaggerHub.

```xml
    <plugin>
        <groupId>io.swagger.core.v3</groupId>
        <artifactId>swagger-maven-plugin</artifactId>
        <version>2.0.5</version>
        <configuration>
            <outputFileName>petStoreAPI</outputFileName>
            <outputPath>${project.build.directory}</outputPath>
            <outputFormat>JSON</outputFormat>
            <resourcePackages>
                <package>test.petstore</package>
            </resourcePackages>
            <prettyPrint>TRUE</prettyPrint>
        </configuration>
        <executions>
            <execution>
                <phase>compile</phase>
                <goals>
                    <goal>resolve</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
    <plugin>
        <groupId>io.swagger</groupId>
        <artifactId>swaggerhub-maven-plugin</artifactId>
        <version>1.0.10</version>
        <executions>
            <execution>
                <phase>deploy</phase>
                <goals>
                    <goal>upload</goal>
                </goals>
                <configuration>
                    <api>PetStoreAPI</api>
                    <owner>jsfrench</owner>
                    <version>1.0.0</version>
                    <inputFile>target/petStoreAPI.json</inputFile>
                    <token>${SWAGGERHUB_APIKEY}</token>
                    <uploadType>inputFile</uploadType>
                </configuration>
            </execution>
        </executions>
    </plugin>
```

##### Upload multiple API definitions

This example uploads all JSON and YAML files from the `${project.basedir}/api-definitions` directory to SwaggerHub.

```xml
    <plugin>
        <groupId>io.swagger</groupId>
        <artifactId>swaggerhub-maven-plugin</artifactId>
        <version>1.0.10</version>
        <executions>
            <execution>
                <phase>deploy</phase>
                <goals>
                    <goal>upload</goal>
                </goals>
                <configuration>
                    <owner>jsfrench</owner>
                    <token>${SWAGGERHUB_APIKEY}</token>
                    <uploadType>directory</uploadType>
                    <definitionDirectory>${project.basedir}/api-definitions</definitionDirectory>
                </configuration>
            </execution>
        </executions>
    </plugin>
```

##### Upload multiple API definitions filtered by a regex

This example uploads all JSON and YAML files from the specified directory whose names start with "definition".

```xml
    <plugin>
        <groupId>io.swagger</groupId>
        <artifactId>swaggerhub-maven-plugin</artifactId>
        <version>1.0.10</version>
        <executions>
            <execution>
                <phase>deploy</phase>
                <goals>
                    <goal>upload</goal>
                </goals>
                <configuration>
                    <owner>jsfrench</owner>
                    <token>${SWAGGERHUB_APIKEY}</token>
                    <uploadType>directory</uploadType>
                    <definitionDirectory>${project.basedir}/api-definitions</definitionDirectory>
                    <definitionFileNameRegex>^definition\w*</definitionDirectory>
                </configuration>
            </execution>
        </executions>
    </plugin>
```
##### Upload multiple API definitions via a specified directory and configure GitHub integrations for each definition
```xml
    <plugin>
        <groupId>io.swagger</groupId>
        <artifactId>swaggerhub-maven-plugin</artifactId>
        <version>1.0.10</version>
        <executions>
            <execution>
                <phase>deploy</phase>
                <goals>
                    <goal>upload</goal>
                </goals>
                <configuration>
                    <owner>jsfrench</owner>
                    <token>${SWAGGERHUB_APIKEY}</token>
                    <uploadType>directory</uploadType>
                    <definitionDirectory>${project.basedir}/api-definitions</definitionDirectory>
                    <scmToken>${GITHUB_APIKEY}</scmToken>
                    <scmProvider>GITHUB</scmProvider>
                    <repository>my_definitions_repository</repository>
                    <repositoryOwner>githubUser</repositoryOwner>
                    <branch>develop</branch>
                </configuration>
            </execution>
        </executions>
    </plugin>
```
