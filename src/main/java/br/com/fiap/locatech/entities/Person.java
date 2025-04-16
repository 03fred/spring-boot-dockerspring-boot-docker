package br.com.fiap.locatech.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Person {

	private Long id;
	private String PersonName;
	private String phone;
	private String cpf;
	private String email;
}
