package com.panel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.panel.entity.MyChat;
import com.panel.entity.User;

public interface ChatRepository extends JpaRepository<MyChat, Integer>{

	@Query("select c from MyChat c join c.users u where u.id=:userId ")
	List<MyChat> findByUserId(@Param("userId")Integer userId);

//	@Query("select c from MyChat c where c.isgroup = false And :user Member of c.users And :reqUser Member of c.users ")
//	public MyChat findSingleChatByUserIds(@Param("user") User user, @Param("reqUser") User reqUser);
	
	@Query("SELECT c FROM MyChat c WHERE c.isgroup = false AND :user MEMBER OF c.users AND :reqUser MEMBER OF c.users")
	public MyChat findSingleChatByUserIds(@Param("user") User user, @Param("reqUser") User reqUser);

	

}
