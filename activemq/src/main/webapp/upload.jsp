<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图片压缩demo</title>

<script type="text/javascript" src="jquery-1.9.1.min.js"></script>
</head>
<body>
	<form action="compressServlet" method="post" enctype="multipart/form-data" onsubmit="return test()">
		<input type="file" name="file"  id="file" accept="image/*"/><br/>
		请输入压缩图片后的宽度：<input type="text" name="width" id="width"><br/>
		请输入压缩图片后的高度：<input type="text" name="height" id="height"><br/>
		<span>注：如不输入则默认生成100*100的压缩图片</span><br>
		<input type="submit" />
	</form>
</body>
<script type="text/javascript">
	function test(){
		var file = $("#file").val();
		if(file==null || file=="undefined" || file==""){
			alert("请选择图片！");
			return false;
		}
		var width = $("#width").val();
		var height =$("#height").val();
		 var reg = new RegExp("^[0-9]*$");
		if(reg.test(width) && reg.test(height)){
			return true;
		}else{
			alert("宽度或高度只能为数字！");
			return false;
		}
		
	}
</script>
</html>
