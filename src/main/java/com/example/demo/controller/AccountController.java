package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.model.UserFollowing;
import com.example.demo.model.UserFollowingPK;
import com.example.demo.respository.AccountRepository;
import com.example.demo.respository.UserFollowingRepository;

@RestController
public class AccountController {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private UserFollowingRepository userFollowingRepository;
	
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
	
	@GetMapping("/accounts/byName")
	public Iterable<Account> getByName(@RequestParam("name") String name) {
		if (name == null || name.equals("")) {
			return new ArrayList<>();
		}
		return accountRepository.findByUsernameContaining(name);
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
	
	@PostMapping("/account/follow/{id}")
	public Account follow(@PathVariable("id") int accountId, @RequestParam("targetId") int targetId) {
		Account account = accountRepository.findById(accountId).orElse(null);
		Account target = accountRepository.findById(targetId).orElse(null);
		if (account == null || target == null) {
			return null;
		}
		UserFollowing isFollowed = account.getUserFollowings1()
				.stream()
				.filter(c -> c.getAccount2().getId() == targetId)
				.findFirst()
				.orElse(null);
		
		if (isFollowed != null) {
			return target;
		}
		
		UserFollowing userFollowing = new UserFollowing();
		UserFollowingPK pk = new UserFollowingPK();
		pk.setAccountId(accountId);
		pk.setFollowId(targetId);
		userFollowing.setId(pk);
		userFollowing.setCreatedTime(new Date());
//		userFollowing.setAccount1(account);
//		userFollowing.setAccount2(target);
		userFollowingRepository.save(userFollowing);
		
		return accountRepository.findById(targetId).orElse(null);
	}
	
	@PostMapping("/account/unfollow/{id}")
	public Account unfollow(@PathVariable("id") int accountId, @RequestParam("targetId") int targetId) {
		Account account = accountRepository.findById(accountId).orElse(null);
		Account target = accountRepository.findById(targetId).orElse(null);
		if (account == null || target == null) {
			return null;
		}
		UserFollowingPK pk = new UserFollowingPK();
		pk.setAccountId(accountId);
		pk.setFollowId(targetId);
		userFollowingRepository.deleteById(pk);
		
		return accountRepository.findById(targetId).orElse(null);
	}
}
