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

import com.proof.concept.beans.Fruit;
import com.proof.concept.beans.User;
import com.proof.concept.exceptions.InvalidInputException;
import com.proof.concept.services.FruitsService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/fruits")
public class FruitsController {
    @Autowired
    private FruitsService fruitsService;

    @PostMapping(value = "/createFruit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Fruit> createUser(@RequestBody Fruit fruit) throws InvalidInputException {
		if (fruitsService.findByFruitName(fruit.getFruitName()) != null) {
			throw new InvalidInputException("Fruit already used");
		}
		Fruit createdFruit = fruitsService.createOrUpdateFruit(fruit);
		return new ResponseEntity<>(createdFruit, HttpStatus.CREATED);
	}
}
