package com.jomolangma.app.spring.web.dao.impl;

import javax.annotation.Resource;

import com.jomolangma.app.mybatis.dao.MyBatisDAO;
import com.jomolangma.app.spring.web.dao.LoginLogDao;
import com.jomolangma.app.spring.web.domain.LoginLog;


public class LoginLogDaoImpl implements LoginLogDao {
	
	@Resource(name="myBatisDAO")
	MyBatisDAO<LoginLog> myBatisDAO;
	
	public void insertLoginLog(LoginLog loginLog) {
		myBatisDAO.insert("addLoginLog", loginLog);
	}
}