package org.framework.rodolfo.freire.git.vertlog.exception;

import org.springframework.expression.ParseException;

/**
 * A custom exception class extending ParseException to indicate errors during buy date parsing.
 * This exception is thrown when the system encounters issues while processing a String value as a buy date in the expected format.
 *
 * @extends ParseException
 */

public class BuyParseException extends ParseException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new BuyParseException with the specified detail message and error position.
     *
     * @param expressionString The String that failed to be parsed as a buy date.
     * @param position         The index position within the String where the parsing error occurred (optional).
     * @param message          The message explaining the reason for the parsing failure.
     */

    public BuyParseException(String expressionString, int position, String message) {
        super(expressionString, position, message);
    }

    /**
     * Constructs a new BuyParseException with the specified error position, message, and a cause.
     *
     * @param position The index position within the String where the parsing error occurred (optional).
     * @param message  The message explaining the reason for the parsing failure.
     * @param cause    The underlying cause (throwable) that led to this parsing exception (optional).
     */
    public BuyParseException(int position, String message, Throwable cause) {
        super(position, message, cause);
    }

    /**
     * Constructs a new BuyParseException with the specified error position and message.
     *
     * @param position The index position within the String where the parsing error occurred (optional).
     * @param message  The message explaining the reason for the parsing failure.
     */
    public BuyParseException(int position, String message) {
        super(position, message);
    }
}
