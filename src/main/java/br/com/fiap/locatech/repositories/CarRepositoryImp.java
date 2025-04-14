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
		return this.jdbcClient.sql("SELECT * FROM cars WHERE ID = :id").param("id", id).query(Car.class).optional();
	}

	@Override
	public List<Car> findAll(int size, int offset) {
		return this.jdbcClient.sql("SELECT * FROM cars LIMIT :size OFFSET :offset").param("size", size)
				.param("offset", offset).query(Car.class).list();

	}

	@Override
	public Integer save(Car car) {
		return this.jdbcClient.sql(
				"INSERT INTO cars(model, brand, car_license_plate, car_year, color, daily_value)  VALUES (:model, :brand, :carLicensePlate, :year, :color, :dailyValue)")
				.param("model", car.getModel())
				.param("brand", car.getBrand())
				.param("carLicensePlate", car.getCarLicensePlate())
				.param("year", car.getYear())
				.param("color", car.getColor())
				.param("dailyValue", car.getDailyValue())
				.update();
	}
	
	@Override
	public Integer update(Car car, Long id) {
		return this.jdbcClient.sql(
				"UPDATE cars SET model = :model, brand = :brand, car_license_plate = :carLicensePlate, car_year = :year, color = :color, daily_value = :dailyValue WHERE id = :id ")
				.param("model", car.getModel())
				.param("brand", car.getBrand())
				.param("carLicensePlate", car.getCarLicensePlate())
				.param("year", car.getYear())
				.param("color", car.getColor())
				.param("dailyValue", car.getDailyValue())
				.param("id", car.getId())
				.update();
	}

	@Override
	public Integer delete(Long id) {
		return this.jdbcClient.sql("DELETE cars WHERE ID = :id").param("id", id).update();

	}

}
