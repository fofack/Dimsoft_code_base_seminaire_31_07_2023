package com.proof.concept.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proof.concept.beans.User;
import com.proof.concept.repository.UserRepository;
import com.proof.concept.services.UsersService;

@Service
@Transactional
public class UserServiceImpl implements UsersService {

	@Autowired
	UserRepository usersRepository;

	@Override
	public User createOrUpdateUser(User usr) {
		if (usr.getUserId() != null) {
			usr.setLastUpdateOn(new Date());
			return usersRepository.save(usr);
		}
		
		// Toujours crypter le password
		usr.setPassword(usr.getPassword());
		usr.setCreatedOn(new Date());
		return usersRepository.save(usr);
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		usersRepository.getAllUsers().forEach(users::add);
		return users;
	}

	@Override
	public User deleteUsers(int userId) {
		Optional<User> userOrNot = usersRepository.findById(userId);
		if (userOrNot.isPresent()) {
			User userToDelete = userOrNot.get();
			usersRepository.delete(userToDelete);
			return userToDelete;
		}
		return null;
	}

	@Override
	public void deleteAll() {
		usersRepository.deleteAll();
	}

	@Override
	public User findByUserName(String userName) {
		User user = usersRepository.findByUsername(userName);
		if (user != null)
			return user;
		return null;
	}

	@Override
	public User findByUserEmail(String userEmail) {
		User user = usersRepository.findByUserEmail(userEmail);
		if (user != null)
			return user;
		return null;
	}

	@Override
	public User findUserById(int userId) {
		Optional<User> userOrNot = usersRepository.findById(userId);
		return userOrNot.isPresent() ? userOrNot.get() : null;
	}

	@Override
	public User updateUserPassword(int userId, String olpPassword, String newPassword) {
		Optional<User> userOrNot = usersRepository.findById(userId);
		if (userOrNot.isPresent()) {
			User userToUpdate = userOrNot.get();
			if (olpPassword.equals(userToUpdate.getPassword())) {
				userToUpdate.setPassword(newPassword);
				userToUpdate.setLastUpdateOn(new Date());
				return userToUpdate;
			}
			return null;
		} else {
			return null;
		}
	}

}
