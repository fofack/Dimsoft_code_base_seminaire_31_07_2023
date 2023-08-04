package com.proof.concept.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proof.concept.beans.Etudiant;
import com.proof.concept.exceptions.CannotCreateEntityException;
import com.proof.concept.model.EtudiantDto;
import com.proof.concept.services.EtudiantsService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/etudiants")
public class EtudiantsController {
    @Autowired
    public EtudiantsService etudiantsService;

    @PostMapping(value = "/createEtudiant", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EtudiantDto> createEtudiant(@RequestBody EtudiantDto etudiantDto) throws CannotCreateEntityException{
		if (etudiantsService.existsByRegNumber(etudiantDto.getEtudiant().getEtudiantRegNumber())) {
			throw new CannotCreateEntityException("Etudiant already exists");
		}
		Etudiant createdEtudiant = etudiantsService.createOrUpdateEtudiant(etudiantDto);
		return new ResponseEntity<>(EtudiantDto.fromEtudiant(createdEtudiant), HttpStatus.CREATED);
	}
}
