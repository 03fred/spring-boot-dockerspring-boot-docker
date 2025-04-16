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

import br.com.fiap.locatech.entities.Person;
import br.com.fiap.locatech.services.PersonService;

@RestController
@RequestMapping("/people")
public class PersonController {

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping
	public ResponseEntity<List<Person>> findAllPerson(@RequestParam("page") int page, @RequestParam("size") int size) {
		logger.info("Foi acessado o endpoint buscar todos os veiculos");
		var cars = this.personService.findAll(page, size);
		return ResponseEntity.ok(cars);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Person>> findPerson(@PathVariable("id") Long id) {
		logger.info("Foi acessado o endpoint buscar um veiculo");
		var person = this.personService.findPersonById(id);
		return ResponseEntity.ok(person);
	}

	@PostMapping
	public ResponseEntity<Void> savePerson(@RequestBody Person person) {
		logger.info("Foi acessado o endpoint salvar um veiculo");
		this.personService.savePerson(person);
		return ResponseEntity.status(201).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updatePerson(@PathVariable("id") Long id, @RequestBody Person person) {
		logger.info("Foi acessado o endpoint atualizar um veiculo");
		this.personService.updatePerson(person, id);
		return ResponseEntity.status(204).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePerson(@PathVariable("id") Long id) {
		logger.info("Foi acessado o endpoint deletar uma pessoa");
		this.personService.delete(id);
		return ResponseEntity.status(204).build();
	}

}
