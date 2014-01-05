<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link href="${util.path}/css/main.css" rel="stylesheet" type="text/css" />
	<title>首页</title>
</head>
<body>
	<div class="logo_div">
		<a href="${util.path}/user/login.do"><img src="${util.path}/img/logo.png" style="border:0;" /></a>
	</div>
	<div>
		<div class="search_div">
			<input type="text" class="search_div_input" /> <input type="button"
				class="search_div_button"
				onclick="window.location='shop/shopIndex.html'" />
		</div>
	</div>
</body>
</html>