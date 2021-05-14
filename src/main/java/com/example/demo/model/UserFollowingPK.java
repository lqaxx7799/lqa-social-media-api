package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the user_following database table.
 * 
 */
@Embeddable
public class UserFollowingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

//	@Column(name="account_id", insertable=false, updatable=false)
	@Column(name="account_id")
	private int accountId;

//	@Column(name="follow_id", insertable=false, updatable=false)
	@Column(name="follow_id")
	private int followId;

	public UserFollowingPK() {
	}
	public int getAccountId() {
		return this.accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getFollowId() {
		return this.followId;
	}
	public void setFollowId(int followId) {
		this.followId = followId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserFollowingPK)) {
			return false;
		}
		UserFollowingPK castOther = (UserFollowingPK)other;
		return 
			(this.accountId == castOther.accountId)
			&& (this.followId == castOther.followId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.accountId;
		hash = hash * prime + this.followId;
		
		return hash;
	}
}