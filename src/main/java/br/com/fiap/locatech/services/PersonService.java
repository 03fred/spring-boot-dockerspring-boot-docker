package br.com.fiap.locatech.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.fiap.locatech.entities.Person;
import br.com.fiap.locatech.repositories.PersonRepository;

@Service
public class PersonService {

	private final PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public List<Person> findAll(int page, int size) {
		int offset = (page - 1) * size;
		return this.personRepository.findAll(size, offset);
	}

	public Optional<Person> findPersonById(Long id) {
		return this.personRepository.findById(id);
	}

	public void savePerson(Person Person) {
		var save = this.personRepository.save(Person);
		Assert.state(save == 1, "Erro ao salvar veiculo " + Person.getPersonName());
	}

	public void updatePerson(Person Person, Long id) {
		var save = this.personRepository.update(Person, id);
		if (save == 0) {
			throw new RuntimeException("Erro ao atualizar Pessoa ");
		}
	}
	
	public void delete(Long id) {
		var delete = this.personRepository.delete(id);
		if (delete == 0) {
			throw new RuntimeException("Erro ao excluir Pessoa ");
		}
	}
}
