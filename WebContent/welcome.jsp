<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% out.println(new String(session.getAttribute("name").toString().getBytes("ISO-8859-1"), "utf-8")); %>
<br/>
登陆成功
<br/>
欢迎您的光临！
</body>
</html>