package com.jomolangma.app.spring.introduce;

public class ForumService {

	public void removeTopic(int topicId) {
		System.out.println("模拟删除topic记录:"+topicId);
		try {
			Thread.currentThread();
			Thread.sleep(20);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		

	}

	public void removeForum(int forumId) {
		System.out.println("模拟删除Forum记录:"+forumId);
		try {
			Thread.currentThread();
			Thread.sleep(40);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}
}
