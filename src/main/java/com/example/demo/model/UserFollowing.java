package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;


/**
 * The persistent class for the user_following database table.
 * 
 */
@Entity
@Table(name="user_following")
@NamedQuery(name="UserFollowing.findAll", query="SELECT u FROM UserFollowing u")
public class UserFollowing implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserFollowingPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="account_id", insertable=false, updatable=false)
	@JsonBackReference(value="account1-userFollowings1")
	private Account account1;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="follow_id", insertable=false, updatable=false)
	@JsonBackReference(value="account2-userFollowings2")
	private Account account2;

	public UserFollowing() {
	}

	public UserFollowingPK getId() {
		return this.id;
	}

	public void setId(UserFollowingPK id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Account getAccount1() {
		return this.account1;
	}

	public void setAccount1(Account account1) {
		this.account1 = account1;
	}

	public Account getAccount2() {
		return this.account2;
	}

	public void setAccount2(Account account2) {
		this.account2 = account2;
	}

}