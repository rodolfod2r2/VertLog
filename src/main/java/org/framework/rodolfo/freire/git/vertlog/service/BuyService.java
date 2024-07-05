package org.framework.rodolfo.freire.git.vertlog.service;

import lombok.extern.slf4j.Slf4j;
import org.framework.rodolfo.freire.git.vertlog.document.Buy;
import org.framework.rodolfo.freire.git.vertlog.document.BuyOrder;
import org.framework.rodolfo.freire.git.vertlog.document.Product;
import org.framework.rodolfo.freire.git.vertlog.repository.BuyRepository;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;


/**
 * Service layer class responsible for managing `Buy` entities.
 * <p>
 * This class implements the `GenericsInterfaceService<Buy>` interface to provide basic CRUD (Create, Read, Update, Delete) operations for `Buy` entities.
 * It interacts with the `BuyRepository` to access and manipulate buy data in the underlying data source (e.g., database).
 *
 * @see Buy
 * @see GenericsInterfaceService
 * @see BuyRepository
 */

@Slf4j
@Service
public class BuyService implements GenericsInterfaceService<Buy> {

    /**
     * Injected dependency providing access to the `BuyRepository`.
     */
    private final BuyRepository buyRepository;
    private final FileService fileService;

    /**
     * Constructor that injects the `BuyRepository` dependency.
     *
     * @param buyRepository The repository providing access to `Buy` entities.
     * @param fileService
     */
    public BuyService(BuyRepository buyRepository, FileService fileService) {
        this.buyRepository = buyRepository;
        this.fileService = fileService;
    }

    /**
     * Inherited method from `GenericsInterfaceService`.
     *
     * @see GenericsInterfaceService#findAll()
     */
    @Override
    public List<Buy> findAll() {
        return buyRepository.findAll();
    }

    /**
     * Inherited method from `GenericsInterfaceService`.
     *
     * @see GenericsInterfaceService#findById(int)
     */
    @Override
    public Optional<Buy> findById(int id) {
        return buyRepository.findById(id);
    }

    /**
     * Finds buy orders within a specified date range (not implemented yet).
     * <p>
     * This method is intended to retrieve buy orders whose dates fall between the provided `ini` and `end` dates (inclusive).
     * However, the current implementation returns an empty `Optional`. You should replace this with your logic to query the repository based on dates.
     *
     * @param ini The start date of the search range.
     * @param end The end date of the search range.
     * @return An `Optional` object containing a list of `Buy` objects found within the date range (to be implemented).
     */
    public List<Buy> findBetweenDate(Date ini, Date end) {
        return buyRepository.findByDate(ini, end);
    }

    /**
     * Inherited method from `GenericsInterfaceService`.
     *
     * @see GenericsInterfaceService #save(Buy)
     */
    @Override
    public Buy save(Buy buy) {
        return buyRepository.save(buy);
    }


    /**
     * Uploads and processes a file containing buy data.
     *
     * @param inputStream The InputStream of the file to be uploaded and processed.
     */
    public void uploadFile(InputStream inputStream) {
        Map<Integer, Buy> buysMap = fileService.parserFileToMap(inputStream);
        saveProcessor(buysMap);
    }

    /**
     * Processes and saves a map of Buy objects.
     *
     * @param buysMap The map of Buy objects to be processed and saved.
     */
    public void saveProcessor(Map<Integer, Buy> buysMap) {
        if (!(buysMap == null)) {
            for (Buy buy : buysMap.values()) {
                log.info("User Id: {}\tName: {}", buy.getUserId(), buy.getUserName());
                for (BuyOrder buyOrder : buy.getOrders()) {
                    double total = 0.0;
                    log.info("\tBuy: {}\t\tDate: {}", buyOrder.getBuyId(), buyOrder.getDate());
                    List<Product> products = Collections.unmodifiableList(buyOrder.getProducts());
                    for (Product product : products) {
                        log.info("\tProduct: {}\tValue {}", product.getProductId(), product.getProductValue());
                        total += product.getProductValue();
                    }
                    buyOrder.setTotal(total);
                    log.info("\tTotal: {}", total);
                }
                save(buy);
            }
        } else {
            log.error("Error Read Map");
        }

    }

}
