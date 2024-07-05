package org.framework.rodolfo.freire.git.vertlog.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Represents a purchase order associated with a buy. A purchase order contains details about the order itself and the products purchased.
 */

@Slf4j
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Schema(title = "Lista de Pedidos")
public class BuyOrder {

    /**
     * The unique identifier of this purchase order.
     */
    @Schema(description = "Unique identifier of this purchase order", example = "123")
    @Id
    @JsonProperty("order_id")
    private int buyId;

    /**
     * The total amount of the purchase order.
     */
    @Schema(description = "Total amount of the purchase order", example = "1024.48")
    @JsonProperty("total")
    private double total;

    /**
     * The date when the purchase order was placed.
     */
    @Schema(description = "Date when the purchase order was placed", example = "2021-12-01", type = "string", pattern = "yyyy-MM-dd")
    @JsonProperty("date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    /**
     * A list of products included in this purchase order.
     */
    @Schema(description = "List of products included in this purchase order")
    @JsonProperty("products")
    private List<Product> products;

}
