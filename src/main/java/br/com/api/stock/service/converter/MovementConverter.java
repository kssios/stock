package br.com.api.stock.service.converter;

import br.com.api.stock.dto.MovementDTO;
import br.com.api.stock.entity.Movement;
import br.com.api.stock.entity.Product;
import br.com.api.stock.enumeration.OperationType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MovementConverter {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Movement dtoToEntity(final MovementDTO movementDTO, Product product) {
        return Movement.builder()
                .productCode(product.getProductCode())
                .operationType(OperationType.findById(movementDTO.getOperationTypeId()))
                .price(movementDTO.getPrice())
                .eventDate(LocalDateTime.parse(movementDTO.getEventDate(), formatter))
                .quantity(movementDTO.getQuantity())
                .build();
    }

    public MovementDTO entityToDTO(final Movement movement) {
        return MovementDTO.builder()
                .id(movement.getId())
                .productCode(movement.getProductCode())
                .operationTypeId(movement.getOperationType().getId())
                .price(movement.getPrice())
                .eventDate(movement.getEventDate().toString())
                .quantity(movement.getQuantity())
                .build();
    }

}
