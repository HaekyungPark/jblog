package com.bit2017.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2017.jblog.vo.BlogVo;
import com.bit2017.jblog.vo.PostVo;


@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(BlogVo blogVo){
		int count = sqlSession.insert("blog.insert", blogVo);
		return (count == 1);
	}

	public boolean write(PostVo postVo) {
		int count = sqlSession.insert("post.insert", postVo);
		return (count == 1);
	}

	public boolean update(BlogVo blogVo) {
		int count = sqlSession.update("blog.update", blogVo);
		return (count == 1);
	}
	public long getMaxPno(String blogId){
		return sqlSession.selectOne("post.getMaxPno", blogId);
	}
	public List<BlogVo> getList(BlogVo blogVo) {
		return sqlSession.selectList("blog.getList", blogVo);
	}

	public PostVo getPost(Long maxPno) {
		return sqlSession.selectOne("post.getPost", maxPno);
	}

	public List<PostVo> getPostList(String blogId) {
		return sqlSession.selectList("post.getList", blogId);
	}
	

}
