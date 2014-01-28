<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link href="${util.path}/css/main.css" rel="stylesheet" type="text/css" />
	<title>过山鲫</title>
</head>
<body>
<div style="width: 100%;text-align: right;font-size: 10pt;">
	<a href="${util.path }/user/login.do">登录</a>
	<a href="${util.path }/index/register.do">注册</a>
</div>
<form id="searchForm" action="${util.path }/index/search.do" method="get">
	<input type="hidden" name="pageNo" value="1" />
	<div class="logo_div">
		<a href="#"><img src="${util.path}/img/logo.png" style="border:0;" /></a>
	</div>
	<div>
		<div class="search_div" >
			<input type="text" name="q" class="search_div_input" />
			<input type="button" onclick="searchSubmit();" class="search_div_button" />
		</div>
	</div>
	<div style="text-align: center;margin-top: 100px;font-size: 8pt;color: #777777">
		中小企业网站专家：电话号码就是您的企业网站！
	</div>
</form>
<script type="text/javascript">
	function searchSubmit(){
		document.getElementById("searchForm").submit();
	}
</script>
</body>
</html>