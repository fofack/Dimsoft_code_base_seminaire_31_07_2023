package com.proof.concept.services;

import com.proof.concept.beans.Etudiant;
import com.proof.concept.model.EtudiantDto;

public interface EtudiantsService {

    public Etudiant createOrUpdateEtudiant(EtudiantDto etudiantDto);

    public boolean existsByRegNumber(String etudiantRegNumber);
    
}
