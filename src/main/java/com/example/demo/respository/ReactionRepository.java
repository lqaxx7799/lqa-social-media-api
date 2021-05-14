package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Reaction;

public interface ReactionRepository extends JpaRepository<Reaction, Integer>{
	
}
