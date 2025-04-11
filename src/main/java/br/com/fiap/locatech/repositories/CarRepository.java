package br.com.fiap.locatech.repositories;

import java.util.List;
import java.util.Optional;

import br.com.fiap.locatech.entities.Car;

public interface CarRepository {

	Optional<Car> findById(Long id);

	List<Car> findAll(int size, int offset);

	Integer save(Car car, Long id);

	Integer update(Car car, Long id);

	Integer delete(Long id);
}
