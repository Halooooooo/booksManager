<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <head>
    <base href="<%=basePath%>">
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<script>
	function dosubmit()
    	{
    		var name=$("#userName");
    		if(name.val()==""){
    			alert("用户名为空！");
    			name.focus();
    			return false;
    		}
    		var password=$("#password");
    		if(password.val()==""){
    			alert("密码为空！");
    			password.foucs();
    			return false;
    		}
    		var account=$("#idcard");
    		if(account.val()==""){
    			alert("身份证为空！");
    			account.foucs();
    			return false;
    		}
    		doVerify();
    		if(result){
    			document.forms[0].submit();
    		}
    		document.forms[0].submit();
    }
    function doVerify(){
		var userName = $("#userName").val();
		var idcard = $("#idcard").val();
    	if(userName!=""||idcard!=""){
    			$.ajax({
    				url:"${basePath}user_verify.sign",
    				data:{"userName":userName,"idcard":idcard}, 
    				type:"post",
    				async:false,	
    				success:function(msg){
    					if("true"!= msg)
    					{
    						alert("存在相同的账户或idcard");
    						$("#userName").focus();
    						result=false;
    					}else{
    						result=true;
    					}
    				}
    			});
    	}
    }
</script>
    <title>用户注册</title>
    
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
<form action="${basePath }user.sign" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>注册</strong>&nbsp;-&nbsp;注册</div></div>
    <div class="tableH2">注册</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">用户名称：</td>
            <td><input type="text" id="userName" name="userName" /></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">密码：</td>
            <td><input type="text" id="password" name="password" /></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">电话：</td>
            <td><input type="text" name="phone" /></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">email：</td>
            <td><input type="text" name="email" /></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">idcard：</td>
            <td><input type="text" id="idcard" name="idcard" /></td>
        </tr>
    </table>
    
    <div class="tc mt20">
        <input type="button" class="btnB2" value="保存" onclick="dosubmit();"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
  </body>
</html>
