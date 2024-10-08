package com.juan.parcialmutantesprogiii.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(MutantController.class)
public class MutantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testIsMutant_withValidDna_returnsOk() throws Exception {
        String validDna = "{ \"dna\": [\"ATCG\", \"CAGT\", \"TTAG\", \"CTGA\"] }";
        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validDna))
                .andExpect(status().isOk());
    }

    @Test
    public void testIsMutant_withEmptyArray_returnsBadRequest() throws Exception {
        String emptyDna = "{ \"dna\": [] }";
        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(emptyDna))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("El arreglo de ADN no puede estar vacío o ser null."));
    }

    @Test
    public void testIsMutant_withNonSquareMatrix_returnsBadRequest() throws Exception {
        String nonSquareDna = "{ \"dna\": [\"ATCG\", \"CAGT\", \"TTAGG\"] }";
        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nonSquareDna))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("La matriz de ADN debe ser NxN."));
    }

    @Test
    public void testIsMutant_withInvalidCharacters_returnsBadRequest() throws Exception {
        String invalidCharsDna = "{ \"dna\": [\"BTAG\", \"CAGH\", \"TTAH\", \"ACTG\"] }";
        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidCharsDna))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("La matriz de ADN contiene caracteres no válidos. Solo se permiten A, C, T, G."));
    }
}