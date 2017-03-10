package com.bit2017.jblog.vo;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class UserVo {
	private String usersId;
	
	@Length(min=2, max=5)
	private String name;
	
	@Pattern( regexp="^[0-9]{4,}$" )
	private String password;
	
	private String regDate;
	
	public String getUsersId() {
		return usersId;
	}
	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "UserVo [usersId=" + usersId + ", name=" + name + ", password=" + password + ", regDate=" + regDate + "]";
	}
	
}
