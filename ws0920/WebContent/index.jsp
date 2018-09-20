<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="org.salem.chat.*" %>
    
<%
	String ip = request.getRemoteAddr();

	ChatManager.INSTANCE.broadcast(ip);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
</body>
</html>