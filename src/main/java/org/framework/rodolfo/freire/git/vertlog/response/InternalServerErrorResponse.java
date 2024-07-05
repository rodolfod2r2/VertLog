package org.framework.rodolfo.freire.git.vertlog.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;


/**
 * Represents a HTTP 500 Internal Server Error response format for API responses.
 */

@Slf4j
@Setter
@Getter
@EqualsAndHashCode
public class InternalServerErrorResponse {

    @Schema(description = "500", example = "500")
    private int status;
    @Schema(description = "Internal Server Error", example = "Internal Server Error")
    private String error;
    @Schema(description = "Error when searching for buy", example = "Error when searching for buy")
    private String message;
    @Schema(description = "/buy/0", example = "/buy/0")
    private String path;

    /**
     * Default constructor initializes the status and error fields for HTTP 500.
     */
    public InternalServerErrorResponse() {
        this.status = 500;
        this.error = "Internal Server Error";
    }

    /**
     * Constructs an InternalServerErrorResponse with a specific message and path.
     *
     * @param message Description of the internal server error.
     * @param path    Path where the internal server error occurred.
     */
    public InternalServerErrorResponse(String message, String path) {
        this();
        this.message = message;
        this.path = path;
    }

}

