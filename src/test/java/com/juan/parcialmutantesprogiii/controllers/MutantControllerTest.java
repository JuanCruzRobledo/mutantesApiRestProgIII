package com.juan.parcialmutantesprogiii.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juan.parcialmutantesprogiii.domain.dto.DnaRequest;
import com.juan.parcialmutantesprogiii.domain.dto.DnaStats;
import com.juan.parcialmutantesprogiii.presentation.controllers.MutantController;
import com.juan.parcialmutantesprogiii.business.services.MutantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(MutantController.class)
public class MutantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MutantService mutantService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testIsNotMutantReturnsForbidden() throws Exception {
        DnaRequest dnaRequest = new DnaRequest(new String[]{
                "ATCG",
                "CAGT",
                "TTAT",
                "AGAA"}
        );
        when(mutantService.isMutant(dnaRequest.getDna())).thenReturn(false);

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dnaRequest)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testIsMutantReturnsOk() throws Exception {
        DnaRequest dnaRequest = new DnaRequest(new String[]{
                "ATCG",
                "CAGT",
                "TGAT",
                "GGAA"}
        );
        when(mutantService.isMutant(dnaRequest.getDna())).thenReturn(true);

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dnaRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void testBadRequestNxM() throws Exception {
        DnaRequest dnaRequest = new DnaRequest(new String[]{
                "ATCG",
                "CAT",
                "TGAT",
                "GGAA"}
        );
        when(mutantService.isMutant(dnaRequest.getDna())).thenThrow(new IllegalArgumentException("El ADN debe ser un cuadrado NxN."));

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dnaRequest)))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testBadRequestNull() throws Exception {
        DnaRequest dnaRequest = new DnaRequest(new String[]{
                null,
                null,
                null,
                null}
        );
        when(mutantService.isMutant(dnaRequest.getDna())).thenThrow(new IllegalArgumentException("El ADN no puede ser null o vacío."));

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dnaRequest)))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testBadRequestEmpty() throws Exception {
        DnaRequest dnaRequest = new DnaRequest(new String[]{}
        );
        when(mutantService.isMutant(dnaRequest.getDna())).thenThrow(new IllegalArgumentException("El ADN no puede ser null o vacío."));

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dnaRequest)))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testInvalidCharacters() throws Exception {
        DnaRequest dnaRequest = new DnaRequest(new String[]{
                "DSAA",
                "DSAD",
                "JPFS",
                "DSAD"
        }
        );
        when(mutantService.isMutant(dnaRequest.getDna())).thenThrow(new IllegalArgumentException("El ADN contiene caracteres inválidos."));

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dnaRequest)))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testGetStatsReturnsOk() throws Exception {
        DnaStats stats = new DnaStats(20,10, 4);
        when(mutantService.getStats()).thenReturn(stats);

        mockMvc.perform(get("/mutant/stats")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countMutantDna").value(stats.getCountMutantDna()))
                .andExpect(jsonPath("$.countHumanDna").value(stats.getCountHumanDna()));
    }
}