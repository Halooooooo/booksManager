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
    
    <title>新增书籍</title>
    
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
<form action="/booksManager/add.book" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>书籍管理</strong>&nbsp;-&nbsp;新增书籍</div></div>
    <div class="tableH2">新增书籍</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">所属类别：</td>
            <td><select size="1" name="n_bt" style="width: 91; height: 18">
				<c:forEach var="type" items="${requestScope.booktype}">
				 <option value="${type.id }"><c:out value="${type.booktype}"/>
				 </option>
				  </c:forEach>
				</select>
			 </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">书籍名称：</td>
            <td><input type="text" id="bookName" name="bookName"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">作者：</td>
            <td><input type="text" name="author"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">价格：</td>
            <td><input type="text" name="price"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">总数量：</td>
            <td><input type="text" name="totalNumber"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">现有数量：</td>
            <td><input type="text" name="nowNumber"/></td>
        </tr>
    </table>
    <div class="tc mt20">
        <input type="submit" class="btnB2" value="保存" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
  </body>
</html>
