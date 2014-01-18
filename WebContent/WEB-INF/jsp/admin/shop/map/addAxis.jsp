<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加点标注工具--简单示例</title>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/MarkerTool/1.2/src/MarkerTool_min.js"></script>
</head>
<body>
	<div style="width: 520px; height: 340px; border: 1px solid gray"
		id="container"></div>
	<input type="button" value="打开" onclick="mkrTool.open()" />
	<input type="button" value="关闭" onclick="mkrTool.close()" />
</body>
</html>
<script type="text/javascript">
	var map = new BMap.Map("container");
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 12);
	map.enableScrollWheelZoom();

	var mkrTool = new BMapLib.MarkerTool(map, {
		autoClose : true,
		followText : "标注所在位置"
	});

	mkrTool.addEventListener("markend", function(e) {
		 alert(e.marker.point.lng);
		 alert(e.marker.point.lat);    
	});
</script>