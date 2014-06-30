package com.jomolangma.app.spring.advice;

import java.sql.SQLException;

public class ForumService {
	public void removeForum(int forumId) {
		// do sth...
		throw new RuntimeException("Runtime Exception");
	}
	public void updateForum(Forum forum) throws Exception{
		// do sth...
		throw new SQLException("SQL Exception");
	}
}
