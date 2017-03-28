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
    
    <title>My JSP 'home.jsp' starting page</title>
    
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
  <script>
  function doVIP(){
  	 var a = window.confirm("将会从账户中扣除10元");
  	 if(a){
  		 alert("成功");
  		 document.location ="${basePath}VIP.user?username=${sessionScope.loginInfo[0]}";
  	 }else{
  	 	return false;
  	 }
  }
  </script>
  <table>
  		<tr>
  		<td><h3>你好牛逼的<c:out value="${sessionScope.loginInfo[0] }"></c:out></h3></td>
  		<td align="left" ><a  href="${basePath }exit.book">退出</a></td>
  		</tr>
  	</table>
  <table>
  	<a href="${basePath }borrow.user" >借书系统</a>
  	<br>
  	<input type="button" onclick="javascript:doVIP();" value="成为会员"/>
  </table>
  </body>
</html>
