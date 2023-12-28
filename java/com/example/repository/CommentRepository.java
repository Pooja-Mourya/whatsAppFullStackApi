package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Comments;

public interface CommentRepository extends JpaRepository<Comments, Integer>{

}
