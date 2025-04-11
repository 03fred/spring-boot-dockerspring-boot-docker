package br.com.fiap.locatech.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import br.com.fiap.locatech.entities.Car;

@Repository
public class CarRepositoryImp implements CarRepository {

	private final JdbcClient jdbcClient;

	public CarRepositoryImp(JdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;
	}

	@Override
	public Optional<Car> findById(Long id) {
		// TODO Auto-generated method stub
		return this.jdbcClient.sql("SELECT * FROM CAR WHERE ID = :id").param("id", id).query(Car.class).optional();
	}

	@Override
	public List<Car> findAll(int size, int offset) {
		return this.jdbcClient.sql("SELECT * FROM CAR LIMIT :size OFFSET :offset").param("size", size)
				.param("offsite", offset).query(Car.class).list();

	}

	@Override
	public Integer save(Car car, Long id) {
		return this.jdbcClient.sql(
				"INSERT INTO CAR(model, brand, carLicensePlate, year, color, dailyValue) VALUES (:model, :brand, :carLicensePlate, :year, :color, :dailyValue)")
				.param("model", car.getModel()).param("brand", car.getBrand())
				.param("carLicensePlate", car.getCarLicensePlate()).param("year", car.getYear())
				.param("color", car.getColor()).param("dailyValue", car.getDailyValue()).update();
	}

	@Override
	public Integer update(Car car, Long id) {
		return this.jdbcClient.sql(
				"UPDATE CAR SET model = :model, brand = :brand, carLicensePlate = :carLicensePlate, year = :year, color = :color, dailyValue = :dailyValue WHERE id = :id )")
				.param("model", car.getModel()).param("brand", car.getBrand())
				.param("carLicensePlate", car.getCarLicensePlate()).param("year", car.getYear())
				.param("color", car.getColor()).param("dailyValue", car.getDailyValue()).param("id", "id").update();
	}

	@Override
	public Integer delete(Long id) {
		return this.jdbcClient.sql("DELETE CAR WHERE ID = :id").param("id", id).update();

	}

}
