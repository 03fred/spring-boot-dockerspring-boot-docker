package br.com.fiap.locatech.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import br.com.fiap.locatech.entities.Person;

@Repository
public class PersonRepositoryImp implements PersonRepository{

	private final JdbcClient jdbcClient;

	public PersonRepositoryImp(JdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;
	}

	@Override
	public Optional<Person> findById(Long id) {
		// TODO Auto-generated method stub
		return this.jdbcClient.sql("SELECT * FROM people WHERE ID = :id").param("id", id).query(Person.class)
				.optional();
	}

	@Override
	public List<Person> findAll(int size, int offset) {
		return this.jdbcClient.sql("SELECT * FROM people LIMIT :size OFFSET :offset").param("size", size)
				.param("offset", offset).query(Person.class).list();

	}

	@Override
	public Integer save(Person person) {
		return this.jdbcClient.sql(
				"INSERT INTO people(person_name, phone, cpf, email)  VALUES (:name, :phone, :cpf, :email)")
				.param("name", person.getPersonName())
				.param("cpf", person.getCpf())
				.param("phone", person.getPhone())
				.param("email", person.getEmail())
			    .update();
	}

	@Override
	public Integer update(Person person, Long id) {
		return this.jdbcClient.sql(
				"UPDATE people SET person_name = :name, phone = :phone, cpf = :cpf, email = :email WHERE id = :id ")
				.param("name", person.getPersonName())
				.param("cpf", person.getCpf())
				.param("phone", person.getPhone())
				.param("email", person.getEmail())
				.param("id", person.getId())
				.update();
	}

	@Override
	public Integer delete(Long id) {
		return this.jdbcClient.sql("DELETE people WHERE ID = :id").param("id", id).update();

	}

}
