package io.swagger.swaggerhub.plugin.exceptions;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * Exception to be thrown when the parameters required for successful upload have not been configured correctly
 */
public class UploadParametersException extends MojoExecutionException {
    
    public UploadParametersException(String message, Exception cause) {
        super(message, cause);
    }

    public UploadParametersException(String message) {
        super(message);
    }
}
