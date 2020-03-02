package com.rmp.rmp.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rmp.rmp.entity.User;
import com.rmp.rmp.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;

	@GetMapping("/users") 	
	public List<User> findAll(){
		return userRepo.findAll();
	}
	
	@GetMapping("/users/{userId}") 	
	public User getUser(@PathVariable int userId){
		Optional<User> tempUser = userRepo.findById(userId);
		User theUser = null;
		if (tempUser.isPresent()) {
			theUser = tempUser.get();
		}else {
			throw new RuntimeException("User id not found " + userId);
		}
		return theUser;
	}
	
	@PostMapping("/users")
	public User addUser(@RequestBody User theUser) {
		theUser.setId(0);
		
		userRepo.save(theUser);
		
		return theUser;
	}
	
	@PutMapping("/users")
	public User updateUser(@RequestBody User theUser) {
				
		userRepo.save(theUser);
		
		return theUser;
	}
	
	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable int userId) {
		
		try {
			userRepo.deleteById(userId);
			return "User deleted by id - " + userId;
		} catch (Exception e) {
			throw new RuntimeException("User id not found - " + userId);
		}
		
	}
}
