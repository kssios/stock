package br.com.api.stock.service.converter;

import org.springframework.stereotype.Component;

import br.com.api.stock.dto.ProductDTO;
import br.com.api.stock.entity.Product;

@Component
public class ProductConverter {

    public Product dtoToEntity(final ProductDTO productDTO) {
        return Product.builder()
                .productCode(productDTO.getProductCode())
                .description(productDTO.getDescription())
                .productType(productDTO.getProductType())
                .costPrice(productDTO.getCostPrice())
                .quantity(productDTO.getQuantity())
                .build();
    }

    public ProductDTO entityToDTO(final Product product) {
        return ProductDTO.builder()
                .productCode(product.getProductCode())
                .description(product.getDescription())
                .productType(product.getProductType())
                .costPrice(product.getCostPrice())
                .quantity(product.getQuantity())
                .build();
    }

}
