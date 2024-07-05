package org.framework.rodolfo.freire.git.vertlog.exception;

/**
 * A custom exception class extending RuntimeException to indicate that a requested resource could not be found.
 * This exception is typically thrown when the system attempts to access a resource (e.g., a buy object) that doesn't exist.
 *
 * @extends RuntimeException
 */

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param message The message explaining the reason why the resource was not found.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
