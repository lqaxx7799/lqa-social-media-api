package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the reaction database table.
 * 
 */
@Embeddable
public class ReactionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

//	@Column(name="account_id", insertable=false, updatable=false)
	@Column(name="account_id")
	private int accountId;

//	@Column(name="post_id", insertable=false, updatable=false)
	@Column(name="post_id")
	private int postId;

	public ReactionPK() {
	}
	public int getAccountId() {
		return this.accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getPostId() {
		return this.postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReactionPK)) {
			return false;
		}
		ReactionPK castOther = (ReactionPK)other;
		return 
			(this.accountId == castOther.accountId)
			&& (this.postId == castOther.postId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.accountId;
		hash = hash * prime + this.postId;
		
		return hash;
	}
}