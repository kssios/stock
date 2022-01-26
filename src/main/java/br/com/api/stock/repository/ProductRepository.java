package br.com.api.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.stock.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
