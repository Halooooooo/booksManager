<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'approve.jsp' starting page</title>
    
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
  function Money(){
   var spot = document.getElementById("spot").value;
   if(spot==-1){
   	alert("请选择派送地点");
   	return false;
   }
   if(spot==1){
   	var mySpan = document.getElementById("money");
	mySpan.innerHTML = "<font color='red' >0</font>";
   }
   if(spot==2){
   	var mySpan = document.getElementById("money");
	mySpan.innerHTML = "<font color='red' >10</font>";
   }
  }
  
  </script>
  <form action="${basePath }borrowExpress.user" method="POST">
  	<table>
  		<tr>
  			<td>选择地点</td>
  			<td><select id="spot" name="spot" onchange="Money()">
  				<option value="-1">请选择派送地点</option>
				<option value="1">市南区</option>
				<option value="2">市北区</option>
				</select></td>
  		</tr>
  		<tr>
  			<td>具体位置</td>
  			<td><input type="text" name="spotInfo"/></td>
  		</tr>
  		<tr>
  			<td>手机号</td>
  			<td><input type="text" name="phone"/></td>
  		</tr>
  		<tr>
  			<td>金额</td>
  			<td><span id="money" name="money"></span></td>
  		</tr>
  		<tr>
  			<td><input type="button" value="返回" onclick="javascript:history.go(-1)"/></td>
  			<td><input type="submit" value="提交" onclick="Money()"/></td>
  		</tr>
  	</table>
  	<input type="hidden" name="bookid" value="${ requestScope.bookid}"/>
  	<input type="hidden" name="bookname" value="${ requestScope.bookname}"/>
  </form>
  </body>
</html>
