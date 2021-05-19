package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.ServletContextResource;

@RestController
@PropertySource("classpath:application.properties")
public class ResourceController {
	private String uploadDir;
	public ResourceController(@Value("${image.upload.dir}") String uploadDir) {
		this.uploadDir = uploadDir; 
	}

	@RequestMapping("/images/**")
	@ResponseBody
	public ResponseEntity<Resource> getImageAsResource(HttpServletRequest request) throws FileNotFoundException {
		String uri = request.getRequestURI();
		int index = uri.indexOf("/images/");
		String path = uri.substring(index + "/images/".length(), uri.length());
		System.out.println(path);
	    HttpHeaders headers = new HttpHeaders();
	    File file = new File(this.uploadDir + "/" + path);
	    InputStream input = new FileInputStream(file);
	    Resource resource = new Resource() {
			
			@Override
			public InputStream getInputStream() throws IOException {
				return input;
			}
			
			@Override
			public long lastModified() throws IOException {
				return file.lastModified();
			}
			
			@Override
			public URL getURL() throws IOException {
				return file.toURL();
			}
			
			@Override
			public URI getURI() throws IOException {
				return file.toURI();
			}
			
			@Override
			public String getFilename() {
				return file.getName();
			}
			
			@Override
			public File getFile() throws IOException {
				return file;
			}
			
			@Override
			public String getDescription() {
				return null;
			}
			
			@Override
			public boolean exists() {
				return file.exists();
			}
			
			@Override
			public Resource createRelative(String relativePath) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public long contentLength() throws IOException {
				return file.length();
			}
		};
		String[] fileNames = file.getName().split("\\.");
		headers.set("Content-Type", "image/" + fileNames[fileNames.length - 1]);
	    return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
}
