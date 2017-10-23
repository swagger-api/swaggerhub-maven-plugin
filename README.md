  # swaggerhub-maven-plugin
A maven plugin to download API definitions from and publish to SwaggerHub.
## Goals
### download
#### Parameters
* **api**: API name.
* **owner**": API owner.
* **version**: API version.
* **token**: SwaggerHub API key.
* **outputFile**: Swagger definition downloaded from SwaggerHub is written to this file.
### upload
#### Parameters
* **api**: API name.
* **owner**": API owner.
* **version**: API version.
* **token**: SwaggerHub API key.
* **inputFile**: Path to file containing Swagger definition to upload to SwaggerHub. 
