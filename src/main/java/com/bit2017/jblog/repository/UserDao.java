package com.bit2017.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit2017.jblog.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(UserVo userVo){
		int count = sqlSession.insert("user.insert", userVo);
		return (count == 1);
	}

	public UserVo get(String usersId) {
		UserVo userVo = sqlSession.selectOne("user.getById", usersId);
		return userVo;
	}

	public UserVo get(String usersId, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("usersId", usersId);
		map.put("password", password);
		
		UserVo userVo = sqlSession.selectOne("user.getByIdAndPassword", map);
		
		return userVo;
	}
	
}
