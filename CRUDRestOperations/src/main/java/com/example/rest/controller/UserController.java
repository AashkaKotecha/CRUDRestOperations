package com.example.rest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.rest.repository.UserRepository;
import com.example.rest.exception.ResourceNotFoundException;
import com.example.rest.model.User;

@RestController
@RequestMapping("/UserRepo")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/hello")
	public String display() {
		return "hello";
	}
	
	  @GetMapping("/users") 
	  public List<User> getAllUsers() {
		  return userRepository.findAll();
	  }
	  
	  @GetMapping("/users/{id}") 
	  public ResponseEntity<User> getUserById(@PathVariable(value="id") Long userId) throws ResourceNotFoundException { 
		  User user = userRepository.findById(userId)
	  				                .orElseThrow(() -> new ResourceNotFoundException("User not found on: "+userId)); 
		  return ResponseEntity.ok().body(user);
	  }
	  
	  @PostMapping("/users") 
	  public User createUser(@RequestBody User user) {
		  
		  return userRepository.save(user); 
	  }
	  
	  @PutMapping("/users/{id}") 
	  public ResponseEntity<User> updateEntity(@PathVariable(value="id") Long userId,
			  		@RequestBody User userDetails) throws ResourceNotFoundException {
	  
	  User user = userRepository.findById(userId) 
			                    .orElseThrow(()-> new ResourceNotFoundException("User not found on: "+userId ));
	  
	  user.setEmailId(userDetails.getEmailId());
	  user.setLastName(userDetails.getLastName());
	  user.setFirstName(userDetails.getFirstName()); user.setUpdatedAt(new Date());
	  
	  final User updatedUser = userRepository.save(user);
	  
	  return ResponseEntity.ok(updatedUser);
	  
	  }
	  
	  @DeleteMapping("/users/{id}") 
	  public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception { 
		  User user = userRepository.findById(userId) 
				                    .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ userId));
	  
		  userRepository.delete(user);
		  Map<String, Boolean> response = new HashMap<String, Boolean>(); response.put("deleted", Boolean.TRUE); 
		  return response; 
	 }
	 
}