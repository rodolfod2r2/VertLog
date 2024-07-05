package org.framework.rodolfo.freire.git.vertlog.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Represents a HTTP 404 Not Found response format for API responses.
 */

@Slf4j
@Setter
@Getter
@EqualsAndHashCode
public class NotFoundResponse {

    @Schema(description = "404", example = "404")
    private int status;
    @Schema(description = "Not Found", example = "Not Found")
    private String error;
    @Schema(description = "Buy Not Found", example = "Buy Not Found")
    private String message;
    @Schema(description = "/buy/0", example = "/buy/0")
    private String path;

    /**
     * Default constructor initializes the status and error fields for HTTP 404.
     */
    public NotFoundResponse() {
        this.status = 404;
        this.error = "Not Found";
    }

    /**
     * Constructs a NotFoundResponse with a specific message and path.
     *
     * @param message Description of the not found error.
     * @param path    Path where the resource was not found.
     */
    public NotFoundResponse(String message, String path) {
        this();
        this.message = message;
        this.path = path;
    }

}
