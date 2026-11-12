package io.swagger.swaggerhub.plugin.services;

import org.apache.commons.lang3.StringUtils;

/**
 * Service with helpers for modifying strings, e.g. before they are logged
 */
public class StringModificationService {

    private static final String ANY_CHAR_REGEX = ".";

    /**
     * Used to hide sensitive information which could potentially be printed to console or logs.
     * Only the first and last character are revealed, every character in between is replaced by the given replacement.
     * Short strings are handled as follows (it's very unlikely a sensitive string this short would exist):
     * a single character is fully replaced, and for two characters only the first one is revealed.
     *
     * @param sensitiveString the value to hide, may be {@code null}
     * @param replacement     text substituted for each hidden character
     * @return the obfuscated value, or an empty string if {@code sensitiveString} is {@code null} or empty
     */
    public static String obfuscateSensitiveString(String sensitiveString, String replacement){

        if(StringUtils.isEmpty(sensitiveString)){
            return "";
        }
        int stringLength = sensitiveString.length();
        if(stringLength == 1){
            return sensitiveString.replaceAll(ANY_CHAR_REGEX, replacement);
        }else if(stringLength == 2){
            return sensitiveString.substring(0,1)+sensitiveString.substring(1).replaceAll(ANY_CHAR_REGEX, replacement);
        }else{
            return sensitiveString.substring(0,1)
                    +sensitiveString.substring(1,stringLength-1).replaceAll(ANY_CHAR_REGEX, replacement)
                    +sensitiveString.substring(stringLength-1);
        }

    }
}
