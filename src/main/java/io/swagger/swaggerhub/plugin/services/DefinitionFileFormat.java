package io.swagger.swaggerhub.plugin.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.util.Json;
import io.swagger.util.Yaml;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Accepted file formats for API definition upload
 */
public enum DefinitionFileFormat {

    JSON(Json.mapper(), Arrays.asList("json"), "json", "JSON (Unresolved)"),
    YAML(Yaml.mapper(), Arrays.asList("yaml", "yml"), "yaml", "YAML (Unresolved)");

    private final String fileFormat;
    private ObjectMapper mapper;
    private List<String> supportedFileExtensions;
    private String languageTarget;

    DefinitionFileFormat(ObjectMapper mapper, List<String> supportedFileExtensions, String fileFormat, String languageTarget) {
        this.mapper = mapper;
        this.supportedFileExtensions = supportedFileExtensions;
        this.fileFormat = fileFormat;
        this.languageTarget = languageTarget;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public List<String> getSupportedFileExtensions() {
        return supportedFileExtensions;
    }

    public String getLanguageTarget() {
        return languageTarget;
    }

    public static Optional<DefinitionFileFormat> getByFileExtensionType(String value){
        return EnumSet.allOf(DefinitionFileFormat.class).stream()
                .filter(isFileExtensionSupportedForFileFormat(value))
                .findFirst();
    }

    private static Predicate<DefinitionFileFormat> isFileExtensionSupportedForFileFormat(String fileExtension) {
        return definitionFileFormat -> definitionFileFormat.getSupportedFileExtensions().contains(fileExtension);
    }

}
