package com.panel.request;

public class UserRequest {

	private String displayName;
    private String profilePicture;
    public UserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserRequest(String displayName, String profilePicture) {
		super();
		this.displayName = displayName;
		this.profilePicture = profilePicture;
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
}
