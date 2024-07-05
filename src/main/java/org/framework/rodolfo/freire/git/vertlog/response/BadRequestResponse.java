package org.framework.rodolfo.freire.git.vertlog.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


/**
 * Represents a HTTP 400 Bad Request response format for API responses.
 */

@Slf4j
@Setter
@Getter
@EqualsAndHashCode
public class BadRequestResponse {

    @Schema(description = "400", example = "400")
    private int status;
    @Schema(description = "Bad Request", example = "Bad Request")
    private String error;
    @Schema(description = "Invalid buy ID. ID must be a positive integer", example = "Invalid buy ID. ID must be a positive integer")
    private String message;
    @Schema(description = "/buy/0", example = "/buy/0")
    private String path;

    /**
     * Default constructor initializes the status and error fields for HTTP 400.
     */
    public BadRequestResponse() {
        this.status = 400;
        this.error = "Bad Request";
    }

    /**
     * Constructs a BadRequestResponse with a specific message and path.
     *
     * @param message Description of the bad request error.
     * @param path    Path where the bad request occurred.
     */
    public BadRequestResponse(String message, String path) {
        this();
        this.message = message;
        this.path = path;
    }
}

