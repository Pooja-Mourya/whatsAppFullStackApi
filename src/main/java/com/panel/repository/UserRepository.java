package com.panel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.panel.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

//	User findByUserId(Integer userId2);
	
	public User findByEmail(String email);

	public User findUserById(Integer userId);
	
//	@Query("select u from user u where u.displayName Like %:query% or u.email Like %:query%")
//	public List<User> searchUser(@Param("query") String query);

}
