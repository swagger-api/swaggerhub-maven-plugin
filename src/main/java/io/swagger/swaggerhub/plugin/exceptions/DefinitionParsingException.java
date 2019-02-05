package io.swagger.swaggerhub.plugin.exceptions;

/**
 * Thrown to indicate an error has occurred when parsing a given definition. This exception will prevent any further parsing of the definition.
 */
public class DefinitionParsingException extends Exception {

    /**
     * {@inheritDoc}
     */
    public DefinitionParsingException() {
    }

    /**
     * {@inheritDoc}
     */
    public DefinitionParsingException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public DefinitionParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     */
    public DefinitionParsingException(Throwable cause) {
        super(cause);
    }
}
