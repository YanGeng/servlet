package com;

import java.io.PrintStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void service (HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException,java.io.IOException
    {
    	// 设置编码方式
    	request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
    	
    	// 获取name,gender,color,national的请求参数值
    	// new String(request.getParameter("gender").getBytes("ISO-8859-1"),"utf-8");
    	String name = new String(request.getParameter("name").getBytes("ISO-8859-1"),"utf-8");
    	String gender = new String(request.getParameter("gender").getBytes("iso-8859-1"),"utf-8");
    	String[] colorT = request.getParameterValues("color");
    	
    	String[] color = new String[colorT.length];
    	int i = 0;
    	for (String c : colorT) {
    		color[i++] = new String(c.getBytes("iso-8859-1"), "utf-8");
    	}
    	
    	String national = new String(request.getParameter("country").getBytes("iso-8859-1"),"utf-8");
    	
    	// 获取页面输出流
    	PrintStream out = new PrintStream(response.getOutputStream());
    	
    	// 输出html页面标签
    	out.println("<html>");
    	out.println("<head>");
    	out.println("<title>Servlet测试</title></head>");
    	out.println("<body>");
    	out.println("您的名字：" + name + "<hr/>");
    	out.println("您的性别：" + gender + "<hr/>");
    	out.println("您喜欢的颜色：");
    	for (String c : color) {
    		out.println(c + " ");
    	}
    	out.println("<hr/>");
    	out.println("您喜欢的颜色：");
    	out.println("您来自的国家：" + national + "<hr/>");
    	
    	out.println("</body>");
    	out.println("</html>");
    }
}
