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

import br.com.fiap.locatech.dto.RentRequestDTO;
import br.com.fiap.locatech.entities.Rent;
import br.com.fiap.locatech.services.RentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/rent")
public class RentController {

	private static final Logger logger = LoggerFactory.getLogger(RentController.class);

	private final RentService rentService;

	public RentController(RentService rentService) {
		this.rentService = rentService;
	}

	@GetMapping
	public ResponseEntity<List<Rent>> findAllRent(@RequestParam("page") int page, @RequestParam("size") int size) {
		logger.info("Foi acessado o endpoint buscar todos os veiculos");
		var cars = this.rentService.findAll(page, size);
		return ResponseEntity.ok(cars);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Rent>> findRend(@PathVariable("id") Long id) {
		logger.info("Foi acessado o endpoint buscar um veiculo");
		var person = this.rentService.findRentById(id);
		return ResponseEntity.ok(person);
	}

	@PostMapping
	public ResponseEntity<Void> saveRent(@Valid @RequestBody RentRequestDTO person) {
		logger.info("Foi acessado o endpoint salvar um veiculo");
		this.rentService.saveRent(person);
		return ResponseEntity.status(201).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateRent(@PathVariable("id") Long id, @Valid @RequestBody RentRequestDTO rentDto) {
		logger.info("Foi acessado o endpoint atualizar um veiculo");
		this.rentService.updateRent(rentDto, id);
		return ResponseEntity.status(204).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRent(@PathVariable("id") Long id) {
		logger.info("Foi acessado o endpoint deletar uma pessoa");
		this.rentService.delete(id);
		return ResponseEntity.status(204).build();
	}

}
