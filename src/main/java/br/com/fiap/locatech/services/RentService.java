package br.com.fiap.locatech.services;

import java.math.BigDecimal;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.fiap.locatech.dto.RentRequestDTO;
import br.com.fiap.locatech.entities.Car;
import br.com.fiap.locatech.entities.Rent;
import br.com.fiap.locatech.repositories.CarRepository;
import br.com.fiap.locatech.repositories.RentRepository;
import br.com.fiap.locatech.services.exceptions.ResourceNotFoundException;

@Service
public class RentService {

	private final RentRepository rentRepository;
	private final CarRepository carRepository;

	public RentService(RentRepository rentRepository, CarRepository carRepository) {
		this.rentRepository = rentRepository;
		this.carRepository = carRepository;
	}

	public List<Rent> findAll(int page, int size) {
		int offset = (page - 1) * size;
		return this.rentRepository.findAll(size, offset);
	}

	public Optional<Rent> findRentById(Long id) {
		return Optional.ofNullable(this.rentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Aluguel não encontrado")));
	}

	public void saveRent(RentRequestDTO rentDto) {
		Rent rent = this.calculateTotalValueAndBuildRentObject(rentDto);
		var save = this.rentRepository.save(rent);
		Assert.state(save == 1, "Erro ao salvar o aluguel veiculo " + rent.getCarModel());
	}

	public void updateRent(RentRequestDTO rentDto, Long id) {
		Rent rent = this.calculateTotalValueAndBuildRentObject(rentDto);

		var save = this.rentRepository.update(rent, id);
		if (save == 0) {
			throw new RuntimeException("Erro ao atualizar aluguel ");
		}
	}

	public void delete(Long id) {
		var delete = this.rentRepository.delete(id);
		if (delete == 0) {
			throw new RuntimeException("Erro ao excluir Aluguel ");
		}
	}

	private Rent calculateTotalValueAndBuildRentObject(RentRequestDTO rentDto) {
		Car car = this.carRepository.findById(rentDto.carId())
				.orElseThrow(() -> new RuntimeException("Carro não encontrado"));

		Period period = Period.between(rentDto.initialDate(), rentDto.finalDate());
		int diffDays = period.getDays();

		BigDecimal daysBigDecimal = new BigDecimal(diffDays);
		BigDecimal totalValue = daysBigDecimal.multiply(car.getDailyValue());
		
		return new Rent(rentDto, totalValue);
		
		
	}
}
