<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib	uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript">
  		function doSelectAll(){
			// jquery 1.6 前
			//$("input[name=selectedRow]").attr("checked", $("#selAll").is(":checked"));
			//prop jquery 1.6+建议使用
			$("input[name=selectedRow]").prop("checked", $("#selAll").is(":checked"));		
		}
		function doAdd()
		{
			document.forms[0].action = "${basePath}addUI.book";
			document.forms[0].submit();
		}
		function doDelete(id)
		{
			document.forms[0].action = "${basePath}delete.book?bookid="+id;
			document.forms[0].submit();
		}
		function doEdit(id)
		{
			document.forms[0].action = "${basePath}editUI.book?bookid="+id;
			document.forms[0].submit();
		}
		function doReturn(id){
			document.forms[0].action = "${basePath}returnUI.book?bookid="+id;
			document.forms[0].submit();
		}
		function doExportPDF(){
			window.open("${basePath}book_exportPDF.book");
		}
  </script>
    <base href="<%=basePath%>">
    
    <title>书籍列表</title>
    
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
  <form name="form1" action="" method="post" enctype="multipart/form-data">
		<li style="list-style:none;margin:0px;"><input type="text" id="bookname" cssClass="s_text" cssStyle="width:160px;"/>
		<input type="button" class="s_button" value="搜 索" onclick="doSearch()"/></li>
        <table width="100%" border="0">
                   <tr class="t_tit">
                       <td width="30" align="center"><input type="checkbox" id="selAll" onclick="doSelectAll()" /></td>
                       <td width="200" align="center">书籍名称</td>
                       <td width="140" align="center">作者</td>
                       <td width="160" align="center">类型</td>
                       <td width="80" align="center">价格</td>
                       <td width="120" align="center">总数量</td>
                       <td align="center">目前可用数量</td>
                   </tr>
                   <c:forEach items="${requestScope.bookList}" var="book">
                       <tr bgcolor="f8f8f8">
                           <td align="center"><input type="checkbox" name="selectedRow" value="${book.bookId }"/></td>
                           <td align="center">${book.bookName}</td>
                           <td align="center">${book.author}</td>
                           <td align="center">${btMap[book.booktype].booktype}</td>
                           <td align="center">${book.price}</td>
                           <td align="center">${book.totalNumber}</td>
                           <td align="center">${book.nowNumber}</td>
                       </tr>
                   </c:forEach>
               </table>
                 	    	<a href="javascript:history.go(-1)">《《返回</a>
               
         </form>
  </body>
</html>
