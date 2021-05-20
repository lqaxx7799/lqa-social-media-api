package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.PostDTO;
import com.example.demo.dto.SignUpDTO;
import com.example.demo.model.Account;
import com.example.demo.model.Post;
import com.example.demo.respository.AccountRepository;
import com.example.demo.respository.PostRepository;

@RestController
@CrossOrigin(origins = "*")
@PropertySource("classpath:application.properties")
public class PostController {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private AccountRepository accountRepository;
	private File uploadDirRoot;
	
	@Autowired
	public PostController(@Value("${image.upload.dir}") String uploadDir, PostRepository postRepository, AccountRepository accountRepository) {
		this.uploadDirRoot = new File(uploadDir);
        this.accountRepository = accountRepository;
        this.postRepository = postRepository;
    }


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
	public ResponseEntity<?> addNew(@ModelAttribute PostDTO postDTO,
			@RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail
	) throws Exception {
		Account account = accountRepository.findById(postDTO.getAccountId()).orElseThrow();
		Post post = new Post();
		post.setCaption(postDTO.getCaption());
		post.setIsDeleted(false);
		post.setAccount(account);
		post.setCreatedTime(new Date());
		
		Post savedPost = postRepository.save(post);
		
		if (thumbnail != null && !thumbnail.isEmpty()) {
			File thumbnailFile;
			 
	        try {
	        	thumbnailFile = uploadPath(savedPost, thumbnail);
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }

	        try (InputStream in = thumbnail.getInputStream(); OutputStream out = new FileOutputStream(thumbnailFile)) {
	            FileCopyUtils.copy(in, out);
	        } catch (IOException ex) {
	            throw new RuntimeException(ex);
	        }
	        
	        savedPost.setThumbnailUrl(String.format("%s/%d/%s", "thumbnails", savedPost.getId(), thumbnail.getOriginalFilename()));
	        postRepository.save(savedPost);
		}
		
        return ResponseEntity.ok(savedPost);
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
	
	private File uploadPath(Post p, MultipartFile file) throws IOException {
        File uploadPath = Paths.get(this.uploadDirRoot.getPath(), "thumbnails", String.valueOf(p.getId())).toFile();
        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }
        return new File(uploadPath.getAbsolutePath(), file.getOriginalFilename());
    }
}
