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
		function doDelete(id)
		{
			document.forms[0].action = "${basePath}userdelete.book?userid="+id;
			document.forms[0].submit();
		}
		function doEdit(id)
		{
			document.forms[0].action = "${basePath}usereditUI.book?userid="+id;
			document.forms[0].submit();
		}
		function doSearch(){
			var search = document.getElementById("searchName").value;
			document.forms[0].action="${basePath}user.book?search="+search;
			document.forms[0].submit();
		}
		function doMoney(id){
			document.forms[0].action = "${basePath}userAddMoneyUI.book?userid="+id;
			document.forms[0].submit();
		}
  </script>
    <base href="<%=basePath%>">
    
    <title>用户列表</title>
    
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
  
  <form name="form1" action="" method="post" enctype="multipart/form-data">
		<li style="list-style:none;margin:0px;">用户名:<input type="text" id="searchName" cssClass="s_text" cssStyle="width:160px;"/>
		<input type="button" class="s_button" value="搜 索" onclick="doSearch()"/></li>
         <li style="float:right;">
             <input type="button" value="新增" class="s_button" onclick="doAdd()"/>&nbsp;
             <input type="button" value="删除" class="s_button" onclick="doDeleteAll()"/>&nbsp;
             <input type="button" value="导出" class="s_button" onclick="doExportPDF()"/>&nbsp;
	     </li>
        <table width="100%" border="0">
                   <tr class="t_tit">
                       <td width="30" align="center"><input type="checkbox" id="selAll" onclick="doSelectAll()" /></td>
                       <td width="200" align="center">用户名称</td>
                       <td width="160" align="center">类型</td>
                       <td width="140" align="center">电话</td>
                       <td width="80" align="center">email</td>
                       <td align="center">idcard</td>
                       <td align="center">目前可借书数目</td>
                       <td width="200" align="center">操作</td>
                   </tr>
                   <c:forEach items="${requestScope.pageBean.pageData}" var="user">
                       <tr bgcolor="f8f8f8">
                           <td align="center"><input type="checkbox" name="selectedRow" value="${user.u_id }"/></td>
                           <td align="center">${user.u_name}</td>
                           <td align="center">${userType[user.u_type]}</td><!-- btMap[book.booktype].booktype -->
                           <td align="center">${user.phone}</td>
                           <td align="center">${user.email}</td>
                           <td align="center">${user.idcard}</td>
                           <td align="center">${user.borrow}</td>
                           <td align="center">
                               <a href="javascript:doMoney(${user.u_id })">充值</a>
                               <a href="javascript:doEdit(${user.u_id })">编辑</a>
                               <a href="javascript:doDelete(${user.u_id })">删除</a>
                           </td>
                       </tr>
                   </c:forEach>
               </table>
         </form>
         <table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					共有<c:out value="${requestScope.pageBean.totalInfo }" /> 条数据,当前是第 <c:out value="${ requestScope.pageBean.currentPage}" />页	
					<c:if test="${requestScope.pageBean.currentPage>1 }">
						<a href="javascript:doPageGO(${requestScope.pageBean.currentPage-1 })">上一页</a>
					</c:if>
					<c:if test="${requestScope.pageBean.currentPage <requestScope.pageBean.totalPage}">
						<a href="javascript:doPageGO(${requestScope.pageBean.currentPage+1 })">下一页</a>
					</c:if>
				</td>
			</tr>
         </table>
         <script type="text/javascript">
         	function doPageGO(pageNo){
         		document.forms[0].action="${basePath}user.book?currentPage="+pageNo;
         		document.forms[0].submit();
         	}
         </script>
  </body>
</html>
