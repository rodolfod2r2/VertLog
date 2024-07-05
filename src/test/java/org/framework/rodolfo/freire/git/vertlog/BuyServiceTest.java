package org.framework.rodolfo.freire.git.vertlog;

import org.framework.rodolfo.freire.git.vertlog.document.Buy;
import org.framework.rodolfo.freire.git.vertlog.repository.BuyRepository;
import org.framework.rodolfo.freire.git.vertlog.service.BuyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BuyServiceTest {

    @Mock
    private BuyRepository buyRepository;


    @InjectMocks
    private BuyService buyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        List<Buy> mockBuys = new ArrayList<>();
        mockBuys.add(new Buy());
        when(buyRepository.findAll()).thenReturn(mockBuys);
        List<Buy> result = buyService.findAll();
        assertEquals(mockBuys, result);
    }

    @Test
    void testFindById() {
        int validId = 1;
        Buy mockBuy = new Buy();
        mockBuy.setUserId(validId);
        when(buyRepository.findById(validId)).thenReturn(Optional.of(mockBuy));

        Optional<Buy> result = buyService.findById(validId);

        assertEquals(Optional.of(mockBuy), result);
    }

    @Test
    void testFindById_NotFound() {
        int invalidId = 0;
        when(buyRepository.findById(invalidId)).thenReturn(Optional.empty());

        Optional<Buy> result = buyService.findById(invalidId);

        assertEquals(Optional.empty(), result);
    }

    @Test
    void testFindBetweenDate() {
        Date startDate = new Date();
        Date endDate = new Date();
        List<Buy> mockBuys = new ArrayList<>();
        when(buyRepository.findByDate(startDate, endDate)).thenReturn(mockBuys);

        List<Buy> result = buyService.findBetweenDate(startDate, endDate);

        assertEquals(mockBuys, result);
    }

    @Test
    void testSave() {

        Buy buyToSave = new Buy();
        when(buyRepository.save(buyToSave)).thenReturn(buyToSave);

        Buy result = buyService.save(buyToSave);

        assertEquals(buyToSave, result);
    }
}
