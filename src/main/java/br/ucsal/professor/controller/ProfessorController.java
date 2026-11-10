package br.ucsal.professor.controller;

import java.util.List;

import br.ucsal.professor.model.Professor;
import br.ucsal.professor.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    // Create
    @PostMapping
    public ResponseEntity<Professor> criar(@Valid @RequestBody Professor professor) {
        Professor novo = service.criar(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    // Read - todos
    @GetMapping
    public List<Professor> listarTodos() {
        return service.listarTodos();
    }

    // Read - por id
    @GetMapping("/{id}")
    public Professor buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // Update
    @PutMapping("/{id}")
    public Professor atualizar(@PathVariable Long id, @Valid @RequestBody Professor professor) {
        return service.atualizar(id, professor);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
