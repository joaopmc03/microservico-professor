package br.ucsal.professor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ProfessorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void crudCompleto() throws Exception {
        String json = """
                {"nome":"Maria Souza","email":"maria@ucsal.br","disciplina":"Banco de Dados","titulacao":"Mestre"}
                """;

        // cria
        mvc.perform(post("/professores").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));

        // lista e busca
        mvc.perform(get("/professores")).andExpect(jsonPath("$.length()").value(1));
        mvc.perform(get("/professores/1")).andExpect(jsonPath("$.nome").value("Maria Souza"));

        // atualiza
        String atualizado = """
                {"nome":"Maria Souza","email":"maria@ucsal.br","disciplina":"Engenharia de Software","titulacao":"Doutora"}
                """;
        mvc.perform(put("/professores/1").contentType(MediaType.APPLICATION_JSON).content(atualizado))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.disciplina").value("Engenharia de Software"));

        // remove
        mvc.perform(delete("/professores/1")).andExpect(status().isNoContent());
        mvc.perform(get("/professores/1")).andExpect(status().isNotFound());
    }

    @Test
    void naoDeveCriarSemNome() throws Exception {
        String json = """
                {"email":"joao@ucsal.br","disciplina":"Redes"}
                """;
        mvc.perform(post("/professores").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nome").exists());
    }
}
