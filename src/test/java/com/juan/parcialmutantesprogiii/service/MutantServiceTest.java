package com.juan.parcialmutantesprogiii.service;

import com.juan.parcialmutantesprogiii.services.MutantService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class MutantServiceTest {

    @MockBean // Cambiar a MockBean para pruebas unitarias aisladas
    private MutantService mutantService;


    // Manejo de errores

    @Test
    void testEmptyArray() {
        String[] dna = {};
        assertThrows(IllegalArgumentException.class, () -> mutantService.isMutant(dna));
    }

    @Test
    void testNxMArray() {
        String[] dna = {"ATGC", "CAGT", "TTAT", "AGA"};
        assertThrows(IllegalArgumentException.class, () -> mutantService.isMutant(dna));
    }

    @Test
    void testArrayWithNumbers() {
        String[] dna = {"1234", "5678", "9101", "1121"};
        assertThrows(IllegalArgumentException.class, () -> mutantService.isMutant(dna));
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> mutantService.isMutant(null));
    }

    @Test
    void testArrayOfNulls() {
        String[] dna = {null, null, null, null};
        assertThrows(IllegalArgumentException.class, () -> mutantService.isMutant(dna));
    }

    @Test
    void testInvalidCharacters() {
        String[] dna = {"BHAT", "CGBG", "TTAA", "GGTG"};
        assertThrows(IllegalArgumentException.class, () -> mutantService.isMutant(dna));
    }

    // Manejo de casos factibles (Mutante y No Mutante)

    @Test
    void testMutantCase1() {
        String[] dna = {
                "AAAA",
                "CCCC",
                "TCAG",
                "GGTC"
        };
        assertTrue(mutantService.isMutant(dna), "Expected DNA to be identified as mutant");
    }

    @Test
    void testNonMutantCase1() {
        String[] dna = {
                "AAAT",
                "AACC",
                "AAAC",
                "CGGG"
        };
        assertFalse(mutantService.isMutant(dna), "Expected DNA to be identified as human");
    }

    @Test
    void testMutantCase2() {
        String[] dna = {
                "TGAC",
                "AGCC",
                "TGAC",
                "GGTC"
        };
        assertTrue(mutantService.isMutant(dna), "Expected DNA to be identified as mutant");
    }

    @Test
    void testNonMutantCase2() {
        String[] dna = {
                "AAAA",
                "AAAA",
                "AAAA",
                "AAAA"
        };
        assertFalse(mutantService.isMutant(dna), "Expected DNA to be identified as human");
    }

    @Test
    void testNonMutantCase3() {
        String[] dna = {
                "TGAC",
                "ATCC",
                "TAAG",
                "GGTC"
        };
        assertFalse(mutantService.isMutant(dna), "Expected DNA to be identified as human");
    }

    @Test
    void testLargeMutantCase() {
        String[] dna = {
                "TCGCGTGAT",
                "TGATCCTTT",
                "TACGAGTGA",
                "AAATGTCGG",
                "ACGAGTGCT",
                "AGACTAAGT",
                "GAATTCGAA",
                "ACTACGACC",
                "TGAGTATCC"
        };
        assertTrue(mutantService.isMutant(dna), "Expected DNA to be identified as mutant");
    }

    @Test
    void testNineByNineMutantCase() {
        String[] dna = {
                "TTTTTTTTT",
                "TTTTTTTTT",
                "TTTTTTTTT",
                "TTTTTTTTT",
                "CCGACCGAT",
                "GGCACTCCA",
                "AGGACACTA",
                "CAAAGGCTA",
                "GCCCGGTTG"
        };
        assertTrue(mutantService.isMutant(dna), "Expected DNA to be identified as mutant");
    }
}