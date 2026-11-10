package br.ucsal.professor.repository;

import br.ucsal.professor.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    boolean existsByEmail(String email);
}
