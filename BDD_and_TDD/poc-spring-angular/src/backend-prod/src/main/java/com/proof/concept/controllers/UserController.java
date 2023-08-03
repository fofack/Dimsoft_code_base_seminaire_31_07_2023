package com.proof.concept.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proof.concept.services.UsersService;
import com.proof.concept.beans.User;
import com.proof.concept.exceptions.InvalidInputException;
import com.proof.concept.model.UserPasswordChangeModel;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UsersService userService;

	@GetMapping(value = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@PostMapping(value = "/createUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user) throws InvalidInputException {
		if (userService.findByUserName(user.getUsername()) != null) {
			throw new InvalidInputException("Username already used");
		}
		User userCreated = userService.createOrUpdateUser(user);
		return new ResponseEntity<>(userCreated, HttpStatus.OK);
	}

	@PostMapping(value = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user) throws InvalidInputException {
		User userByUserName = userService.findByUserName(user.getUsername());
		if (userByUserName != null && !userByUserName.getUserId().equals(user.getUserId())) {
			throw new InvalidInputException("The new username you have choosen already used");
		} 
		User userUpdated = userService.createOrUpdateUser(user);
		return new ResponseEntity<>(userUpdated, HttpStatus.OK);
	}

	@GetMapping(value = "/deleteUser/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> deleteUser(@PathVariable("userId") int userId) throws InvalidInputException {
		User userDeleted = userService.deleteUsers(userId);
		if (userDeleted != null) {
			return new ResponseEntity<>(userDeleted, HttpStatus.OK);
		} else {
			throw new InvalidInputException("User to delete not found");
		}

	}

	@GetMapping(value = "/findUserById/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> findUserById(@PathVariable("userId") int userId) {
		User userFound = userService.findUserById(userId);
		return new ResponseEntity<>(userFound, HttpStatus.OK);
	}

	@PostMapping(value = "/updateUserPassword", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUserPassword(@RequestBody UserPasswordChangeModel user)
			throws InvalidInputException {
		User userChanged = userService.updateUserPassword(user.getUserId(), user.getOldPassword(),
				user.getNewPassword());
		if (userChanged != null) {
			return new ResponseEntity<>(userChanged, HttpStatus.OK);
		} else {
			throw new InvalidInputException("Votre ancien mot de passe est incorrect.");
		}
	}

}
