package org.framework.rodolfo.freire.git.vertlog.exception;

/**
 * A custom exception class thrown to indicate a bad request received by the system.
 * This exception is typically used when the client provides invalid input or violates a business rule during a request.
 *
 * @extends RuntimeException
 */

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new BadRequestException with the specified message.
     *
     * @param message The message explaining the reason for the bad request.
     */
    public BadRequestException(String message) {
        super(message);
    }
}
