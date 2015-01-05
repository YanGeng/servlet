package com;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DbDao;

/**
 * Servlet implementation class LoginServlet
 */	
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String errMsg = "";
		// Servlet本身并不输出响应到客户端，因此必须将请求转发到视图页面
		RequestDispatcher rd;
		// 获取请求参数
		String username = request.getParameter("username");
		String user = new String(username.getBytes("ISO-8859-1"),"utf-8");
		System.out.println("解码前：" + username);
		System.out.println("解码后：" + user);
		String pass = request.getParameter("pass");
		try {
			DbDao dd = new DbDao("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/j2ee_go", "root", "");
			// 查询结果集    jdbc:mysql://localhost:3306/j2ee_go
			ResultSet rs = dd.query("select password from user_info" + " where user = ?", user);
			
			if (rs.next()) {
				// 用户名和密码匹配
				System.out.println("数据库中密码：" + rs.getString("password"));
				System.out.println("输入的密码：" + pass);
				if (rs.getString("password").equals(pass)) {
					// 获取session对象
					HttpSession session = request.getSession(true);
					// 设置session属性，跟踪用户会话状态
					session.setAttribute("name", username);
					// 获取转发对象
					rd = request.getRequestDispatcher("/welcome.jsp");
					// 转发请求
					rd.forward(request, response);
				} else {
					// 用户名和密码不匹配
					errMsg += "您输入的用户名和密码不符合，请重新输入";
				}
			} else {
				errMsg += "您的用户名不存在，请先注册";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 如果出错，转发到重新登录
		if (errMsg != null && !errMsg.equals("")) {
			rd = request.getRequestDispatcher("/login.jsp");
			request.setAttribute("err", errMsg);
			rd.forward(request, response);
		}
	}

}
