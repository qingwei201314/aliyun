<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/commonjsp/head.jsp"%>
<link href="${util.path}/css/shop/shop.css" rel="stylesheet" />
<title>用户登录</title>
</head>
<body style="padding-top: 60px;">
	<%@ include file="/commonjsp/admin/topbar.jsp"%>
	<div class="container">
		<form id="productListForm" action="">
			<fieldset>
				<div class="clearfix">
					<label for="xlInput" class="label_width">类别</label>
					<div class="div_margin">
						<select id="category" class="xlarge">
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.id}">${category.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div style="width: 500px;margin:0 auto;">
					<table class="bordered-table zebra-striped" id="sortTableExample">
						<thead>
							<tr>
								<th class="blue">产品名称</th>
								<th class="blue">操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="product" items="${productList}">
							<tr>
								<td>${product.name}</td>
								<td>
									<a href="${util.path}/product/editProduct.do?productId=${product.id}">编辑</a>
									<a href="${util.path}/image/addImage.jsf?deleteProductId=${product.id}">删除</a>
									<a href="${util.path}/product/addProductImage.do?productId=${product.id}">查看</a>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</fieldset>
			<div class="actions" style="padding-left: 360px;">
			</div>
		</form>
	</div>
	<script type="text/javascript">
		$("#image").attr("class","active");
	</script>
</body>
</html>