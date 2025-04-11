package br.com.fiap.locatech.entities;

import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Car {

	private Long id;
	private String model;
	private String brand;
	private String carLicensePlate;
	private int year;
	private String color;
	private BigDecimal dailyValue;

}
