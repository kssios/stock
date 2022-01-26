package br.com.api.stock.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import br.com.api.stock.dto.ProductDTO;
import br.com.api.stock.service.converter.MovementConvert;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import br.com.api.stock.dto.MovementDTO;
import br.com.api.stock.entity.Movement;
import br.com.api.stock.entity.Product;
import br.com.api.stock.enumeration.OperationType;
import br.com.api.stock.exception.InsufficientStockException;
import br.com.api.stock.exception.MovementQuantityInvalidException;
import br.com.api.stock.exception.OperationTypeNotFoundException;
import br.com.api.stock.exception.ProductNotFoundException;
import br.com.api.stock.repository.MovementRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovementService {

    @NonNull
    private final MovementRepository movementRepository;
    @NonNull
    private final ProductService productService;
    @NonNull
    private final MovementConvert convert;

    public MovementDTO save(MovementDTO movementDTO) {

        String productCode = Optional.ofNullable(movementDTO.getProductCode()).orElseThrow(ProductNotFoundException::new);
        Product product = productService.findOptionalById(productCode).orElseThrow(ProductNotFoundException::new);
        checkStockBalance(movementDTO.getOperationTypeId(), product.getQuantity(), movementDTO.getQuantity());
        return convert.entityToDTO(movementRepository.save(convert.dtoToEntity(movementDTO, product)));
    }

    private void updateProductQuantity(ProductDTO productDTO, MovementDTO movementDTO) {

        BigDecimal newProductQuantity = OperationType.VENDA.getId() == movementDTO.getOperationTypeId() ? productDTO.getQuantity().subtract(movementDTO.getQuantity()) : productDTO.getQuantity().add(movementDTO.getQuantity());
        productDTO.setQuantity(newProductQuantity);
        productService.save(productDTO);
    }

    private void checkStockBalance(Integer operation, BigDecimal stockBalance, BigDecimal quantity) {
        if (OperationType.VENDA.getId() == operation && quantity.compareTo(stockBalance) > 0) {
            throw new InsufficientStockException();
        }
    }

}
