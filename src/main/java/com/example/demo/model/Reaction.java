package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the reaction database table.
 * 
 */
@Entity
@NamedQuery(name="Reaction.findAll", query="SELECT r FROM Reaction r")
public class Reaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReactionPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;

	//bi-directional many-to-one association to Post
	@ManyToOne
	@JoinColumn(name="post_id", insertable=false, updatable=false)
	private Post post;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="account_id", insertable=false, updatable=false)
	private Account account;

	public Reaction() {
	}

	public ReactionPK getId() {
		return this.id;
	}

	public void setId(ReactionPK id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}