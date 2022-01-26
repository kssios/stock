package br.com.api.stock.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.stock.dto.ProductDTO;
import br.com.api.stock.entity.Product;
import br.com.api.stock.exception.ProductNotFoundException;
import br.com.api.stock.service.ProductService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @NonNull
    private final ProductService productService;

    @GetMapping()
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(path = "/{productCode}")
    public ResponseEntity<Object> findByProductCode(@PathVariable String productCode) {
        try {
            return new ResponseEntity<>(productService.findByProductCode(productCode), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO newProduct(@RequestBody final ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO update(@RequestBody final ProductDTO productDTO) {
        return productService.save(productDTO);
    }
}
