package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Category;
import com.example.entity.Post;
import com.example.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	Page<Post> findByTitleContaining(String title, PageRequest of);
	
	// this method use for spring starter 2.6.6 lowest version
	// @Query("select p from Post p where p.title like");
	// List<Post> searchByKeyword(@Param("key") String keyword);
}
