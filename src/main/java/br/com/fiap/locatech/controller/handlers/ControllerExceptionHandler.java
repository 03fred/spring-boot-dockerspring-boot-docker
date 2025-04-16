package br.com.fiap.locatech.controller.handlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.fiap.locatech.dto.HttpMessageNotReadableExceptionDTO;
import br.com.fiap.locatech.dto.ResourceNotFoundDTO;
import br.com.fiap.locatech.dto.ValidationErrorDto;
import br.com.fiap.locatech.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResourceNotFoundDTO> handlerResourceNotFoundException(ResourceNotFoundException e){
		var statusCode = HttpStatus.NOT_FOUND;
		return ResponseEntity.status(statusCode.value()).body(new ResourceNotFoundDTO(e.getMessage(), statusCode.value()));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorDto> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){
		var statusCode = HttpStatus.BAD_REQUEST;
		List<String> errors = new ArrayList<>();
		
		for(var erro : e.getBindingResult().getFieldErrors()) {
			errors.add(erro.getField() + " : " + erro.getDefaultMessage());
		}
		return ResponseEntity.status(statusCode.value()).body(new ValidationErrorDto(errors, statusCode.value()));
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<HttpMessageNotReadableExceptionDTO> handlerHttpMessageNotReadableException(HttpMessageNotReadableException e){
		 String mensagem = "Erro ao ler a requisição JSON. Verifique o formato dos dados.";
	        if (e.getCause() instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException) {
	            com.fasterxml.jackson.databind.exc.InvalidFormatException ife = 
	            		(com.fasterxml.jackson.databind.exc.InvalidFormatException) e.getCause();
	            if (ife.getTargetType().isAssignableFrom(java.util.Date.class) ||
	                ife.getTargetType().isAssignableFrom(java.time.LocalDate.class) ||
	                ife.getTargetType().isAssignableFrom(java.time.LocalDateTime.class)) {
	                mensagem = String.format("Erro de formato na data '%s'. O formato esperado é: ANO-MES-DIA", ife.getValue());
	            }
	        }
	        
		 var statusCode = HttpStatus.BAD_REQUEST;

		return ResponseEntity.status(statusCode.value()).body(new HttpMessageNotReadableExceptionDTO(mensagem, statusCode.value()));
	}

}
