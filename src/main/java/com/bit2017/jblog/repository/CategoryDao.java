package com.bit2017.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2017.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(CategoryVo categoryVo){
		System.out.println(categoryVo);
		int count = sqlSession.insert("category.insert", categoryVo);
		return (count == 1);
	}

	public List<CategoryVo> getList(String id){
		return sqlSession.selectList("category.getList", id);
	}
	public boolean delete(Long cno){
		int count = sqlSession.delete("category.delete",cno);
		return (count==1);
	}
}
