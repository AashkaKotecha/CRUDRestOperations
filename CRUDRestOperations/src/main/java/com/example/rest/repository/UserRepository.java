package com.example.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rest.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
}
