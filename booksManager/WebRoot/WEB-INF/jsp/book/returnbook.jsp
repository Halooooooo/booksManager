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
    
    <title>returnbook</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script>
		function doReturn(bookid,userid){
			document.forms[0].action="${basePath}return.book?bookid="+bookid+"&userid="+userid;
			document.forms[0].submit();
		}
		</script>
  </head>
  
  <body>
      <h3><a href="${basePath }home.book" >返回首页</a></h3>
  
    <br>
    <form action="" method="post">
		<table width="100%" border="0">
		   <tr class="t_tit">
			  <td width="30" align="center"><input type="checkbox" id="selAll" onclick="doSelectAll()" /></td>
		      <td width="200" align="center">借书者</td>
		      <td width="140" align="center">书籍</td>
		      <td width="200" align="center">操作</td>
		  </tr>
		  <c:forEach items="${requestScope.userList}" var="user">
		      <tr bgcolor="f8f8f8">
		          <td align="center"><input type="checkbox" name="selectedRow" value="${user.u_id }"/></td>
		          <td align="center">${user.u_name}</td>
		          <td align="center">${book.bookName}</td>
		          <td align="center">
		              <a href="javascript:doReturn(${book.bookId },${user.u_id })">还书</a>
		          </td>
		        </tr>
		    </c:forEach>
		</table>
    </form>
  </body>
</html>
