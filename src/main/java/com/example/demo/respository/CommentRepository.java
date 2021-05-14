package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
}
