<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
  <script type="text/javascript">
  /* 	function doQueryType(){
  		var billtype = document.getElementById("billtype").value;
  		alert(billtype);
  		var currentPage = ${requestScope.BillPB.currentPage};
 			$.ajax({
 				url:"${basePath}QueryBill.book",
 				data:{"billtype":billtype,"currentPage":currentPage}, 
 				type:"post",
 				async:false,	
 				success:function(msg){}
 			});
    } */
    function doQueryType(){
		var billtype = document.getElementById("billtype").value;
    	document.forms[0].action="${basePath}QueryBill.book?billType="+billtype;
    	document.forms[0].submit();
    }
  </script>
    <base href="<%=basePath%>">
    
    <title>账单详情</title>
    
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
  <br>
  	查询账单类型 : <select size="1" id="billtype" name="billtype" style="width: 91; height: 18" onchange="javascript:doQueryType()">
					<c:forEach var="type" items="${requestScope.billType}">
				 		<option <c:if test="${requestScope.defaultType==type.key }">selected="selected"</c:if>  value="${type.key}"><c:out value="${type.value }"/></option>
					</c:forEach>
				</select>
  	<form action="" method="post">
        <table width="100%" border="0">
  			<tr>
                <td width="30" align="center"><input type="checkbox" id="selAll" onclick="doSelectAll()" /></td>
                <td width="300" align="center">账单类型</td>
                <td width="240" align="center">用户</td>
                <td width="260" align="center">时间</td>
                <td width="180" align="center">涉及金额</td>
            </tr>
            <c:forEach items="${requestScope.BillPB.pageData}" var="bill">
	            <tr bgcolor="f8f8f8">
	                <td align="center"><input type="checkbox" name="selectedRow" value="${bill.billid }"/></td>
	                <td align="center">${requestScope.billType[bill.billtype]}</td>
	                <td align="center">${bill.username}</td>
	                <td align="center">${bill.billdate}</td>
	                <td align="center">${bill.money}</td>
	            </tr>
            </c:forEach>
  		</table>
  	</form>
  	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					共有<c:out value="${requestScope.BillPB.totalInfo }" /> 条数据,当前是第 <c:out value="${ requestScope.BillPB.currentPage}" />页	
					<c:if test="${requestScope.BillPB.currentPage>1 }">
						<a href="javascript:doPageGO(${requestScope.BillPB.currentPage-1 })">上一页</a>
					</c:if>
					<c:if test="${requestScope.BillPB.currentPage <requestScope.billid.totalPage}">
						<a href="javascript:doPageGO(${requestScope.BillPB.currentPage+1 })">下一页</a>
					</c:if>
				</td>
			</tr>
         </table>
         <script type="text/javascript">
         	function doPageGO(pageNo){
         		document.forms[0].action="${basePath}QueryBill.book?currentPage="+pageNo;
         		document.forms[0].submit();
         	}
         </script>
  </body>
</html>
