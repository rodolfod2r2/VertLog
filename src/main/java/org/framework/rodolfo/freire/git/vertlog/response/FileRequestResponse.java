package org.framework.rodolfo.freire.git.vertlog.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
@EqualsAndHashCode
public class FileRequestResponse {

    @Schema(description = "200", example = "200")
    private int status;
    @Schema(description = "Success", example = "Success")
    private String successful;
    @Schema(description = "Successful processing the file", example = "Successful processing the file")
    private String message;
    @Schema(description = "/buy/upload", example = "/buy/upload")
    private String path;

    /**
     * Default constructor initializes the status and error fields for HTTP 400.
     */
    public FileRequestResponse() {
        this.status = 200;
        this.successful = "Success";
    }

    /**
     * Constructs a FileRequestResponse with a specific message and path.
     *
     * @param message Description of the bad request error.
     * @param path    Path where the bad request occurred.
     */
    public FileRequestResponse(String message, String path) {
        this();
        this.message = message;
        this.path = path;
    }

}
