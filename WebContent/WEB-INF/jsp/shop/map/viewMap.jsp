<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<%@ include file="/commonjsp/head.jsp"%>
<style type="text/css">
html {
	height: 100%
}

body {
	height: 100%;
	margin: 0px;
	padding: 0px
}

#container {
	height: 100%
}
</style>
<script
	src="http://api.map.baidu.com/api?v=1.5&ak=6Yv3muNeAAYjqGx2a9IYl6XC"
	type="text/javascript"></script>
<title>地图位置</title>
</head>
<body>
<div id="container"></div>

	<script type="text/javascript">
		var map = new BMap.Map("container");
		map.addControl(new BMap.NavigationControl());  
		var point = new BMap.Point(116.404, 39.915);
		var marker = new BMap.Marker(point);        // 创建标注    
		map.addOverlay(marker);  
		map.centerAndZoom(point, 15);

	</script>
</body>
</html>