<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/commonjsp/head.jsp"%>
<link href="../css/shop/shop.css" rel="stylesheet">
<title>关于我们</title>
</head>
<body style="padding-top: 60px;">
	<%@ include file="/commonjsp/topbar.jsp"%>
	
	<div class="container">
		<div class="hero-unit">
			<p style="margin-top: 10px;">
				${about.content}
			</p>
		</div>
		<%@ include file="/commonjsp/footer.jsp"%>
	</div>
	
<script type="text/javascript">
	$("#aboutUs").attr("class", "active");
</script>
</body>
</html>