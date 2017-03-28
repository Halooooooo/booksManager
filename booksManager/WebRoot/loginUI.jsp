<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<title>登录</title>
	</head>
	<script>
	function doSign(){
		window.location.href="sign.jsp";
	}
	</script>
	<body>
		<form action="${basePath }loginServlet" method="post">
			<table>
				<tr>
					<td>选择登陆身份：</td>
					<td><select name="status" style="width: 91; height: 18">
						<option value="-1">请选择登陆身份</option>
						<option value="1">管理员</option>
						<option value="2">用户</option>
					</select></td>
				</tr>
				<tr><td>用户名</td><td><input type="text" name="user_name"/></td></tr>
				<tr><td>密码</td><td><input type="password" name="password"/></td></tr>
				<tr>
				<td><input type="submit"  value="Login In"/></td>
				
				<td><input type="button" value="注册" onclick="doSign();"/></td>
				</tr>
			</table>
		</form>
	</body>
</html>