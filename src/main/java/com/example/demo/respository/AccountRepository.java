package com.example.demo.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	@Query("SELECT a FROM Account a WHERE a.id IN (SELECT uf.id.accountId FROM UserFollowing uf WHERE uf.id.followId = :accountId)")
	public Iterable<Account> getFollowed(@Param("accountId") int accountId);
	
	@Query("SELECT a FROM Account a WHERE a.id IN (SELECT uf.id.followId FROM UserFollowing uf WHERE uf.id.accountId = :accountId)")
	public Iterable<Account> getFollowing(@Param("accountId") int accountId);
}
