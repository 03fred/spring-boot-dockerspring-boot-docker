package br.com.fiap.locatech.repositories;

import java.util.List;
import java.util.Optional;

import br.com.fiap.locatech.entities.Rent;

public interface RentRepository {

	Optional<Rent> findById(Long id);

	List<Rent> findAll(int size, int offset);

	Integer save(Rent rent);

	Integer update(Rent rent, Long id);

	Integer delete(Long id);
}
