package br.com.api.stock.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import br.com.api.stock.dto.ProductDTO;
import br.com.api.stock.entity.Product;
import br.com.api.stock.exception.InvalidProductCode;
import br.com.api.stock.exception.ProductNotFoundException;
import br.com.api.stock.repository.ProductRepository;
import br.com.api.stock.service.converter.ProductConverter;
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
    @NonNull
    private final ProductConverter productConverter;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public ProductDTO findByProductCode(String productCode) {
        Optional<Product> product = findOptionalById(productCode);
        if(product.isEmpty()) {
            throw new ProductNotFoundException();
        }
        return productConverter.entityToDTO(product.get());
    }

    public Optional<Product> findOptionalById(String productCode) {
        return productRepository.findById(productCode);
    }

    public ProductDTO save(ProductDTO productDTO) {
        if (StringUtils.isEmpty(productDTO.getProductCode())) {
            throw new InvalidProductCode();
        }
        return productConverter.entityToDTO(productRepository.save(productConverter.dtoToEntity(productDTO)));
    }
}
