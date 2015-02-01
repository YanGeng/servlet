package com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Application Lifecycle Listener implementation class OnlineListener2
 *
 */
@WebListener
public class OnlineListener2 implements ServletContextListener {


	// 超过该时间（10分钟）没有访问本站即认为用户已经离线
	public final int MAX_MILLIS = 30 * 60 * 1000;
	
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	new javax.swing.Timer(60 * 1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("The ServletContextListener is running for deleting the old sessionid");
				try {
					DbDao dcDao = new DbDao("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/test", "root", "");
					ResultSet rsResultSet = dcDao.query("select * from test.online_info");
					StringBuffer beRomove = new StringBuffer("(");
					while (rsResultSet.next()) {
						// 如果距离上次时间超过了指定时间
						if (System.currentTimeMillis() - rsResultSet.getLong(6) > MAX_MILLIS) {
							// 将需要删除的sessionId添加进来
							beRomove.append("'");
							beRomove.append(rsResultSet.getString(2));
							beRomove.append("' , ");
						}
					}
					// 有需要删除的记录
					if (beRomove.length() > 3) {
						beRomove.setLength(beRomove.length() - 3);
						beRomove.append(")");
						// 删除所有"超过指定时间未重新请求的记录"
						System.out.println("The deleted sessionId is: " + beRomove.toString());
						dcDao.modify("delete from test.online_info where sessionId in " + beRomove.toString());
					}
					dcDao.closeConn();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}).start();
    }
	
}
