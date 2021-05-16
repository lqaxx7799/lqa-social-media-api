package com.example.demo.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LogInDTO;
import com.example.demo.model.Account;
import com.example.demo.model.Post;
import com.example.demo.respository.AccountRepository;
import com.example.demo.util.CommonUtil;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {
	@Autowired
	private AccountRepository accountRepository;

	@PostMapping("/authentication/logIn")
	public String addNew(@RequestBody LogInDTO logInDTO, HttpServletResponse response) {
		System.out.println(logInDTO.getEmail());
		Account account = new Account();
		account.setEmail(logInDTO.getEmail());
		
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny();
		
		Example<Account> accountExample = Example.of(account, exampleMatcher);
		
		Account found = accountRepository.findOne(accountExample).orElse(null);
		System.out.println(found);
		if (found == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return "Wrong email";
		}
		
		String hashedPassword = CommonUtil.generateSHA1(logInDTO.getPassword());
		if (!hashedPassword.equals(found.getPassword())) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return "Wrong password";
		}
		
		return String.valueOf(found.getId());
	}
}
