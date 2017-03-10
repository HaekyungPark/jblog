package com.bit2017.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit2017.jblog.repository.BlogDao;
import com.bit2017.jblog.repository.CategoryDao;
import com.bit2017.jblog.repository.UserDao;
import com.bit2017.jblog.vo.BlogVo;
import com.bit2017.jblog.vo.CategoryVo;
import com.bit2017.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	
	@Transactional
	public boolean join(UserVo userVo){
		BlogVo blogVo = new BlogVo();
		blogVo.setUsersId(userVo.getUsersId());
		blogVo.setTitle("타이틀");
		blogVo.setLogo("로고");
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlogId(userVo.getUsersId());
		categoryVo.setName("제목");
		categoryVo.setDescription("설명");
		
		boolean result1 = userDao.insert(userVo);
		boolean result2 = blogDao.insert(blogVo);
		boolean result3 = categoryDao.insert(categoryVo);
		return result1&&result2&&result3;
	}

	public boolean exists(String usersId) {
		UserVo userVo = userDao.get(usersId);
		return (userVo!=null);
	}

	public UserVo getUser(String usersId, String password) {
		UserVo userVo = userDao.get(usersId, password);
		return userVo;
	}
	public UserVo getUser(String usersId){
		UserVo userVo = userDao.get(usersId);
		return userVo;
	}

}
