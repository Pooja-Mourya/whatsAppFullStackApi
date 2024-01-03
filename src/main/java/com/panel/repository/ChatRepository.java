package com.panel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.panel.entity.MyChat;
import com.panel.entity.User;

public interface ChatRepository extends JpaRepository<MyChat, Integer>{

	List<MyChat> findByUsers_Id(Integer userId);

	MyChat findFirstByIsgroupFalseAndUsersContainsAndUsersContains(User user, User reqUser);

}
