package com.example.demo.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class SignUpDTO {
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date dateOfBirth;
	private String email;
	private String gender;
	private String name;
	private String username;
	private String password;
	private String reenterPassword;
	private String phoneNumber;
//	private MultipartFile profilePicture;
	private String bio;

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReenterPassword() {
		return reenterPassword;
	}

	public void setReenterPassword(String reenterPassword) {
		this.reenterPassword = reenterPassword;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

//	public MultipartFile getProfilePicture() {
//		return profilePicture;
//	}
//
//	public void setProfilePicture(MultipartFile profilePicture) {
//		this.profilePicture = profilePicture;
//	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

}
