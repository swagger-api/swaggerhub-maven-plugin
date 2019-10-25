package io.swagger.swaggerhub.plugin;

import java.util.EnumSet;
import java.util.function.Predicate;

public enum DefinitionType {

    API("API", "apis"),
    DOMAIN("domain", "domains");

    private String friendlyName;
    private String pathSegment;

    DefinitionType(String friendlyName, String pathSegment) {
        this.friendlyName = friendlyName;
        this.pathSegment = pathSegment;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public String getPathSegment() {
        return pathSegment;
    }

    /**
     * Returns the appropriate enum by matching the given input to the enums param value
     *
     * @param userInputValue
     * @return
     */
    public static DefinitionType getByParamValue(String userInputValue) {
        return EnumSet.allOf(DefinitionType.class).stream()
                .filter(doesTypeMatchUserInput(userInputValue))
                .findFirst()
                .orElse(API);
    }

    private static Predicate<DefinitionType> doesTypeMatchUserInput(String value) {
        return uploadType -> uploadType.getFriendlyName().equalsIgnoreCase(value);
    }

}
