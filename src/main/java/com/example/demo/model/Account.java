package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Lob
	private String bio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_of_birth")
	private Date dateOfBirth;

	private String email;

	private String gender;

	private String name;

	private String password;

	@Column(name="phone_number")
	private String phoneNumber;

	@Lob
	@Column(name="profile_picture_url")
	private String profilePictureUrl;

	private String username;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="account")
	private List<Comment> comments;

	//bi-directional many-to-one association to Post
	@OneToMany(mappedBy="account")
	@JsonBackReference(value="account-posts")
	private List<Post> posts;

	//bi-directional many-to-one association to Reaction
	@OneToMany(mappedBy="account")
	private List<Reaction> reactions;

	//bi-directional many-to-one association to UserFollowing
	@OneToMany(mappedBy="account1")
	@JsonManagedReference(value="account1-userFollowings1")
	private List<UserFollowing> userFollowings1;

	//bi-directional many-to-one association to UserFollowing
	@OneToMany(mappedBy="account2")
	@JsonManagedReference(value="account2-userFollowings2")
	private List<UserFollowing> userFollowings2;

	public Account() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProfilePictureUrl() {
		return this.profilePictureUrl;
	}

	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setAccount(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setAccount(null);

		return comment;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post addPost(Post post) {
		getPosts().add(post);
		post.setAccount(this);

		return post;
	}

	public Post removePost(Post post) {
		getPosts().remove(post);
		post.setAccount(null);

		return post;
	}

	public List<Reaction> getReactions() {
		return this.reactions;
	}

	public void setReactions(List<Reaction> reactions) {
		this.reactions = reactions;
	}

	public Reaction addReaction(Reaction reaction) {
		getReactions().add(reaction);
		reaction.setAccount(this);

		return reaction;
	}

	public Reaction removeReaction(Reaction reaction) {
		getReactions().remove(reaction);
		reaction.setAccount(null);

		return reaction;
	}

	public List<UserFollowing> getUserFollowings1() {
		return this.userFollowings1;
	}

	public void setUserFollowings1(List<UserFollowing> userFollowings1) {
		this.userFollowings1 = userFollowings1;
	}

	public UserFollowing addUserFollowings1(UserFollowing userFollowings1) {
		getUserFollowings1().add(userFollowings1);
		userFollowings1.setAccount1(this);

		return userFollowings1;
	}

	public UserFollowing removeUserFollowings1(UserFollowing userFollowings1) {
		getUserFollowings1().remove(userFollowings1);
		userFollowings1.setAccount1(null);

		return userFollowings1;
	}

	public List<UserFollowing> getUserFollowings2() {
		return this.userFollowings2;
	}

	public void setUserFollowings2(List<UserFollowing> userFollowings2) {
		this.userFollowings2 = userFollowings2;
	}

	public UserFollowing addUserFollowings2(UserFollowing userFollowings2) {
		getUserFollowings2().add(userFollowings2);
		userFollowings2.setAccount2(this);

		return userFollowings2;
	}

	public UserFollowing removeUserFollowings2(UserFollowing userFollowings2) {
		getUserFollowings2().remove(userFollowings2);
		userFollowings2.setAccount2(null);

		return userFollowings2;
	}

}