package org.framework.rodolfo.freire.git.vertlog.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a buy object within the system. A buy is associated with a user and contains their buy orders.
 */

@Slf4j
@Setter
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(value = "DocumentBuy")
@Schema(title = "Buy")
public class Buy {

    /**
     * The unique identifier of the user associated with this buy.
     */
    @Schema(description = "Unique identifier of the user associated with this buy", example = "123")
    @JsonProperty("user_id")
    @Id
    private int userId;

    /**
     * The name of the user associated with this buy.
     */
    @Schema(description = "name of the user associated with this buy", example = "Sheldon Cooper")
    @JsonProperty("name")
    private String userName;

    /**
     * A collection of purchase orders associated with this buy.
     */
    @Schema(description = "List of purchase orders associated with this buy")
    @JsonProperty("orders")
    private Set<BuyOrder> orders;

    /**
     * Default constructor that initializes the orders set.
     */
    public Buy() {
        this.orders = new HashSet<>();
    }

    /**
     * Override constructor.
     */
    public Buy(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }


    /**
     * Adds a purchase order to the associated set of orders.
     *
     * @param order The BuyOrder object to be added.
     */
    public void addOrder(BuyOrder order) {
        orders.add(order);
    }

}
