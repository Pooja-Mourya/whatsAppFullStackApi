package com.panel.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    private String displayName;
    private String profilePicture;
    private boolean online;
    
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String username, String password, String email, String displayName, String profilePicture,
			boolean online) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.displayName = displayName;
		this.profilePicture = profilePicture;
		this.online = online;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	@Override
	public int hashCode() {
		return Objects.hash(displayName, email, id, online, password, profilePicture, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(displayName, other.displayName) && Objects.equals(email, other.email) && id == other.id
				&& online == other.online && Objects.equals(password, other.password)
				&& Objects.equals(profilePicture, other.profilePicture) && Objects.equals(username, other.username);
	}
    
	
    
}
