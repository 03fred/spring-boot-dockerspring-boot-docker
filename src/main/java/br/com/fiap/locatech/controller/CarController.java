package br.com.fiap.locatech.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarController {


	@GetMapping
	public String getCar() {
		return "yellow car";
	}
}
