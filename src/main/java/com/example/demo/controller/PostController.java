package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Account;
import com.example.demo.model.Post;
import com.example.demo.respository.AccountRepository;
import com.example.demo.respository.PostRepository;

@RestController
@CrossOrigin(origins = "*")
public class PostController {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private AccountRepository accountRepository;

	@GetMapping("/feed")
	public Iterable<Post> getFeed(@RequestParam("id") int accountId, @RequestParam("page") int page) {
		System.out.println(accountId + " " + page);
		Account account = accountRepository.findById(accountId).orElse(null);
		if (account == null) {
			return new ArrayList<>();
		}
		Pageable pageable = PageRequest.of(page, 10, Sort.by("createdTime").descending());
		return postRepository.getFeed(account.getId(), pageable).getContent();
	}
	
	
	@GetMapping("/posts/byAccount/{id}")
	public Iterable<Post> getByAccountId(@PathVariable("id") int accountId) {
		Account account = accountRepository.findById(accountId).orElse(null);
		if (account == null) {
			return new ArrayList<>();
		}
		return postRepository.findByAccount(account);
	}
	
	@GetMapping("/post/{id}")
	public Post addNew(@PathVariable("id") int postId) {
		return postRepository.findById(postId).orElse(null);
	}
	
	@PostMapping("/post")
	public Post addNew(@RequestBody Post post) {
		return postRepository.save(post);
	}
	
	@PutMapping("/post")
	public Post update(@RequestBody Post post) {
		return postRepository.save(post);
	}
	
	@DeleteMapping("/post/{id}")
	public boolean delete(@PathVariable("id") int postId) {
		postRepository.deleteById(postId);
		return true;
	}
}
