package com.jomolangma.app.spring.web.dao;

import com.jomolangma.app.spring.web.domain.User;

public interface UserDao {
	public int getMatchCount(String name, String password);
	public User findUserByName(final String name);
	public void updateUser(User user);
	
	public void createUser(User user);
	public User getUserById(int id);
}
