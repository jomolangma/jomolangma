package com.jomolangma.app.spring.anno;

public class ForumService {
    @NeedTest(value=true)
	public void deleteForum(int forumId){
    	System.out.println("模拟删除Forum模块:"+forumId);
	}
    /**
     * 
     * @param topicId
     */
    @NeedTest(value=false)
    public void deleteTopic(int topicId){
    	System.out.println("模拟删除Forum主题:"+topicId);
	}	
}
