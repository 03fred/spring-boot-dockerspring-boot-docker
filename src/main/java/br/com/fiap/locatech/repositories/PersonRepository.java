package br.com.fiap.locatech.repositories;

import java.util.List;
import java.util.Optional;

import br.com.fiap.locatech.entities.Person;

public interface PersonRepository {

	Optional<Person> findById(Long id);

	List<Person> findAll(int size, int offset);

	Integer save(Person person);

	Integer update(Person person, Long id);

	Integer delete(Long id);
}
