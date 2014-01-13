<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/commonjsp/head.jsp"%>
<link href="${util.path}/css/shop/shop.css" rel="stylesheet" />
<title>首页</title>
</head>
<body style="padding-top: 60px;">
	<%@ include file="/commonjsp/topbar.jsp"%>
	<div class="container">
		<div class="index_home">
			<div class="left_div">
				<img class="gate_img" src="${util.path}${util.repository}${shop.gate_url}" />
			</div>
			<div class="right_div">
				<dl>
					<dt>
						<h1>电话：${shop.user.phone}</h1>
					</dt>
					<dd>
						<table class="gate_right">
							<tr>
								<td class="gate_right_td_left">联系人：</td>
								<td>${shop.contact}</td>
							</tr>
							<tr>
								<td class="gate_right_td_left">名称：</td>
								<td>${shop.name}</td>
							</tr>
							<tr>
								<td class="gate_right_td_left" style="padding-top: 10px;">地址：</td>
								<td style="padding-top: 10px;">${provinceTownCity}${shop.address}</td>
							</tr>
						</table>
					</dd>
					<dd style="text-align: right;">
						<a class="btn primary large">查看地图</a>
					</dd>
				</dl>
			</div>
		</div>

		<!-- Example row of columns -->
		<div class="row">
			<div class="span-one-third">
				<h4>1.吉祥餐盘</h4>
				<p>
					<a href="canteen/viewCanteen.html"><img class="product"
						src="../img/shaoxing/19.jpg" />
					</a>
				</p>
			</div>
			<div class="span-one-third">
				<h4>2.富贵餐盘</h4>
				<p>
					<img class="product" src="../img/shaoxing/19.jpg" />
				</p>
			</div>
			<div class="span-one-third">
				<h4>3.幸福水壶</h4>
				<p>
					<img class="product" src="../img/shaoxing/6.jpg" />
				</p>
			</div>
		</div>

		<footer>
		<p>&copy; 绍兴五金厂 2013</p>
		</footer>
	</div>
</body>
</html>