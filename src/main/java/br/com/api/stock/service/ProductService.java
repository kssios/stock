package br.com.api.stock.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import br.com.api.stock.dto.ProductDTO;
import br.com.api.stock.entity.Product;
import br.com.api.stock.exception.InvalidProductCode;
import br.com.api.stock.exception.ProductNotFoundException;
import br.com.api.stock.repository.ProductRepository;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    @NonNull
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public ProductDTO findByProductCode(String productCode) {
        return findOptionalById(productCode).map(this::entityToDTO)
                .orElseThrow(ProductNotFoundException::new);
    }

    public Optional<Product> findOptionalById(String productCode) {
        return productRepository.findById(productCode);
    }

    public ProductDTO save(ProductDTO productDTO) {
        return entityToDTO(productRepository.save(dtoToEntity(productDTO)));
    }

    private Product dtoToEntity(final ProductDTO productDTO) {
        if (StringUtils.isEmpty(productDTO.getProductCode())) {
            throw new InvalidProductCode();
        }
        return Product.builder()
                .productCode(productDTO.getProductCode())
                .description(productDTO.getDescription())
                .productType(productDTO.getProductType())
                .costPrice(productDTO.getCostPrice())
                .quantity(productDTO.getQuantity())
                .build();
    }

    private ProductDTO entityToDTO(final Product product) {
        return ProductDTO.builder()
                .productCode(product.getProductCode())
                .description(product.getDescription())
                .productType(product.getProductType())
                .costPrice(product.getCostPrice())
                .quantity(product.getQuantity())
                .build();
    }

}
