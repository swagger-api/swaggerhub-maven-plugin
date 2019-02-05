package io.swagger.swaggerhub.plugin.services;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.swaggerhub.plugin.exceptions.DefinitionParsingException;
import org.apache.commons.lang3.StringUtils;


/**
 * Service used to fetch definition features
 */
public class DefinitionParserService {

    /**
     * Returns the API identifier by fetching title from the info section of a definition and normalizing.
     * Any whitespace or non-word characters are replaced by underscores
     * @param definition
     * @return
     */
    public String getApiId(JsonNode definition) throws DefinitionParsingException {

        String titleValue;
        try {
            titleValue = definition.get("info").get("title").asText();
        }catch (NullPointerException ne){
            throw new DefinitionParsingException("Unable to fetch a valid API ID", ne);
        }

        return titleValue.replaceAll("\\W", StringUtils.SPACE).trim().replaceAll("\\s","_");

    }

    /**
     * Returns the API version from the info section of a definition
     * @param definition
     * @return
     * @throws DefinitionParsingException
     */
    public String getVersion(JsonNode definition) throws DefinitionParsingException{
        String versionValue;
        try {
            versionValue = definition.get("info").get("version").asText();
        }catch (NullPointerException ne){
            throw new DefinitionParsingException("Unable to fetch the version", ne);
        }

        return versionValue.trim();
    }
}
