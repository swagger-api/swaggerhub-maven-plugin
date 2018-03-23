[![Build Status](https://travis-ci.org/swagger-api/swaggerhub-maven-plugin.svg)](https://travis-ci.org/swagger-api/swaggerhub-maven-plugin)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.swagger/swaggerhub-maven-plugin/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/io.swagger/swaggerhub-maven-plugin)
# swaggerhub-maven-plugin
A simple maven plugin to access [SwaggerHub](https:\\swaggerhub.com) hosting of [OpenAPI/Swagger](https://swagger.io/specification/) from a maven build process, primarily to integrate with other OpenAPI/Swagger maven tooling.

## Features
* download/upload API definitions from/to SwaggerHub.
* supports `json` and `yaml` format for API definitions.
* authenticate with API key for restricted operations (e.g downloading a private API definition). 
* connects to SwaggerHub cloud by default or local SwaggerHub instance through optional configuration.

The pattern of usage is likely to depend on whether a [code first or design first](https://swaggerhub.com/blog/api-design/design-first-or-code-first-api-development/) approach is followed.

## Example use cases

### Code First
1. Code API implementation.
2. Automatically generate API definition from implementation, e.g. via annotations from [swagger-core](https://github.com/swagger-api/swagger-core), or [JAX-RS Analyzer](https://github.com/sdaschner/jaxrs-analyzer-maven-plugin/).
3. Upload generated API definition to SwaggerHub with swaggerhub-maven-plugin.

### Design First
1. Write API definition (e.g. in Swagger Editor or SwaggerHub).
2. Download API definition with swaggerhub-maven-plugin.
3. Pass API definition to another Swagger tool e.g.
    - [swagger-codegen-maven-plugin](https://github.com/swagger-api/swagger-codegen/tree/master/modules/swagger-codegen-maven-plugin) to generate API client and resource classes.
    - [swagger-inflector](https://github.com/swagger-api/swagger-inflector) to automatically wire up the API definition to the implementation and provide out-of-the-box mocking.



## Goals
### download
#### Example Usage
* Download a public API definition in json format from SwaggerHub automatically as part of the default maven build lifecycle and save to a local file.
```xml
    <plugin>
        <groupId>io.swagger</groupId>
        <artifactId>swaggerhub-maven-plugin</artifactId>
        <version>1.0.1</version>
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
Parameter | Description | Required | Default
--------- | ----------- | --------- | -------
**`api`** | API name | true  | -
**`owner`** | API owner | true | -
**`version`** | API version | true | -  
**`outputFile`** | API definition is written to this file | true | - 
**`token`** | SwaggerHub API key, required to access private definitions | false | -
**`format`** | API definition format, `json` or `yaml` | false | `json`
**`host`** | URL of SwaggerHub API | false | `api.swaggerhub.com`
**`protocol`** | Protocol for SwaggerHub API,`http` or `https` | false | `https`
**`port`** | Port to access SwaggerHub API| false | `443`

***

### upload
#### Example Usage
* Upload an API definition in json format as a public API in SwaggerHub.
```xml
    <plugin>
        <groupId>io.swagger</groupId>
        <artifactId>swaggerhub-maven-plugin</artifactId>
        <version>1.0.1</version>
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
                </configuration>
            </execution>
        </executions>
    </plugin>
```
#### Parameters
Parameter | Description | Required | Default
--------- | ----------- | --------- | -------
**`api`** | API name | true  | -
**`owner`** | API owner | true | -
**`version`** | API version | true | -  
**`inputFile`** | Local file containing the API definition in json or yaml format  | true | - 
**`token`** | SwaggerHub API key | true | -
**`format`** | API definition format, `json` or `yaml` | false | `json`
**`isPrivate`** | Defines whether the API should be private on SwaggerHub (using `true` requires a paid plan). | false | `false`
**`host`** | URL of SwaggerHub API | false | `api.swaggerhub.com`
**`protocol`** | Protocol for SwaggerHub API,`http` or `https` | false | `https`
**`port`** | Port to access SwaggerHub API| false | `443`

 


