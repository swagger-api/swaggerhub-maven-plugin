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
 * Accepted file formats for API definition upload ({@code json}, {@code yaml}/{@code yml}).
 * Each format knows the Jackson mapper used to read it, its supported file extensions and the
 * language target SwaggerHub expects when creating an SCM integration for it.
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

    /**
     * @return mapper able to read definitions in this format
     */
    public ObjectMapper getMapper() {
        return mapper;
    }

    /**
     * @return format name as sent to SwaggerHub, e.g. {@code json}
     */
    public String getFileFormat() {
        return fileFormat;
    }

    /**
     * @return lower case file extensions, without dot, which hold this format
     */
    public List<String> getSupportedFileExtensions() {
        return supportedFileExtensions;
    }

    /**
     * @return the SwaggerHub SCM integration target, e.g. {@code JSON (Unresolved)}
     */
    public String getLanguageTarget() {
        return languageTarget;
    }

    /**
     * Finds the format which supports the given file extension (case sensitive).
     *
     * @param value file extension without dot, e.g. {@code yml}
     * @return the matching format, or empty if the extension is not supported
     */
    public static Optional<DefinitionFileFormat> getByFileExtensionType(String value){
        return EnumSet.allOf(DefinitionFileFormat.class).stream()
                .filter(isFileExtensionSupportedForFileFormat(value))
                .findFirst();
    }

    private static Predicate<DefinitionFileFormat> isFileExtensionSupportedForFileFormat(String fileExtension) {
        return definitionFileFormat -> definitionFileFormat.getSupportedFileExtensions().contains(fileExtension);
    }

}
