package com.proof.concept.beans;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app_etudiant")
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "etudiant_id", nullable = false)
    private Integer etudiantId;

    @Basic(optional = false)
    @Column(name = "etudiant_reg_number", length = 255, nullable = false)
    private String etudiantRegNumber;

    @Basic(optional = false)
    @Column(name = "etudiant_name", length = 255, nullable = false)
    private String etudiantName;

    @Basic(optional = false)
    @Column(name = "etudiant_birth_year", nullable = false)
    private Integer etudiantBirthYear;

    public Etudiant() {
    }

    public Etudiant(String etudiantRegNumber, String etudiantName, Integer etudiantBirthYear) {
        this.etudiantRegNumber = etudiantRegNumber;
        this.etudiantName = etudiantName;
        this.etudiantBirthYear = etudiantBirthYear;
    }

    public Etudiant(Integer etudiantId, String etudiantRegNumber, String etudiantName, Integer etudiantBirthYear) {
        this.etudiantId = etudiantId;
        this.etudiantRegNumber = etudiantRegNumber;
        this.etudiantName = etudiantName;
        this.etudiantBirthYear = etudiantBirthYear;
    }

    public Integer getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(Integer etudiantId) {
        this.etudiantId = etudiantId;
    }

    public String getEtudiantRegNumber() {
        return etudiantRegNumber;
    }

    public void setEtudiantRegNumber(String etudiantRegNumber) {
        this.etudiantRegNumber = etudiantRegNumber;
    }

    public String getEtudiantName() {
        return etudiantName;
    }

    public void setEtudiantName(String etudiantName) {
        this.etudiantName = etudiantName;
    }

    public Integer getEtudiantBirthYear() {
        return etudiantBirthYear;
    }

    public void setEtudiantBirthYear(Integer etudiantBirthYear) {
        this.etudiantBirthYear = etudiantBirthYear;
    }
}
