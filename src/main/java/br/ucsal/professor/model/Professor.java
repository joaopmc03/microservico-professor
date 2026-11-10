package br.ucsal.professor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nome e obrigatorio")
    private String nome;

    @NotBlank(message = "email e obrigatorio")
    @Email(message = "email invalido")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "disciplina e obrigatoria")
    private String disciplina;

    private String titulacao; // graduado, especialista, mestre, doutor

    public Professor() {
    }

    public Professor(String nome, String email, String disciplina, String titulacao) {
        this.nome = nome;
        this.email = email;
        this.disciplina = disciplina;
        this.titulacao = titulacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }
}
