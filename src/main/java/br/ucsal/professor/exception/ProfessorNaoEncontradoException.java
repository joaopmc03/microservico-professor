package br.ucsal.professor.exception;

public class ProfessorNaoEncontradoException extends RuntimeException {

    public ProfessorNaoEncontradoException(Long id) {
        super("Professor com id " + id + " nao encontrado");
    }
}
