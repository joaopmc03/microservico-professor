package br.ucsal.professor.service;

import java.util.List;

import br.ucsal.professor.exception.ProfessorNaoEncontradoException;
import br.ucsal.professor.model.Professor;
import br.ucsal.professor.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    private final ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public Professor criar(Professor professor) {
        if (repository.existsByEmail(professor.getEmail())) {
            throw new IllegalArgumentException("Ja existe professor com esse email");
        }
        professor.setId(null); // garante que vai criar um novo
        return repository.save(professor);
    }

    public List<Professor> listarTodos() {
        return repository.findAll();
    }

    public Professor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProfessorNaoEncontradoException(id));
    }

    public Professor atualizar(Long id, Professor dados) {
        Professor professor = buscarPorId(id);

        if (!professor.getEmail().equals(dados.getEmail()) && repository.existsByEmail(dados.getEmail())) {
            throw new IllegalArgumentException("Ja existe professor com esse email");
        }

        professor.setNome(dados.getNome());
        professor.setEmail(dados.getEmail());
        professor.setDisciplina(dados.getDisciplina());
        professor.setTitulacao(dados.getTitulacao());
        return repository.save(professor);
    }

    public void remover(Long id) {
        Professor professor = buscarPorId(id);
        repository.delete(professor);
    }
}
