package com.jomolangma.app.spring.anno;

public class ForumService {
    @NeedTest(value=true)
	public void deleteForum(int forumId){
    	System.out.println("ģ��ɾ��Forumģ��:"+forumId);
	}
    /**
     * 
     * @param topicId
     */
    @NeedTest(value=false)
    public void deleteTopic(int topicId){
    	System.out.println("ģ��ɾ��Forum����:"+topicId);
	}	
}
