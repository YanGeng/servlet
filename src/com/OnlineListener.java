package com;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class OnlineListener
 *
 */
@WebListener
public class OnlineListener implements HttpSessionListener {

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	System.out.println("Session listenner(online) is called ******** start");
    	HttpSession session = arg0.getSession();
    	ServletContext application = session.getServletContext();
    	// 获取sessionid
    	String sessionId = session.getId();
    	// 如果是一次新的会话
    	if (session.isNew())
    	{
    		String user = (String)session.getAttribute("user");
    		// 未登录用户当游客处理
    		user = (user == null)? "游客" : user;
    		Map<String, String> online = (Map<String, String>)application.getAttribute("online");
    		if (online == null) {
				online = new Hashtable<String, String>();
			}
    		online.put(sessionId, user);
    		application.setAttribute("online", online);
    	}
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	System.out.println("Session listenner(online) is called ******** finished");
    	HttpSession session = arg0.getSession();
    	ServletContext application = session.getServletContext();
    	String sessionId = session.getId();
    	Map<String, String> online = (Map<String, String>)application.getAttribute("online");
    	if (online != null) {
			// 删除该用户的在线信息
    		online.remove(sessionId);
		}
    	application.setAttribute("online", online);
    }
	
}
