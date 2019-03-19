[![Build Status](https://img.shields.io/jenkins/s/https/jenkins.swagger.io/view/OSS%20-%20Java/job/oss-swaggerhub-maven-plugin.svg)](https://jenkins.swagger.io/view/OSS%20-%20Java/job/oss-swaggerhub-maven-plugin)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.swagger/swaggerhub-maven-plugin/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/io.swagger/swaggerhub-maven-plugin)
# swaggerhub-maven-plugin <img src="https://raw.githubusercontent.com/swagger-api/swagger.io/wordpress/images/assets/SW-logo-clr.png" height="50" align="right">
A simple Maven plugin to access [SwaggerHub](https://swaggerhub.com) hosting of [OpenAPI/Swagger](https://swagger.io/specification/) definitions within a Maven build process, using the [SwaggerHub API](https://api.swaggerhub.com).

## Features
* Download/upload API definitions from/to SwaggerHub Cloud.
* Upload multiple API definitions at once.
* Authenticate with an API key for restricted operations (for example, to download private definitions).
* Supports YAML and JSON format for API definitions.
* Automatically provision an SCM Integration to update source control with changes made to definitions.

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
    <version>1.0.2</version>
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

This goal downloads an API definition from SwaggerHub to a local file as part of the default Maven build lifecycle.

```xml
    <plugin>
        <groupId>io.swagger</groupId>
        <artifactId>swaggerhub-maven-plugin</artifactId>
        <version>1.0.3</version>
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
**`api`** | API name (case-sensitive) | yes  | -
**`owner`** | API owner (case-sensitive) | yes | -
**`version`** | API version (case-sensitive) | yes | -
**`outputFile`** | API definition will be saved to this file | yes | -
**`token`** | SwaggerHub API key, required to access private definitions | no | -
**`format`** | API definition format, `json` or `yaml` | no | `json`
**`host`**, **`protocol`**, **`port`** | Reserved for future use | no |

### upload

This goal creates or updates one or more API definitions on SwaggerHub. All definitions are saved in the `owner` account (organization or user), and the `token` owner must have permissions to create and update definitions in this account.

Additionally, there is the option of provisioning a SwaggerHub SCM integration which will allow changes made in SwaggerHub to be pushed back to source control.

There are two `uploadType` modes:

* `inputFile` - Upload a single API definition.
* `directory` - Upload one or more definitions from the specified `definitionDirectory`, optionally filtered by a regular expression.

#### Parameters

Common parameters:

Parameter | Description | Required? | Default
--------- | ----------- | -------- | -------
**`uploadType`** | Possible values: `inputFile` - upload a single API definition; `directory` - upload multiple definitions stored in a directory | yes | -
**`owner`** | The account name (case-sensitive) to upload the definitions to | yes | -
**`token`** | SwaggerHub API key. The API key owner must have permissions to create and update definitions in the `owner` account | yes | -
**`isPrivate`** | Specifies whether the uploaded APIs will be made public (`false`) or private (`true`) | no | `false`
**`skipFailures`** | Specifies whether a build should fail when errors are encountered | no | false
**`host`**, **`protocol`**, **`port`** | Reserved for future use | no |

Additional parameters for `uploadType`=`inputFile`:

Parameter | Description | Required? | Default
--------- | ----------- | -------- | -------
**`api`** | API name to create or update (case-sensitive) | yes | -
**`version`** | API version to create or update (case-sensitive). If this version already exists, it must not be [published](https://app.swaggerhub.com/help/apis/publishing-api). | yes | -
**`inputFile`** | Local file containing the API definition in the JSON or YAML format | yes | -
**`format`** | API definition format, `json` or `yaml` | no | `json`

Additional parameters for `uploadType`=`directory`:

Parameter | Description | Required? | Default
--------- | ----------- | -------- | -------
**`definitionDirectory`** | Directory containing the definitions to be uploaded to SwaggerHub. Note that subdirectories are not included in the upload. | yes | -
**`definitionFileNameRegex`** | [Regular expression](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html#sum) that specifies the files to be uploaded. This regex matches against file names without extensions. If not specified, all .json, .yaml and .yml files from the `definitionDirectory` will be uploaded. | no | -

Additional parameters for SCM Provisioning:

Parameter | Description | Required? | Default
--------- | ----------- | -------- | -------
**`scmProvider`** | SCM to create a SwaggerHub Integration for. | yes | -
**`scmToken`** | User generated API token to be used for SCM requests | yes | -
**`repository`** | The repository to push SwaggerHub changes to | yes | -
**`repositoryOwner`** | The SCM account which owns the above repository | yes | -
**`enableScmIntegration`** | Specifies whether to enable the SCM integration. If enabled, SwaggerHub changes will be pushed automatically on save | no | true
**`branch`** | The repository branch to push SwaggerHub changes to | no | SWAGGERHUB

#### Multi-upload considerations

When using `uploadType`=`directory`, all definitions to be uploaded must be stored in the `definitionDirectory` (the directory itself, not subdirectories). The plugin only processes files with the following extensions: `.yaml`, `.yml`, `.json`. Files with other extensions are ignored. The files must be valid JSON or YAML files.

By default, the plugin uploads all JSON and YAML files from the specified directory, but you can use `definitionFileNameRegex` to narrow down the files to be uploaded. The regular expression matches against file names *without file extensions*. The matching is partial unless the regex contains the `^` (beginning of line) and `$` (end of line) anchors. To make the matching case-insensitive, include `(?i)` at the beginning, or `(?iu)` for Unicode-aware case-insensitive matching. Examples:

* `acme` matches file names that contain "acme" in lower case.
* `^acme` matches file names that begin with "acme" in lower case.
* `^(?i)acme` matches file names that begin with "acme" in any letter case.

API names and versions are generated by parsing the `info` section of the definitions. The `info` section must include non-empty `title` and `version` keys.

* API names are generated based on the `info.title`, with characters other than `A-Za-z0-9_` replaced with underscores. For example, a definition with `title: Sample API` will be saved under the name `Sample_API` on SwaggerHub.

* API versions are extracted from the `info.version` key. If this API version already exists, it will be updated with the new definition (unless the version is published - in this case the update will be rejected).

If an error occurs while uploading any definition, the build will fail and subsequent definitions will not be uploaded.

#### Examples

##### Upload a single API definition

This example uploads the specified API definition in JSON format as a public API in SwaggerHub.

```xml
    <plugin>
        <groupId>io.swagger</groupId>
        <artifactId>swaggerhub-maven-plugin</artifactId>
        <version>1.0.3</version>
        <executions>
            <execution>
                <phase>deploy</phase>
                <goals>
                    <goal>upload</goal>
                </goals>
                <configuration>
                    <api>PetStoreAPI</api>
                    <owner>jsfrench</owner>
                    <version>1.0.1-SNAPSHOT</version>
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
        <version>1.0.3</version>
        <executions>
            <execution>
                <phase>deploy</phase>
                <goals>
                    <goal>upload</goal>
                </goals>
                <configuration>
                    <api>PetStoreAPI</api>
                    <owner>jsfrench</owner>
                    <version>1.0.1-SNAPSHOT</version>
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
        <version>1.0.3</version>
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
        <version>1.0.3</version>
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
        <version>1.0.3</version>
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