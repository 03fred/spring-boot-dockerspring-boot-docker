package br.com.fiap.locatech.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.fiap.locatech.entities.Car;
import br.com.fiap.locatech.repositories.CarRepository;

@Service
public class CarService {

	private final CarRepository carRepository;

	public CarService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	public List<Car> findAll(int page, int size) {
		int offset = (page - 1) * size;
		return this.carRepository.findAll(size, offset);
	}

	public Optional<Car> findCarById(Long id) {
		return this.carRepository.findById(id);
	}

	public void saveCar(Car car) {
		var save = this.carRepository.save(car);
		Assert.state(save == 1, "Erro ao salvar veiculo " + car.getModel());
	}

	public void updateCar(Car car, Long id) {
		var save = this.carRepository.update(car, id);
		if (save == 0) {
			throw new RuntimeException("Erro ao atualizar veiculo ");
		}
	}
	
	public void delete(Long id) {
		var delete = this.carRepository.delete(id);
		if (delete == 0) {
			throw new RuntimeException("Erro ao excluir veiculo ");
		}
	}
}
