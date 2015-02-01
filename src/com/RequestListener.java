package com;

import java.sql.ResultSet;
import java.util.jar.Attributes.Name;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Application Lifecycle Listener implementation class RequestListener
 *
 */
@WebListener
public class RequestListener implements ServletRequestListener, ServletRequestAttributeListener {

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent arg0)  { 
         // TODO Auto-generated method stub
    	HttpServletRequest request = (HttpServletRequest)arg0.getServletRequest();
    	System.out.println("----发向" + request.getRequestURI() + "请求被初始化----");
    	HttpSession session = request.getSession();
    	// 获取sessionId
    	String sessionId = session.getId();
    	// 获取访问的IP和正在访问的页面
    	String ip = request.getRemoteAddr();
    	String page = request.getRequestURI();
    	String userString = (String)session.getAttribute("user");
    	// 未登录用户当做游客处理
    	userString = (userString == null)? "游客" : userString;
    	try {
			DbDao ddDao = new DbDao("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/test", "root", "");
			ResultSet rsResultSet = ddDao.query("select * from test.online_info where sessionId = ?", sessionId);
			// 如果该用户的sessionId存在，表明是旧的会话
			if (rsResultSet.next()) {
				// 更新记录
				rsResultSet.updateString(5, page);
				rsResultSet.updateLong(6, System.currentTimeMillis());
				rsResultSet.updateRow();
				rsResultSet.close();
			}
			else {
				// 插入该用户的信息
				ddDao.insert("insert into test.online_info(sessionId, user, ip, page, requesttime) values(?, ?, ?, ?, ?)",
						sessionId, userString, ip, page, System.currentTimeMillis());
			}
			ddDao.closeConn();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent arg0)  { 
         // TODO Auto-generated method stub
    	HttpServletRequest request = (HttpServletRequest)arg0.getServletRequest();
    	System.out.println("----发向" + request.getRequestURI() + "请求被销毁----");
    }

	/**
     * @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent)
     */
    public void attributeRemoved(ServletRequestAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    	ServletRequest request = arg0.getServletRequest();
    	String name = arg0.getName();
    	Object value = arg0.getValue();
    	System.out.println(request + "范围内名为" + name + ",值为" + value + "的属性被删除了！");
    }

	/**
     * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
     */
    public void attributeAdded(ServletRequestAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    	ServletRequest request = arg0.getServletRequest();
    	String name = arg0.getName();
    	Object value = arg0.getValue();
    	System.out.println(request + "范围内添加了名为" + name + ",值为" + value + "的属性");
    }

	/**
     * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
     */
    public void attributeReplaced(ServletRequestAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    	ServletRequest request = arg0.getServletRequest();
    	String name = arg0.getName();
    	Object value = arg0.getValue();
    	System.out.println(request + "范围内名为" + name + ",值为" + value  + "的属性被替换了！");
    }
	
}
