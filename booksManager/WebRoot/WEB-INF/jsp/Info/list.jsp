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
		function doExportPDF(){
			window.open("${basePath}book_exportPDF.book");
		}
		function doBorrowBook(id,bid,uid,uname){
			document.forms[0].action = "${basePath}borrowBook.book?approveid="+id+"&bookid="+bid+"&userid="+uid+"&name="+uname;
			document.forms[0].submit();
		}
  </script>
    <base href="<%=basePath%>">
    
    <title>申请借书表</title>
    
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
    <h3><a href="${basePath }home.book" >返回首页</a></h3>
  <form name="form1" action="" method="post" >
		<li style="list-style:none;margin:0px;"><input type="text" id="bookname" cssClass="s_text" cssStyle="width:160px;"/>
		<input type="button" class="s_button" value="搜 索" onclick="doSearch()"/></li>
         <li style="float:right;">
	     </li>
        <table width="100%" border="0">
                   <tr class="t_tit">
                       <td width="30" align="center"><input type="checkbox" id="selAll" onclick="doSelectAll()" /></td>
                       <td width="300" align="center">书籍名称</td>
                       <td width="300" align="center">用户</td>
                       <td width="100" align="center">操作</td>
                   </tr>
                   <c:forEach items="${requestScope.approveList}" var="approve">
                       <tr bgcolor="f8f8f8">
                           <td align="center"><input type="checkbox" name="selectedRow" value="${approve.id }"/></td>
                           <td align="center"><a href="#" >${approve.book_name}</a></td>
                           <td align="center"><a href="#" >${approve.user_name}</a></td>
                           <td align="center">
                               <a href="javascript:doBorrowBook(${approve.id },${approve.book_id },${approve.user_id },'${approve.user_name }');">借书</a>
                           </td>
                       </tr>
                   </c:forEach>
                   
               </table>
         </form>
  </body>
</html>
