# Microserviço de Professores

Aluno: João Pedro Menezes Costa - RA 20002903

Microserviço em Java com Spring Boot que faz o CRUD do cadastro de professor.
Usei o banco H2 em memória pra não precisar instalar nada, então os dados somem quando o serviço reinicia.

## Como rodar

Precisa do Java 21 e do Maven (ou abrir direto na IDE e rodar a classe `ProfessorServiceApplication`).

```
mvn spring-boot:run
```

O serviço sobe na porta **8081**.

## Endpoints

| Método | Rota | O que faz |
|---|---|---|
| POST | /professores | cria um professor |
| GET | /professores | lista todos |
| GET | /professores/{id} | busca por id |
| PUT | /professores/{id} | atualiza os dados |
| DELETE | /professores/{id} | remove o professor |

Exemplo de JSON pra criar/atualizar:

```json
{
  "nome": "Carlos Lima",
  "email": "carlos@ucsal.br",
  "disciplina": "Sistemas Distribuidos",
  "titulacao": "Doutor"
}
```

Nome, email e disciplina são obrigatórios e o email não pode repetir. Se buscar um id que não existe volta 404.

## Testes

```
mvn test
```

Tem um teste que passa pelo CRUD inteiro e outro que tenta cadastrar sem nome.

O console do banco fica em http://localhost:8081/h2-console (JDBC URL: `jdbc:h2:mem:professordb`, usuário `sa`, sem senha).
