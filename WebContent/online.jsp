<%@page import="java.sql.ResultSet"%>
<%@page import="com.DbDao"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户在线信息</title>
</head>
<body>
在线用户：
<table width="400" border="1">
<%
DbDao dd = new DbDao("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/test", "root", "");
// 查询 online_info 表中的全记录
ResultSet rs = dd.query("select * from test.online_info");
while (rs.next()) {
/*
Map<String, String> online = (Map<String, String>)application.getAttribute("online");
if (online == null)
{
	System.out.println("The online is null");
	return;
}
for (String sessionId : online.keySet()) {
*/
%>
<tr>
 	<td><%=rs.getString(2) %>
	<td><%=rs.getString(3) %>
	<td><%=rs.getString(4) %>
	<td><%=rs.getString(5) %>
	<td><%=rs.getString(6) %>
</tr>
<%
}
dd.closeConn();
%>
</table>
</body>
</html>