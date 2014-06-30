package com.jomolangma.app.spring.web.service;

import com.jomolangma.app.spring.web.domain.User;

public interface UserService {
	public boolean hasMatchUser(String name, String password);
	public User findUserByName(String name);
	public void loginSuccess(User user);
	
	public void createUser(User user);
	public User getUserById(String userId);
}
