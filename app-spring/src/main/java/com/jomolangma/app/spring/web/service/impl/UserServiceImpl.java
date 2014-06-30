package com.jomolangma.app.spring.web.service.impl;

import javax.annotation.Resource;

import com.jomolangma.app.spring.web.dao.LoginLogDao;
import com.jomolangma.app.spring.web.dao.UserDao;
import com.jomolangma.app.spring.web.domain.LoginLog;
import com.jomolangma.app.spring.web.domain.User;
import com.jomolangma.app.spring.web.service.UserService;

public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Resource
	private LoginLogDao loginLogDao;

	public boolean hasMatchUser(String name, String password) {
		int matchCount = userDao.getMatchCount(name, password);
		return matchCount > 0;
	}

	public User findUserByName(String name) {
		return userDao.findUserByName(name);
	}

	public void loginSuccess(User user) {
		user.setCredits(5 + user.getCredits());
		LoginLog loginLog = new LoginLog();
		loginLog.setUserId(user.getId());
		loginLog.setIp(user.getLastIp());
		loginLog.setLoginDate(user.getLastVisit());
		userDao.updateUser(user);
		loginLogDao.insertLoginLog(loginLog);
	}

	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
