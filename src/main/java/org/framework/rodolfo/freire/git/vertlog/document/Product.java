package org.framework.rodolfo.freire.git.vertlog.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

/**
 * Represents a product within the system. Each product has a unique identifier and a value.
 */

@Slf4j
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Schema(title = "Product")
public class Product {

    /**
     * The unique identifier of this product.
     */
    @Schema(description = "Unique identifier of this product", example = "123")
    @JsonProperty("product_id")
    @Id
    private int productId;

    /**
     * The price of this product.
     */
    @Schema(description = "Price of this product", example = "1024.48")
    @JsonProperty("value")
    private double productValue;

}