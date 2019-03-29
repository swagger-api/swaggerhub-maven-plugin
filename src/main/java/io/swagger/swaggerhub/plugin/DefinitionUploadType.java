package io.swagger.swaggerhub.plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public enum DefinitionUploadType {

    INPUT_FILE("inputFile", Arrays.asList("api","version", "format", "inputFile")),
    DIRECTORY("directory", Arrays.asList("definitionDirectory"));

    private String paramValue;
    private List<String> requiredFields;

    DefinitionUploadType(String paramValue, List<String> requiredFields){
        this.paramValue = paramValue;
        this.requiredFields = requiredFields;
    }

    public String getParamValue() {
        return paramValue;
    }

    public List<String> getRequiredFields() {
        return requiredFields;
    }

    /**
     * Returns the appropriate enum by matching the given input to the enums param value
     * @param userInputValue
     * @return
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
