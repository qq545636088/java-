<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>im即时聊天系统</title>
<style type="text/css">
div {
	border: 1px solid gray;
}

#left {
	width: 20%;
	height: 800px;
	float: left;
}

#top {
	width: 80%;
	height: 600px;
	margin-left: 20%;
}

#buttom {
	width: 80%;
	height: 200px;
	margin-left: 20%;
	position: relative;
}

button {
	position: absolute;
	right: 0;
	bottom: 0;
	padding: 11px;
	margin-right: 5px;
	margin-bottom: 3px;
	width: 100px;
}
</style>
<script type="text/javascript" src="jquery-1.9.1.min.js"></script>
</head>
<body>
	<div>
		<div id="left">
			<span>用户列表</span>
			<div style="overflow: auto; height: 750px">
				<c:forEach items="${userList }" var="user">
					<a href="javascript:chatwith('${user.username }','${user.openfireId }')">${user.username }</a>
					<br />
				</c:forEach>
			</div>
			<span>xx聊天软件</span>
		</div>
		<div id="top">
			<center>
				<span id="chatWith"></span>
			</center>
			<div style="overflow: auto; height: 96%;"></div>
		</div>
		<div id="buttom">
			<textarea rows="10" cols="138" id="message"></textarea>
			<input type="hidden" value="" id="openfireId">
			<button onclick="chatMessage()">发送</button>
		</div>
	</div>
</body>
<script type="text/javascript">
	function chatwith(username,openfireId) {
		$("#chatWith").text("和" + username + "正在聊天");
		$("#openfireId").val(openfireId);
	}
	function chatMessage() {
		var message = $("#message").val();
		var openfireId = $("#openfireId").val();
		$.ajax({
			url : "/activemq/chatServlet",
			data:{
				message:message,
				openfireId:openfireId
			},
			success : function(data) {
				console.info(data);
			}
		});
	}
</script>
</html>
