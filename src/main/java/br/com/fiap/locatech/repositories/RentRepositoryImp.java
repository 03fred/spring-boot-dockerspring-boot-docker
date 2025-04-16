package br.com.fiap.locatech.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import br.com.fiap.locatech.entities.Rent;

@Repository
public class RentRepositoryImp implements RentRepository{

	private final JdbcClient jdbcClient;
	

	public RentRepositoryImp(JdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;
	}

	@Override
	public Optional<Rent> findById(Long id) {
		// TODO Auto-generated method stub
		return this.jdbcClient
				.sql("SELECT a.id, a.person_id, a.car_id , a.initial_date, a.final_date, a.total_value, p.person_name,"
						+ "p.cpf as person_cpf, c.model as car_model" 
						+ " FROM car_rental a"
						+ " INNER JOIN people p ON p.id = a.person_id"
						+ " INNER JOIN cars c ON c.id = a.car_id" 
						+ " WHERE a.id = :id")
				.param("id", id)
				.query(Rent.class)
				.optional();
	}
	
	@Override
	public List<Rent> findAll(int size, int offset) {
		return this.jdbcClient.sql("SELECT a.id, a.person_id, a.car_id , a.initial_date, a.final_date, a.total_value, p.person_name,"
				+ "p.cpf as person_cpf, c.model as car_model" 
				+ " FROM car_rental a"
				+ " INNER JOIN people p ON p.id = a.person_id"
				+ " INNER JOIN cars c ON c.id = a.car_id"
				+ " LIMIT :size OFFSET :offset")
				.param("size", size)
				.param("offset", offset)
				.query(Rent.class).list();

	}

	@Override
	public Integer save(Rent rent) {
		return this.jdbcClient.sql(
				"INSERT INTO car_rental(car_id, person_id, initial_date, final_date, total_value)  "
						+ "VALUES (:carId, :personId, :initialDate, :finalDate, :totalValue)")
				.param("carId", rent.getCarId())
				.param("personId", rent.getPersonId())
				.param("initialDate", rent.getInitialDate())
				.param("finalDate", rent.getFinalDate())
				.param("totalValue", rent.getTotalValue())
				.update();
	}

	@Override
	public Integer update(Rent rent, Long id) {
		return this.jdbcClient
				.sql("UPDATE car_rental SET car_id = :carId, car_model = carModel, person_cpf = :personCpf, "
						+ "person_name = :personName, initial_date = :initialDate, final_date = :finalDate ,"
						+ " total_value = :totalValue WHERE id = :id ")
				.param("carId", rent.getCarId())
				.param("carModel", rent.getCarModel())
				.param("personCpf", rent.getPersonCpf())
				.param("personName", rent.getPersonName())
				.param("initialDate", rent.getInitialDate())
				.param("finalDate", rent.getFinalDate())
				.param("totalValue", rent.getTotalValue())
				.param("id", rent.getId())
				.update();
	}

	@Override
	public Integer delete(Long id) {
		return this.jdbcClient.sql("DELETE car_rental WHERE ID = :id").param("id", id).update();

	}

}
