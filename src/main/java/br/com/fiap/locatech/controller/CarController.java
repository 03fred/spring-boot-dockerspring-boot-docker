package br.com.fiap.locatech.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.locatech.entities.Car;
import br.com.fiap.locatech.services.CarService;

@RestController
@RequestMapping("/car")
public class CarController {

	private static final Logger logger = LoggerFactory.getLogger(CarController.class);

	private final CarService carService;

	public CarController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping
	public ResponseEntity<List<Car>> findAllCar(@RequestParam("page") int page, @RequestParam("size") int size) {
		logger.info("Foi acessado o endpoint buscar todos os veiculos");
		var cars = this.carService.findAll(page, size);
		return ResponseEntity.ok(cars);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Car>> findCar(@PathVariable("id") Long id) {
		logger.info("Foi acessado o endpoint buscar um veiculo");
		var car = this.carService.findCarById(id);
		return ResponseEntity.ok(car);
	}

	@PostMapping
	public ResponseEntity<Void> saveCar(@RequestBody Car car) {
		logger.info("Foi acessado o endpoint salvar um veiculo");
		this.carService.saveCar(car);
		return ResponseEntity.status(201).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateCar(@PathVariable("id") Long id, @RequestBody Car car) {
		logger.info("Foi acessado o endpoint atualizar um veiculo");
		this.carService.updateCar(car, id);
		return ResponseEntity.status(204).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCar(@PathVariable("id") Long id) {
		logger.info("Foi acessado o endpoint deletar um veiculo");
		this.carService.delete(id);
		return ResponseEntity.status(204).build();
	}

}
