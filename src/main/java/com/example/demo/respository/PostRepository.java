package com.example.demo.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Account;
import com.example.demo.model.Post;

public interface PostRepository extends PagingAndSortingRepository<Post, Integer>{
	@Query("SELECT p FROM Post p WHERE p.account.id IN (SELECT uf.id.followId FROM UserFollowing uf WHERE uf.id.accountId = :accountId)")
	public Page<Post> getFeed(@Param("accountId") int accountId, Pageable pageable);	
	
	public Iterable<Post> findByAccountOrderByCreatedTimeDesc(Account account);
}
