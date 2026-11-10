package br.ucsal.professor.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(ProfessorNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> naoEncontrado(ProfessorNaoEncontradoException e) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> requisicaoInvalida(IllegalArgumentException e) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", e.getMessage());
        return ResponseEntity.badRequest().body(erro);
    }

    // quando falta campo obrigatorio ou o email ta errado
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> camposInvalidos(MethodArgumentNotValidException e) {
        Map<String, String> erros = new HashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(campo -> erros.put(campo.getField(), campo.getDefaultMessage()));
        return ResponseEntity.badRequest().body(erros);
    }
}
