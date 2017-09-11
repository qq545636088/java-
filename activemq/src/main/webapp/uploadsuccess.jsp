<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图片压缩成功</title>
<style type="text/css">

	span{
		margin-left: 20px;
	    width: 100px;
	    height: 10px;
	    display: inline-block;
	    word-break: break-all;
	}
</style>
<script type="text/javascript" src="jquery-1.9.1.min.js"></script>
</head>
<body>
	<h3>图片压缩成功!</h3>
	<div style="margin-top: 10px;">
		<span>原文件名</span> <span>新文件名</span> <span>原文件大小</span> <span>新文件大小</span>
	</div>
	<div>
		<span><a href="dowloadServlet?name=${filename}">${filename }</a></span>
		<span><a href="dowloadServlet?name=${newFileName}">${newFileName }</a> </span>
		<span>${fileSize }kb</span>
		<span>${newFileSize }kb</span>
	</div>
</body>
</script>
</html>
