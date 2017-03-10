package com.bit2017.jblog.vo;

public class BlogVo {
	private String usersId;
	private String title;
	private String logo;
	public String getUsersId() {
		return usersId;
	}
	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Override
	public String toString() {
		return "BlogVo [usersId=" + usersId + ", title=" + title + ", logo=" + logo + "]";
	}
	
}
