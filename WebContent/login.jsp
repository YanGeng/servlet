<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>new document</title>
</head>
<body>
<!-- 输出出错提示 -->
<span style="color:red;font-weight:bold">
<%
if (request.getAttribute("err") != null) {
	out.println(request.getAttribute("err") + "<br/>");
}
%>
</span>
<%
if (request.getParameter("username") != null) {
	out.println(new String(request.getParameter("username").getBytes("ISO-8859-1"), "utf-8"));
	out.println("先生（小姐）<br/><br/>");
}
%>
请输入用户名和密码：
<!-- 登陆表单，提交到一个Servlet -->
<form id="login" method="post" action="login">
用户名：<input type="text" name="username"/><br/>
密&nbsp;&nbsp码：<input type="password" name="pass"><br/>
<input type="submit"  value="登陆"/><br/>
</form>
</body>
</html>