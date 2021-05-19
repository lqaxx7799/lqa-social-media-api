package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.LogInDTO;
import com.example.demo.dto.SignUpDTO;
import com.example.demo.model.Account;
import com.example.demo.model.Post;
import com.example.demo.respository.AccountRepository;
import com.example.demo.util.CommonUtil;

@RestController
@CrossOrigin(origins = "*")
@PropertySource("classpath:application.properties")
public class AuthenticationController {
	@Autowired
	private AccountRepository accountRepository;
	private File uploadDirRoot;
	
	@Autowired
	public AuthenticationController(@Value("${image.upload.dir}") String uploadDir, AccountRepository accountRepository) {
		this.uploadDirRoot = new File(uploadDir);
        this.accountRepository = accountRepository;
    }

	@PostMapping("/authentication/logIn")
	public String logIn(@RequestBody LogInDTO logInDTO, HttpServletResponse response) {
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
	
	@PostMapping(value = "/authentication/signUp", consumes = { "multipart/form-data" })
	public ResponseEntity<?> signUp(
			@ModelAttribute SignUpDTO signUpDTO,
			@RequestPart(value = "profilePicture", required = false) MultipartFile profilePicture
	) throws Exception {
		Account account = new Account();
		account.setBio(signUpDTO.getBio());
		account.setDateOfBirth(signUpDTO.getDateOfBirth());
		account.setEmail(signUpDTO.getEmail());
		account.setGender(signUpDTO.getGender());
		account.setName(signUpDTO.getName());
		account.setPassword(CommonUtil.generateSHA1(signUpDTO.getPassword()));
		account.setPhoneNumber(signUpDTO.getPhoneNumber());
		account.setUsername(signUpDTO.getUsername());
		account.setBio(signUpDTO.getBio());
		
		Account savedAccount = accountRepository.save(account);
		
		System.out.println(profilePicture);
		
		File profilePictureFile;
		 
        try {
        	profilePictureFile = uploadPath(savedAccount, profilePicture);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (InputStream in = profilePicture.getInputStream(); OutputStream out = new FileOutputStream(profilePictureFile)) {
            FileCopyUtils.copy(in, out);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
        savedAccount.setProfilePictureUrl(String.format("%s/%d/%s", "profilePictures", savedAccount.getId(), profilePicture.getOriginalFilename()));
        accountRepository.save(savedAccount);
        
        return ResponseEntity.ok(savedAccount);
	}
	
	private File uploadPath(Account a, MultipartFile file) throws IOException {
        File uploadPath = Paths.get(this.uploadDirRoot.getPath(), "profilePictures", String.valueOf(a.getId())).toFile();
        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }
        System.out.println(uploadPath.getAbsolutePath());
        System.out.println(uploadPath.exists());
        System.out.println(file.getOriginalFilename());
        return new File(uploadPath.getAbsolutePath(), file.getOriginalFilename());
    }
}
