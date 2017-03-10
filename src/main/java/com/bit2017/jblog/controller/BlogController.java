package com.bit2017.jblog.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bit2017.jblog.service.BlogService;
import com.bit2017.jblog.service.CategoryService;
import com.bit2017.jblog.vo.BlogVo;
import com.bit2017.jblog.vo.PostVo;
import com.bit2017.jblog.vo.UserVo;
import com.bit2017.security.Auth;
import com.bit2017.security.AuthUser;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;

	
	@RequestMapping("/{usersId}")
	public String blogMain(
			@RequestParam(value= "pno", required=false, defaultValue="") Long pno,
			@PathVariable("usersId") String usersId,
			@ModelAttribute BlogVo blogVo,
			@ModelAttribute PostVo postVo,
			Model model
			){
		System.out.println(blogVo);
		List<BlogVo> list = blogService.getList(blogVo); 
		List<PostVo> Postlist = blogService.getPostList(usersId); 
		PostVo vo = blogService.getPost(usersId, pno);
		model.addAttribute("vo", list);
		model.addAttribute("vo2",vo);
		model.addAttribute("postvo", Postlist);
		model.addAttribute("usersId",usersId);
		return "blog/blog-main";
	}
	
	@RequestMapping("/{usersId}/admin/basic")
	public String blogAdminBasic(
			@ModelAttribute BlogVo blogVo,
			Model model
			){
		List<BlogVo> vo = blogService.getList(blogVo); 
		model.addAttribute("vo", vo);
		return "blog/blog-admin-basic";
	}
	@Auth
	@RequestMapping("/{usersId}/adminbasic")
	public String update(
			@PathVariable("usersId") String usersId,
			@ModelAttribute BlogVo blogVo,
			@AuthUser UserVo authUser,
			Model model,
			@RequestParam("file") MultipartFile file
			){
		List<BlogVo> vo = blogService.getList(blogVo); 
		model.addAttribute("vo", vo);
		blogVo.setUsersId(authUser.getUsersId());
		System.out.println(blogVo);
		blogService.update(blogVo, file);
		return "redirect:/"+blogVo.getUsersId();
	}
	
	@RequestMapping("/{usersId}/admin/category")
	public String blogAdminCategory(
			@ModelAttribute BlogVo blogVo,
			Model model
			){
		List<BlogVo> vo = blogService.getList(blogVo); 
		model.addAttribute("vo", vo);
		return "blog/blog-admin-category";
	}
	
	@RequestMapping("/{usersId}/admin/write")
	public String blogAdminWriter(
			@PathVariable("usersId") String usersId,
			@ModelAttribute BlogVo blogVo,
			Model model
			){
		List<BlogVo> vo = blogService.getList(blogVo); 
		model.addAttribute("vo", vo);
		model.addAttribute("list", categoryService.getMessageList(usersId));
		System.out.println();
		return "blog/blog-admin-write";
	}
	
	@RequestMapping("/{usersId}/blogwrite")
	public String blogWrite(
			@ModelAttribute PostVo postVo,
			@ModelAttribute BlogVo blogVo,
			Model model
			){
		List<BlogVo> vo = blogService.getList(blogVo); /*
		List<PostVo> vo2 = blogService.getList2(postVo);*/
		model.addAttribute("vo", vo);/*
		model.addAttribute("vo2",vo2);*/
		System.out.println(postVo);
		blogService.blogWrite(postVo);
		return "redirect:/"+blogVo.getUsersId();
	}
}
