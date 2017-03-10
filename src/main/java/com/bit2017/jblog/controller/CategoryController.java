package com.bit2017.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2017.jblog.dto.JSONResult;
import com.bit2017.jblog.service.CategoryService;
import com.bit2017.jblog.vo.CategoryVo;


@Controller
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	

	@ResponseBody
	@RequestMapping( "/list" )
	public JSONResult list(@RequestParam ("id") String id) {
		List<CategoryVo> list = 
				categoryService.getMessageList(id);
		
		return JSONResult.success( list );
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public JSONResult delete(@RequestParam ("cno") Long cno){
		boolean result = categoryService.removeMessage(cno);
		return JSONResult.success(result ? cno : -1) ;
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public JSONResult add(@ModelAttribute CategoryVo vo){
		boolean result = categoryService.addMessage(vo);
		return JSONResult.success(vo);
	}
}
