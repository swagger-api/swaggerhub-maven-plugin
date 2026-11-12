package io.swagger.swaggerhub.plugin;

import java.util.EnumSet;
import java.util.function.Predicate;

/**
 * Kind of definition stored in SwaggerHub. Determines the path segment used in SwaggerHub API urls.
 */
public enum DefinitionType {

    /** An API definition, served under {@code /apis}. */
    API("API", "apis"),
    /** A domain definition, served under {@code /domains}. */
    DOMAIN("domain", "domains");

    private String friendlyName;
    private String pathSegment;

    DefinitionType(String friendlyName, String pathSegment) {
        this.friendlyName = friendlyName;
        this.pathSegment = pathSegment;
    }

    /**
     * @return the name users give in the {@code definitionType} plugin parameter
     */
    public String getFriendlyName() {
        return friendlyName;
    }

    /**
     * @return the SwaggerHub API url path segment for this type
     */
    public String getPathSegment() {
        return pathSegment;
    }

    /**
     * Returns the type whose {@link #getFriendlyName() friendly name} matches the given input, ignoring case.
     *
     * @param userInputValue value of the {@code definitionType} plugin parameter, may be {@code null}
     * @return the matching type, or {@link #API} if the input is {@code null} or unrecognised
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
