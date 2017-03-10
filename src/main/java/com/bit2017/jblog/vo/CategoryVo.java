package com.bit2017.jblog.vo;

public class CategoryVo {
	private Long cno;
	private String name;
	private String description;
	private String regDate;
	private String blogId;
	public Long getCno() {
		return cno;
	}
	public void setCno(Long cno) {
		this.cno = cno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	@Override
	public String toString() {
		return "CategoryVo [cno=" + cno + ", name=" + name + ", description=" + description + ", regDate=" + regDate
				+ ", blogId=" + blogId + "]";
	}
	
}
