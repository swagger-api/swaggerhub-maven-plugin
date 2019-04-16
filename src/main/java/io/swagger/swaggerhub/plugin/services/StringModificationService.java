package io.swagger.swaggerhub.plugin.services;

import org.apache.commons.lang3.StringUtils;

public class StringModificationService {

    private static final String ANY_CHAR_REGEX = ".";

    /**
     * Used to hide sensitive information which could potentially be printed to console or logs.
     * Only the first and last character should be revealed with all others in between being replaced by a given replacement.
     * Edge case behaviour where the length of the string is 2 or less has also been captured (it's very unlikely a sensitive string of 2 would exist)
     * @param sensitiveString
     * @param replacement
     * @return
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
