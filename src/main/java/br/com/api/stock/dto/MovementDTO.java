package br.com.api.stock.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
@Valid
public class MovementDTO {

    private Long id;
    private String productCode;
    private Integer operationTypeId;
    private BigDecimal price;
    private String eventDate;
    @NotNull(message = "Campo quantidade é obrigatório")
    private BigDecimal quantity;
}
