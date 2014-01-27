<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户注册</title>
<style type="text/css">
.register {
	width: 100%;
	height: 90%;
	text-align: center;
}
.photoFrame{
	background-image: url(${util.path}/img/register/register.jpg);
	height: 300px;
	width: 700px;
	padding-top: 260px;
	margin-top: 20px;
	background-repeat: no-repeat;
}
</style>
</head>
<body >
<div class="register" >
	<div class="photoFrame" >
		<div style="color: green;font-weight: bold;margin-bottom: 20px;font-size: 16pt;">注册一个账号只需：<span style="color: red;">¥200/年</span></div>
		<div style="color: green;font-weight: bold;font-size: 16pt;">现在就到《凯文信息》淘宝店<a target="_blank" href="http://item.taobao.com/item.htm?id=37144133143" style="color: green">购买</a>吧！</div>
		<a href="${util.path }" style="font-size: 10pt;color: green;">返回首页</a>
	</div>
</div>
</body>
</html>