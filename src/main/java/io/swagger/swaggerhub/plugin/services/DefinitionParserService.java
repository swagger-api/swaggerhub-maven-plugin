package io.swagger.swaggerhub.plugin.services;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.swaggerhub.plugin.exceptions.DefinitionParsingException;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;


/**
 * Service used to fetch features of a given API definition
 */
public class DefinitionParserService {

    /**
     * Returns the API identifier by fetching title from the info section of a definition and normalizing.
     * Any whitespace or non-word characters are replaced by underscores
     *
     * @param definition the parsed definition
     * @return the normalized API identifier
     * @throws DefinitionParsingException if the definition has no {@code info.title}
     */
    public static String getApiId(JsonNode definition) throws DefinitionParsingException {
        String titleValue;
        try {
            titleValue = definition.get("info").get("title").asText();
        }catch (NullPointerException ne){
            throw new DefinitionParsingException("Unable to fetch a valid API ID. Info and/or Title is missing from the given definition.", ne);
        }

        return titleValue.replaceAll("\\W", StringUtils.SPACE).trim().replaceAll("\\s","_");

    }

    /**
     * Returns the API version from the info section of a definition
     *
     * @param definition the parsed definition
     * @return the trimmed version
     * @throws DefinitionParsingException if the definition has no {@code info.version}
     */
    public static String getVersion(JsonNode definition) throws DefinitionParsingException {
        String versionValue;
        try {
            versionValue = definition.get("info").get("version").asText();
        }catch (NullPointerException ne){
            throw new DefinitionParsingException("Unable to fetch the version. Info and/or Title is missing from the given definition.", ne);
        }

        return versionValue.trim();
    }

    /**
     * Attempts to convert a given definition into a JSON node object.
     * If this is possible, the definition is potentially valid and can be parsed prior to upload.
     *
     * @param definition           the raw definition content
     * @param definitionFileFormat the format the definition is written in
     * @return the parsed definition
     * @throws DefinitionParsingException if the content cannot be read as the given format
     */
    public static JsonNode convertDefinitionToJsonNode(String definition, DefinitionFileFormat definitionFileFormat) throws DefinitionParsingException {
        try {
            return definitionFileFormat.getMapper().readTree(definition);
        }catch (IOException e){
            throw new DefinitionParsingException("Unable to parse definition prior to value extraction.", e);
        }
    }

    /**
     * Returns the OpenAPI/Swagger specification version of a definition, taken from its
     * {@code swagger} (OAS 2.0) or {@code openapi} (OAS 3.x) field.
     *
     * @param definition the parsed definition
     * @return the specification version, e.g. {@code 2.0} or {@code 3.0.0}
     * @throws DefinitionParsingException if the definition has neither field
     */
    public static String getOASVersion(JsonNode definition) throws DefinitionParsingException {

        if (definition.has("swagger")) {
            return definition.get("swagger").textValue();
        }else if (definition.has("openapi")){
            return definition.get("openapi").textValue();
        }else{
            throw new DefinitionParsingException("Unable to validate the OAS version of the definition.");
        }
    }

}
