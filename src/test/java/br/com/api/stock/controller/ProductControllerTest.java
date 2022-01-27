package br.com.api.stock.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.api.stock.entity.Product;
import br.com.api.stock.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @LocalServerPort
    private int port;

    @MockBean
    private ProductRepository productRepository;

    private TestRestTemplate restTemplate;

    @BeforeEach
    public void init() {
        restTemplate = new TestRestTemplate();
        Product product = new Product("10", "Televisor 49", "Eletrônico", new BigDecimal("1500.41"), new BigDecimal("10"));
        when(productRepository.findById("10")).thenReturn(Optional.of(product));
    }

    @Test
    void find_login_ok() throws Exception {
        String expected = "{\"productCode\":\"10\",\"description\":\"Televisor 49\",\"productType\":\"Eletrônico\",\"costPrice\":1500.41,\"quantity\":10}";
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "user123")
                .getForEntity(new URL("http://localhost:" + port + "/products/10").toString(), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        JSONAssert.assertEquals(expected, response.getBody(), false);    }

    @Test
    void find_nologin_401() throws Exception {
        ResponseEntity<String> response = restTemplate
                .getForEntity(new URL("http://localhost:" + port + "/products/10").toString(), String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

}
