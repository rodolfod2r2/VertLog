package org.framework.rodolfo.freire.git.vertlog;

import org.framework.rodolfo.freire.git.vertlog.controller.BuyController;
import org.framework.rodolfo.freire.git.vertlog.document.Buy;
import org.framework.rodolfo.freire.git.vertlog.exception.BuyParseException;
import org.framework.rodolfo.freire.git.vertlog.exception.ResourceNotFoundException;
import org.framework.rodolfo.freire.git.vertlog.response.BadRequestResponse;
import org.framework.rodolfo.freire.git.vertlog.response.InternalServerErrorResponse;
import org.framework.rodolfo.freire.git.vertlog.response.NotFoundResponse;
import org.framework.rodolfo.freire.git.vertlog.service.BuyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class BuyControllerTest {

    @Mock
    private BuyService buyService;

    @InjectMocks
    private BuyController buyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllBuy_Success() {

        List<Buy> mockBuys = new ArrayList<>();
        mockBuys.add(new Buy());

        when(buyService.findAll()).thenReturn(mockBuys);


        ResponseEntity<?> response = buyController.getAllBuy();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBuys, response.getBody());
    }

    @Test
    void testGetAllBuy_EmptyList() {

        when(buyService.findAll()).thenReturn(new ArrayList<>());


        ResponseEntity<?> response = buyController.getAllBuy();


        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(new NotFoundResponse(HttpStatus.NOT_FOUND.name(), "No buys found"), response.getBody());
    }

    @Test
    void testGetAllBuy_ResourceNotFoundException() {

        when(buyService.findAll()).thenThrow(new ResourceNotFoundException("No buys found"));

        ResponseEntity<?> response = buyController.getAllBuy();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(new BadRequestResponse(HttpStatus.BAD_REQUEST.name(), "Resource not found"), response.getBody());
    }

    @Test
    void testGetAllBuy_InternalServerError() {
        when(buyService.findAll()).thenThrow(new RuntimeException("Simulated error"));

        ResponseEntity<?> response = buyController.getAllBuy();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(new InternalServerErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                "Error when searching for buys: "), response.getBody());
    }

    @Test
    void testGetBuyById_ValidId() {

        int validId = 1;
        Buy mockBuy = new Buy();
        mockBuy.setUserId(validId);

        when(buyService.findById(validId)).thenReturn(Optional.of(mockBuy));


        ResponseEntity<?> response = buyController.getBuyById(validId);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBuy, response.getBody());
    }

    @Test
    void testGetBuyById_InvalidId() {

        int invalidId = 0;


        ResponseEntity<?> response = buyController.getBuyById(invalidId);


        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(new BadRequestResponse("Invalid buy ID. ID must be a positive integer", "/buy/" + invalidId), response.getBody());
    }

    @Test
    void testGetBuyById_NotFound() {

        int validId = 1;

        when(buyService.findById(validId)).thenReturn(Optional.empty());


        ResponseEntity<?> response = buyController.getBuyById(validId);


        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(new NotFoundResponse("Buy not found with ID: " + validId, "/buy/" + validId), response.getBody());
    }

    @Test
    void testGetBuyById_InternalServerError() {
        int validId = 1;

        when(buyService.findById(validId)).thenThrow(new RuntimeException("Simulated error"));

        ResponseEntity<?> response = buyController.getBuyById(validId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(new InternalServerErrorResponse("Error when searching for buy", "/buy/" + validId), response.getBody());
    }

    @Test
    void testGetBuyBetweenDates_Success() throws BuyParseException {
        String ini = "2023-01-01";
        String end = "2023-01-31";

        when(buyService.findBetweenDate(any(Date.class), any(Date.class))).thenReturn(new ArrayList<>());

        ResponseEntity<?> response = buyController.getBuyBetweenDates(ini, end);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(new NotFoundResponse("No buys found within the specified date range",
                "/buy/date/" + ini + "/" + end), response.getBody());
    }

    @Test
    void testGetBuyBetweenDates_BadRequest() throws BuyParseException {
        String ini = "2023-01-01";
        String end = "invalid-date";

        ResponseEntity<?> response = buyController.getBuyBetweenDates(ini, end);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(new BadRequestResponse("Invalid date format", "/buy/date/" + ini + "/" + end), response.getBody());
    }

    @Test
    void testGetBuyBetweenDates_InternalServerError() throws BuyParseException {
        String ini = "2023-01-01";
        String end = "2023-01-31";

        doThrow(new RuntimeException("Simulated error")).when(buyService).findBetweenDate(any(Date.class), any(Date.class));

        ResponseEntity<?> response = buyController.getBuyBetweenDates(ini, end);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(new InternalServerErrorResponse("Error when searching for buys", "/buy/date/" + ini + "/" + end), response.getBody());
    }


}

