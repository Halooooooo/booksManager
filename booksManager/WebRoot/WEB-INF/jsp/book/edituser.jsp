<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<script type="text/javascript">
</script>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改用户</title>
    
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
  <body class="rightBody">
<form action="/booksManager/edituser.book" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>用户管理</strong>&nbsp;-&nbsp;修改用户</div></div>
    <div class="tableH2">修改用户信息信息</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="usertype" width="200px">用户身份：</td>
            <td><c:out value="${userType[user.u_type] }"></c:out>
			 </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">用户名称：</td>
            <td><input type="text" id="userName" name="u_name" value="${user.u_name }"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">电话：</td>
            <td><input type="text" name="phone" value="${user.phone }"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">email：</td>
            <td><input type="text" name="email" value="${user.email}"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">idcard：</td>
            <td><input type="text" name="idcard" value="${user.idcard }"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">目前可借书数目：</td>
            <td><input type="text" name="borrow" value="${user.borrow }"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">余额：</td>
            <td><c:out value="${user.money }"/></td>
        </tr>
    </table>
        <input type="hidden" name="userid" value="${user.u_id }" />
    
    <div class="tc mt20">
        <input type="submit" class="btnB2" value="保存" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
  </body>
</html>
