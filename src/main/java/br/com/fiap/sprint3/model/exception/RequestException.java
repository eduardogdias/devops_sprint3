package br.com.fiap.sprint3.model.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RequestException {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Map<String, String>> tratar404(EntityNotFoundException ex) {
	    Map<String, String> resposta = new HashMap<>();

	    String mensagem = ex.getMessage();
	    if (mensagem == null || mensagem.isBlank()) {
	        mensagem = "Nada encontrado";	// se nao receber nenhuma mensagem por parametro, a mensagem sera essa
	    }

	    resposta.put("mensagem", mensagem);
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta); //404
	}
	
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> tratarIllegalArgument(IllegalArgumentException ex) {
	    Map<String, String> resposta = new HashMap<>();
	    resposta.put("mensagem", ex.getMessage());
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta); // 400
	}

}

