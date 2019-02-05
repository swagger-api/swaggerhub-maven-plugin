package io.swagger.swaggerhub.plugin.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.util.Json;
import io.swagger.util.Yaml;

/**
 * Accepted file formats for API definition upload
 */
public enum DefinitionFileFormat {

    JSON(Json.mapper()),
    YAML(Yaml.mapper());

    private final ObjectMapper mapper;

    DefinitionFileFormat(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }
}
