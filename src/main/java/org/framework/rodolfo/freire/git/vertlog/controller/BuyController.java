package org.framework.rodolfo.freire.git.vertlog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.framework.rodolfo.freire.git.vertlog.document.Buy;
import org.framework.rodolfo.freire.git.vertlog.exception.BadRequestException;
import org.framework.rodolfo.freire.git.vertlog.exception.BuyParseException;
import org.framework.rodolfo.freire.git.vertlog.exception.ResourceNotFoundException;
import org.framework.rodolfo.freire.git.vertlog.response.BadRequestResponse;
import org.framework.rodolfo.freire.git.vertlog.response.FileRequestResponse;
import org.framework.rodolfo.freire.git.vertlog.response.InternalServerErrorResponse;
import org.framework.rodolfo.freire.git.vertlog.response.NotFoundResponse;
import org.framework.rodolfo.freire.git.vertlog.service.BuyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.framework.rodolfo.freire.git.vertlog.util.BuyParseDate.parseDate;
import static org.framework.rodolfo.freire.git.vertlog.util.BuyParseDate.parseDateApi;

/**
 * This class represents a controller responsible for handling operations related to purchases.
 * It receives HTTP requests, executes business operations through the {@link BuyService} service and returns
 * appropriate responses to customers.
 */

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@Tag(name = "Serviço de Pedidos")
@RequestMapping(value = "/api")
public class BuyController {

    final BuyService buyService;

    /**
     * Constructor that initializes the controller with an instance of {@link BuyService}.
     *
     * @param buyService Service responsible for business logic related to purchases.
     */

    public BuyController(BuyService buyService) {
        this.buyService = buyService;
    }

    /**
     * Retrieves all buy orders currently stored in the system.
     *
     * @return A ResponseEntity containing a list of Buy objects or an appropriate error response.
     * The list will be empty if no buy orders are found.
     * @throws ResourceNotFoundException (optional) This exception might be thrown by the underlying service
     *                                   (buyService.findAll) if no buys are found. (Note: The specific exception thrown depends on your service
     *                                   implementation.)
     * @throws Exception                 This generic exception catches any unexpected errors that occur during buy retrieval
     *                                   and logs the error for debugging purposes.
     */

    @Operation(description = "check all orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Buy.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = NotFoundResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @GetMapping(value = "/buy")
    public ResponseEntity<?> getAllBuy() {
        try {
            List<Buy> listBuy = buyService.findAll();
            if (listBuy.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new NotFoundResponse(HttpStatus.NOT_FOUND.name(), "No buys found"));
            }
            return ResponseEntity.ok(listBuy);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BadRequestResponse(HttpStatus.BAD_REQUEST.name(), "Resource not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new InternalServerErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                            "Error when searching for buys: "));
        }
    }

    /**
     * Retrieves a buy order by its identifier.
     *
     * @param id The unique identifier of the buy order to retrieve. (Path Variable)
     * @return A ResponseEntity containing the requested buy order or an appropriate error response.
     * @throws BadRequestException if the provided id is less than or equal to zero.
     */

    @Operation(description = "General Order Query Filtered by Order Identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Buy.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = NotFoundResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @GetMapping(value = "/buy/{id}")
    public ResponseEntity<?> getBuyById(@PathVariable int id) {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BadRequestResponse("Invalid buy ID. ID must be a positive integer", "/buy/" + id));
        }

        Optional<Buy> buyOptional;
        try {
            buyOptional = buyService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new InternalServerErrorResponse("Error when searching for buy", "/buy/" + id));
        }

        if (!buyOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new NotFoundResponse("Buy not found with ID: " + id, "/buy/" + id));
        }

        return ResponseEntity.ok(buyOptional.get());
    }


    /**
     * Retrieves buy orders within a specified date range.
     * This method searches for buy orders whose dates fall between the provided ini and end dates (inclusive).
     *
     * @param ini (PathVariable) The start date of the search range in the expected format (defined by parseDate).
     * @param end (PathVariable) The end date of the search range in the expected format (defined by parseDate).
     * @return A ResponseEntity containing a list of Buy objects found within the date range, or an appropriate error response.
     * An empty Optional will be returned if no buys are found within the specified date range.
     * A 400 (Bad Request) response will be returned if the provided dates are invalid.
     * @throws BuyParseException This exception might be thrown by the parseDate method if the input date strings cannot be parsed successfully. (Note: The specific exceptions thrown depend on your implementation of parseDate.)
     */

    @Operation(description = "Query Orders Filtered by Order Date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Buy.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = NotFoundResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @GetMapping(value = "/buy/date/{ini}/{end}")
    public ResponseEntity<?> getBuyBetweenDates(@PathVariable String ini, @PathVariable String end) {
        Date startDate;
        Date endDate;
        try {
            startDate = parseDateApi(ini);
            endDate = parseDateApi(end);
        } catch (BuyParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BadRequestResponse("Invalid date format", "/buy/date/" + ini + "/" + end));
        }
        List<Buy> buys;
        try {
            buys = buyService.findBetweenDate(startDate, endDate);
            if (!buys.isEmpty()) {
                return ResponseEntity.ok(buys);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new NotFoundResponse("No buys found within the specified date range",
                                "/buy/date/" + ini + "/" + end));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new InternalServerErrorResponse("Error when searching for buys", "/buy/date/" + ini + "/" + end));
        }


    }


    @Operation(description = "Uploaded file only .txt files are allowed")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = FileRequestResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = NotFoundResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @PostMapping(consumes = "multipart/form-data", value = "/upload")
    public ResponseEntity<?> uploadDocument(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                log.error("Uploaded file is empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new BadRequestResponse("Uploaded file is empty","/buy/upload/"));
            }
            if (!file.getOriginalFilename().toLowerCase().endsWith(".txt")) {
                log.error("Only .txt files are allowed");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new BadRequestResponse("Only .txt files are allowed","/buy/upload/"));
            }
            InputStream inputStream = file.getInputStream();
            buyService.uploadFile(inputStream);
            log.info("Successful processing the file");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new FileRequestResponse("Successful processing the file","/buy/upload/"));
        } catch (IOException e) {
            log.error("Failed to process uploaded file");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new InternalServerErrorResponse("Failed to process uploaded file", "/buy/upload/)"));
        } catch (Exception e) {
            log.error("Unexpected error during file upload");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new InternalServerErrorResponse("Unexpected error during file upload", "/buy/upload/)"));
        }
    }
}
