<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script src="http://api.map.baidu.com/api?v=1.2&ak=Pv6hBzRYLwB9kjEGGIMXHr7B" type="text/javascript"></script>
<title>地图位置</title>
</head>
<body>
<%@ include file="/commonjsp/admin/topbar.jsp"%>

<div style="width: 100%; height: 450px; " id="container"></div>

	<script type="text/javascript">
		var map = new BMap.Map("container");
		map.addControl(new BMap.NavigationControl()); 
		map.enableScrollWheelZoom();
		
		var point = new BMap.Point(${map.longitude}, ${map.latitude});
		var marker = new BMap.Marker(point);        // 创建标注    
		map.addOverlay(marker);  
		map.centerAndZoom(point, 12);
	</script>
</body>
</html>