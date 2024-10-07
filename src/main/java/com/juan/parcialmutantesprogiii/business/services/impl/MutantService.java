package com.juan.parcialmutantesprogiii.business.services.impl;

import com.juan.parcialmutantesprogiii.business.detector.MutantDetector;
import com.juan.parcialmutantesprogiii.business.services.IMutantService;
import org.springframework.stereotype.Service;

@Service
public class MutantService implements IMutantService {

    private final MutantDetector mutantDetector;

    public MutantService(MutantDetector mutantDetector) {
        this.mutantDetector = mutantDetector;
    }

    @Override
    public boolean isMutant(String[] dna) {
        return mutantDetector.isMutant(dna);
    }
}
