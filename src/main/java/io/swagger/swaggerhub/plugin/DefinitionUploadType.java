package io.swagger.swaggerhub.plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Supported values of the {@code uploadType} plugin parameter, together with the plugin parameters that must be set for each.
 */
public enum DefinitionUploadType {

    /** Upload the single file given by {@code inputFile}. */
    INPUT_FILE("inputFile", Arrays.asList("api","version", "format", "inputFile")),
    /** Upload every matching definition file in {@code definitionDirectory}. */
    DIRECTORY("directory", Arrays.asList("definitionDirectory"));

    private String paramValue;
    private List<String> requiredFields;

    DefinitionUploadType(String paramValue, List<String> requiredFields){
        this.paramValue = paramValue;
        this.requiredFields = requiredFields;
    }

    /**
     * @return the value users give in the {@code uploadType} plugin parameter
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * @return names of the {@link SwaggerHubUpload} fields which must be non-empty for this upload type
     */
    public List<String> getRequiredFields() {
        return requiredFields;
    }

    /**
     * Returns the upload type whose {@link #getParamValue() param value} equals the given input (case sensitive).
     *
     * @param userInputValue value of the {@code uploadType} plugin parameter
     * @return the matching type, or empty if the input is unrecognised
     */
    public static Optional<DefinitionUploadType> getByParamValue(String userInputValue){

        return EnumSet.allOf(DefinitionUploadType.class).stream()
                .filter(doesUploadTypeMatchUserInput(userInputValue))
                .findFirst();
    }

    private static Predicate<DefinitionUploadType> doesUploadTypeMatchUserInput(String value) {
        return uploadType -> uploadType.getParamValue().equals(value);
    }

}
