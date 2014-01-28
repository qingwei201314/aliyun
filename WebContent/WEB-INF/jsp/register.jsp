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

.photoFrame {
	background-image: url(${util.path}/img/register/register.jpg);
	height: 300px;
	width: 700px;
	padding-top: 252px;
	margin: 20px auto;
	background-repeat: no-repeat;
}

.photoFrame .div1 {
	color: green;
	font-weight: bold;
	margin-bottom: 10px;
	font-size: 16pt;
	text-align: right;
	padding-right: 160px;
}

.photoFrame .div2 {
	color: green;
	font-weight: bold;
	font-size: 16pt;
	text-align: right;
	padding-right: 150px;
	margin-bottom: 40px;
}
</style>
</head>
<body>
	<div class="register">
		<div class="photoFrame">
			<div class="div1">
				开网站只需：<span style="color: red;">¥200/年</span>
			</div>
			<div class="div2">
				现在就<a href="#" style="color: green">购买</a>吧！
			</div>
			<div style="padding-left: 10px;"><a href="${util.path }" style="font-size: 10pt; color: green;">返回首页</a></div>
		</div>
	</div>
</body>
</html>