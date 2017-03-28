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
    
    <title>My JSP 'recharge.jsp' starting page</title>
    
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
  <script type="text/javascript">
function testDouble(){
	var resistance=document.getElementByName("money");
	if(!/^[0-9]*$/.test(resistance.value)){
	alert("输入数字");
	}
}
  </script>
  
  	用户名: <c:out value="${requestScope.user.u_name }"></c:out>
  	<br>
  	余额  : <c:out value="${requestScope.user.money}"></c:out>
  	<br>
  	<form action="${basePath }recharge.book" method="post">
  		充值金额 : <input type="text" name="money" onblur="testDouble()"/>
  		<input type="submit" value="确认充值" />
  		<input type="hidden" name="userid" value="${requestScope.user.u_id }"/>
  	</form>
  </body>
</html>
