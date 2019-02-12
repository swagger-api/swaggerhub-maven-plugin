package io.swagger.swaggerhub.plugin;

import java.util.EnumSet;
import java.util.Optional;
import java.util.function.Predicate;

public enum DefinitionUploadType {

    INPUT_FILE("inputFile"),
    DIRECTORY("directory");

    private String paramValue;

    DefinitionUploadType(String paramValue){
        this.paramValue = paramValue;
    }

    public String getParamValue() {
        return paramValue;
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
