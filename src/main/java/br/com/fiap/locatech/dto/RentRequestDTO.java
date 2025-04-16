package br.com.fiap.locatech.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public record RentRequestDTO(
		@NotNull(message = "O id da pessoa não pode ser null") Long pessoaId,
		@NotNull(message = "O id do carro não pode ser null") Long carId,
		@JsonFormat(pattern = "yyyy-MM-dd")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
		LocalDate initialDate,
		LocalDate finalDate) {

}
