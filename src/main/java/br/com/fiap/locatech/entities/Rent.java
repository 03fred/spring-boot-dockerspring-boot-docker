package br.com.fiap.locatech.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.fiap.locatech.dto.RentRequestDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Rent {

	private Long id;
	private Long personId;
	private Long carId;
	private String carModel;
    private String personCpf;
    private String personName;
    private LocalDate initialDate;
    private LocalDate finalDate;
    private BigDecimal totalValue;
	
    public Rent(RentRequestDTO rentDto, BigDecimal totalValue) {
		super();
		this.personId = rentDto.pessoaId();
		this.carId = rentDto.carId();
		this.initialDate = rentDto.initialDate();
		this.finalDate = rentDto.finalDate();
		this.totalValue = totalValue;
	}
    
    
    
}
