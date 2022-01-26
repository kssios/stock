package br.com.api.stock.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDTO {

    private String productCode;
    private String description;
    private String productType;
    private BigDecimal costPrice;
    private BigDecimal quantity;
}
