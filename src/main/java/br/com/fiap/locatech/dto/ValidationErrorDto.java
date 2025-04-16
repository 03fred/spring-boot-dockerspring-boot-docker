package br.com.fiap.locatech.dto;

import java.util.List;

public record ValidationErrorDto (List<String> errors, int httpStatusCode){

}
