package com.bit2017.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2017.jblog.repository.CategoryDao;
import com.bit2017.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public boolean join(CategoryVo categoryVo){
		boolean result = categoryDao.insert(categoryVo);
		return result;
	}
	public List<CategoryVo> getMessageList(String id){
		return categoryDao.getList(id);
	}
	public boolean removeMessage(Long cno) {
		return categoryDao.delete(cno);
		
	}
	public boolean addMessage(CategoryVo categoryVo) {
		return categoryDao.insert(categoryVo);
	}
}
