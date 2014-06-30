package com.jomolangma.app.spring.web.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jomolangma.app.spring.web.domain.LoginCommand;
import com.jomolangma.app.spring.web.domain.User;
import com.jomolangma.app.spring.web.service.UserService;

@Controller
public class LoginController{
	
	@Resource
	private UserService userService;
    
	@RequestMapping(value = "/index.html")
	public String loginPage(){
		return "login";
	}
	
	@RequestMapping(value = "/loginCheck.html")
	public ModelAndView loginCheck(HttpServletRequest request,LoginCommand loginCommand){
		boolean isValidUser = 
			   userService.hasMatchUser(loginCommand.getName(),
					                    loginCommand.getPassword());
		if (!isValidUser) {
			return new ModelAndView("login", "error", "error");
		} else {
			User user = userService.findUserByName(loginCommand.getName());
			user.setLastIp(request.getLocalAddr());
			user.setLastVisit(new Date());
			userService.loginSuccess(user);
			request.getSession().setAttribute("user", user);
			return new ModelAndView("main");
		}
	}
}
