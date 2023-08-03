package com.proof.concept.model;

import com.proof.concept.beans.Etudiant;

public class EtudiantDto {
    private Etudiant etudiant;

    public EtudiantDto() {
    }

    public EtudiantDto(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
    
    public static EtudiantDto fromEtudiant(Etudiant etud) {
        return new EtudiantDto(etud);
    }
}
