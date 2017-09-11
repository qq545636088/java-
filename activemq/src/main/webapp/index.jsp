<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>im即时聊天系统</title>
<style type="text/css">
body {
	font-size: 16px;
	margin: 0px;
	padding: 0px;
}


#submit {
	margin-top: 20px;
	padding: 10px;
	margin-left: 20px;
}

#regedit {
	margin-top: 20px;
	padding: 10px;
	margin-left: 100px;
}

#password {
	margin-top: 20px;
}
</style>
</head>
<body>

	<center style="margin-top: 300px">
		<h1>IM 即时聊天web系统</h1>
		<form action="loginServlet" method="post" class="form-style">
			<div>
				用户名：<input type="text" id="username" name ="username">
			</div>
			<div>
				密&emsp;码：<input type="text" id="password" name="password">
			</div>
			<div style="color: red">
				${error }
			</div>
			<div>
				<input type="submit" value="登录" id="submit"> <input
					type="button" value="注册" id="regedit">
			</div>
		</form>
	</center>
</body>
</html>
