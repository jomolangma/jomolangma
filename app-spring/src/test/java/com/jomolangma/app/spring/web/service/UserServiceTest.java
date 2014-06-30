package com.jomolangma.app.spring.web.service;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.jomolangma.app.spring.web.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:DATASOURCE_CONFIG.xml", "classpath:APPLICATION_SERVICE_BEAN.xml"})
@TransactionConfiguration(defaultRollback = true)

public class UserServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	UserService userService;
	
	@Test
	public void testHasMatchUser() {
		boolean result = userService.hasMatchUser("admin", "123456");
		Assert.assertEquals(true, result);
	}

	@Test
	public void testFindUserByUserName() {
		User user = userService.findUserByName("admin");
		Assert.assertNotNull(user);
	}
	
	@Test
	public void testLoginSuccess() {
		User user = userService.findUserByName("admin");
		user.setLastIp("127.0.0.1");
		user.setLastVisit(new Date());
		
		userService.loginSuccess(user);
	}
}