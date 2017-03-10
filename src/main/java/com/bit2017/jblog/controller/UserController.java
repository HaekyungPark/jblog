package com.bit2017.jblog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2017.jblog.dto.JSONResult;
import com.bit2017.jblog.service.UserService;
import com.bit2017.jblog.vo.UserVo;
import com.bit2017.security.Auth;
import com.bit2017.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Auth
	@ResponseBody
	@RequestMapping("/checkId")
	public JSONResult checkId(
			@RequestParam( value="usersId", required=true, defaultValue="")String usersId ,
			@AuthUser UserVo authUser,
			@ModelAttribute UserVo userVo
			){
			userVo.setUsersId(authUser.getUsersId());
		boolean isExists = userService.exists( usersId );
		return JSONResult.success( isExists ? "exist" : "not exist" );
	}
	@RequestMapping("/joinform")
	public String joinform(@ModelAttribute UserVo userVo){
		return "user/join";
	}
	
	@RequestMapping("/join")
	public String join(
			@ModelAttribute @Valid UserVo userVo,
			BindingResult result,
			Model model
			){
		if( result.hasErrors() == true ) {
			List<ObjectError> list = result.getAllErrors();
			for( ObjectError error : list ) {
				System.out.println( error );
			}
			model.addAllAttributes( result.getModel() );
			return "/user/join";
		}
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess(){
		return "user/joinsuccess";
	}
	
	@RequestMapping("/loginform")
	public String loginform(){
		return "user/login";
	}

}
