<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Home</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<table>
  		<tr>
  		<td><h3>你好牛逼的<c:out value="${sessionScope.loginInfo[0] }"></c:out></h3></td>
  		<td align="left" ><a  href="${basePath }exit.book">退出</a></td>
  		</tr>
  	</table>
  		<br>
  		<a href="${basePath }checkapproveUI.book" >审批借书</a>
  		<br>
  		<a href="${basePath }list.book">书籍管理</a>
  		<br>
  		<a href="${basePath }user.book">用户管理</a>
  		<br>
  		<table>
  			<tr><th>报告:</th></tr>
  			<tr><td><a href="${basePath }reportborrowbook.book">被借书本名单</a></td></tr>
  			<tr><td><a href="${basePath }QueryBill.book">费用报告</a></td></tr>
  			<tr><td><a href="${basePath }reportborrowbook.book">收取现金费用报告</a></td></tr>
  			<tr><td><a href="${basePath }reportborrowbook.book">收取罚款费用报告</a></td></tr>
  		</table>
  </body>
</html>
