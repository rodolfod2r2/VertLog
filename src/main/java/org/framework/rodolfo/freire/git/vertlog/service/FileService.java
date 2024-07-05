package org.framework.rodolfo.freire.git.vertlog.service;

import lombok.extern.slf4j.Slf4j;
import org.framework.rodolfo.freire.git.vertlog.document.Buy;
import org.framework.rodolfo.freire.git.vertlog.document.BuyOrder;
import org.framework.rodolfo.freire.git.vertlog.document.Product;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.framework.rodolfo.freire.git.vertlog.util.BuyParseDate.parseDate;

/**
 * Service class responsible for parsing an input stream representing a file into a structured map of Buy objects.
 */

@Slf4j
@Service
public class FileService {

    /**
     * Parses the contents of an InputStream representing a file into a map of Buy objects.
     *
     * @param inputStream The InputStream to parse, representing a file containing buy data.
     * @return A map where keys are user IDs and values are Buy objects representing parsed data.
     */
    public Map<Integer, Buy> parserFileToMap(InputStream inputStream){

        Map<Integer, Buy> buysMap = new HashMap<>();
        Map<Integer, BuyOrder> buyOrdersMap = new HashMap<>();
        Map<Integer, Product> productsMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            log.info("Starting Mapping");
            String line;
            while ((line = reader.readLine()) != null) {
                int userId = Integer.parseInt(line.substring(0, 10).trim());
                String userName = line.substring(11, 55).trim();
                int buyId = Integer.parseInt(line.substring(56, 65).trim());
                int productId = Integer.parseInt(line.substring(66, 75).trim());
                double productValue = Double.parseDouble(line.substring(76, 86).trim());
                Date dateBuy = parseDate(line.substring(87, 95).trim());

                Buy buy = buysMap.getOrDefault(userId, new Buy());
                buy.setUserId(userId);
                buy.setUserName(userName);

                BuyOrder currentBuyOrder = buyOrdersMap.get(buyId);
                if (currentBuyOrder == null) {
                    currentBuyOrder = new BuyOrder();
                    currentBuyOrder.setBuyId(buyId);
                    currentBuyOrder.setTotal(0.0);
                    currentBuyOrder.setDate(dateBuy);
                    currentBuyOrder.setProducts(new ArrayList<>());
                    buyOrdersMap.put(buyId, currentBuyOrder);
                    buy.getOrders().add(currentBuyOrder);
                }

                Product product = productsMap.getOrDefault(productId, new Product());
                product.setProductId(productId);
                product.setProductValue(productValue);

                currentBuyOrder.getProducts().add(product);

                double currentTotal = currentBuyOrder.getTotal();
                double productValueDouble = product.getProductValue();
                currentBuyOrder.setTotal(currentTotal + productValueDouble);

                buysMap.put(userId, buy);
                productsMap.put(productId, product);
            }
            log.info("End Mapping");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buysMap;
    }

}
