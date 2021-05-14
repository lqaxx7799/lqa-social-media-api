package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Account;
import com.example.demo.respository.AccountRepository;

@RestController
public class AccountController {
	@Autowired
	private AccountRepository accountRepository;
	
	@GetMapping("/accounts")
	public Iterable<Account> getAll() {
		return accountRepository.findAll();
	}
	
	@GetMapping("/accounts/followed/{id}")
	public Iterable<Account> getFollowed(@PathVariable("id") int id) {
		return accountRepository.getFollowed(id);
	}
	
	@GetMapping("/accounts/following/{id}")
	public Iterable<Account> getFollowing(@PathVariable("id") int id) {
		return accountRepository.getFollowing(id);
	}
	
	@GetMapping("/account/{id}")
	public Account getById(@PathVariable("id") int id) {
		return accountRepository.findById(id).orElse(null);
	}
	
	@PostMapping("/account")
	public Account addNew(@RequestBody Account account) {
		return accountRepository.save(account);
	}
	
	@PutMapping("/account")
	public Account update(@RequestBody Account account) {
		return accountRepository.save(account);
	}
	
	@DeleteMapping("account/{id}")
	public boolean delete(@PathVariable("id") int id) {
		accountRepository.deleteById(id);
		return true;
	}
}
