package com.juan.parcialmutantesprogiii.service;

import com.juan.parcialmutantesprogiii.detector.MutantDetector;
import com.juan.parcialmutantesprogiii.services.MutantService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class MutantServiceTest {

    private MutantDetector mutantDetector = new MutantDetector();


    // Manejo de errores

    @Test
    void testEmptyArray() {
        String[] dna = {};
        assertThrows(IllegalArgumentException.class, () -> mutantDetector.isMutant(dna));
    }

    @Test
    void testNxMArray() {
        String[] dna = {"ATGC", "CAGT", "TTAT", "AGA"};
        assertThrows(IllegalArgumentException.class, () -> mutantDetector.isMutant(dna));
    }

    @Test
    void testArrayWithNumbers() {
        String[] dna = {"1234", "5678", "9101", "1121"};
        assertThrows(IllegalArgumentException.class, () -> mutantDetector.isMutant(dna));
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> mutantDetector.isMutant(null));
    }

    @Test
    void testArrayOfNulls() {
        String[] dna = {null, null, null, null};
        assertThrows(IllegalArgumentException.class, () -> mutantDetector.isMutant(dna));
    }

    @Test
    void testInvalidCharacters() {
        String[] dna = {"BHAT", "CGBG", "TTAA", "GGTG"};
        assertThrows(IllegalArgumentException.class, () -> mutantDetector.isMutant(dna));
    }

    // Manejo de casos factibles (Mutante y No Mutante)

    //Mutantes
    @Test
    void testMutantCase1() {
        String[] dna = {
                "AAAA",
                "CCCC",
                "TCAG",
                "GGTC"
        };
        assertTrue(mutantDetector.isMutant(dna), "Se espera que el ADN sea mutante");
    }


    @Test
    void testMutantCase2() {
        String[] dna = {
                "TGAC",
                "AGCC",
                "TGAC",
                "GGTC"
        };
        assertTrue(mutantDetector.isMutant(dna), "Se espera que el ADN sea mutante");
    }


    @Test
    void testMutantCase5() {
        String[] dna = {
                "CTAGCTAG",
                "CTAGCTAG",
                "CTAGCTAG",
                "CTAGCTAG"
        };
        assertThrows(IllegalArgumentException.class, () -> mutantDetector.isMutant(dna), "La matriz debe ser N x N");
    }

    @Test
    void testMutantCase6() {
        String[] dna = {
                "GGGG",
                "ATCG",
                "ATCG",
                "GGGG"
        };
        assertTrue(mutantDetector.isMutant(dna), "Se espera que el ADN sea mutante");
    }

    @Test
    void testMutantCase7() {
        String[] dna = {
                "AAAA",
                "AAAA",
                "AAAA",
                "AAAA"
        };
        assertTrue(mutantDetector.isMutant(dna), "Se espera que el ADN sea mutante");
    }

    @Test
    void testMutantCase8() {
        String[] dna = {
                "ATAT",
                "TATA",
                "ATAT",
                "TATA"
        };
        assertTrue(mutantDetector.isMutant(dna), "Se espera que el ADN sea mutante");
    }

    //No Mutantes
    @Test
    void testNonMutantCase1() {
        String[] dna = {
                "TTAA",
                "TTAA",
                "CCGG",
                "CCGG"
        };
        assertFalse(mutantDetector.isMutant(dna), "Se espera que el ADN sea humano");
    }
    @Test
    void testNonMutantCase2() {
        String[] dna = {
                "AAGG",
                "AAGG",
                "TCTC",
                "TCTC"
        };
        assertFalse(mutantDetector.isMutant(dna), "Se espera que el ADN sea humano");
    }
    @Test
    void testNonMutantCase3() {
        String[] dna = {
                "AAAT",
                "AACC",
                "AAAC",
                "CGGG"
        };
        assertFalse(mutantDetector.isMutant(dna), "Se espera que el ADN sea humano");
    }

    @Test
    void testNonMutantCase4() {
        String[] dna = {
                "TGAC",
                "ATCC",
                "TAAG",
                "GGTC"
        };
        assertFalse(mutantDetector.isMutant(dna), "Se espera que el ADN sea humano");
    }

    @Test
    void testNonMutantCase5() {
        String[] dna = {
                "ATCG",
                "TAGC",
                "CGAT",
                "GCAT"
        };
        assertFalse(mutantDetector.isMutant(dna), "Se espera que el ADN sea humano");
    }

    @Test
    void testNonMutantCase6() {
        String[] dna = {
                "AAAC",
                "AACC",
                "CCGA",
                "TGGC"
        };
        assertFalse(mutantDetector.isMutant(dna), "Se espera que el ADN sea humano");
    }

    @Test
    void testNonMutantCase7() {
        String[] dna = {
                "CATG",
                "TACG",
                "AGTC",
                "GATC"
        };
        assertFalse(mutantDetector.isMutant(dna), "Se espera que el ADN sea humano");
    }
    // Casos particulares
    @Test
    void testLargeMutantCase() {
        String[] dna = {
                "TCGGGTGAT",
                "TGATCCTTT",
                "TACGAGTGA",
                "AAATGTACG",
                "ACGAGTCGT",
                "AGACACATG",
                "GAATTCCAA",
                "ACTACGACC",
                "TGAGTATCC"
        };
        assertTrue(mutantDetector.isMutant(dna), "Se espera que el ADN sea mutante");
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
        assertTrue(mutantDetector.isMutant(dna), "Se espera que el ADN sea mutante");
    }
}