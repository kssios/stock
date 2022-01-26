package br.com.api.stock.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.api.stock.entity.Movement;

public interface MovementRepository extends CrudRepository<Movement, Long> {

}
