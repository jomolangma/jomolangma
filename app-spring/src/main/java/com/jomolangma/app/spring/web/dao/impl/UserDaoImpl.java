package com.jomolangma.app.spring.web.dao.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import com.jomolangma.app.mybatis.dao.MyBatisDAO;
import com.jomolangma.app.spring.web.dao.UserDao;
import com.jomolangma.app.spring.web.domain.User;

public class UserDaoImpl implements UserDao{

	@Resource(name="myBatisDAO")
	MyBatisDAO<User> myBatisDAO;
	
	@Override
	public int getMatchCount(String name, String password) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("password", password);
		
		User user = myBatisDAO.findForObject("getMatchCount", map);
		return (user !=null)? 1:0;
	}

	@Override
	public User findUserByName(String name) {
		return myBatisDAO.findForObject("findUserByName", name);
	}

	@Override
	public void updateUser(User user) {
		myBatisDAO.update("updateUser", user);
	}

	@Override
	public void createUser(User user) {
		myBatisDAO.insert("createUser", user);
	}

	@Override
	public User getUserById(int id) {
		return myBatisDAO.findForObject("getUserById", id);
	}
	
}
