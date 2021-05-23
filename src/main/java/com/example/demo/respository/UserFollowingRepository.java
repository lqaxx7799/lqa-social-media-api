package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserFollowing;
import com.example.demo.model.UserFollowingPK;

public interface UserFollowingRepository extends JpaRepository<UserFollowing, UserFollowingPK>{

}
