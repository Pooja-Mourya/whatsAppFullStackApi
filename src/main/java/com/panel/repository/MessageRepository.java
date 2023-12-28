package com.panel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.panel.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{

	@Query("select m from Message m join m.chat c where c.id=:chatId")
	List<Message> findByChatId(@Param("chatId") Integer chatId);

}
