<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/commonjsp/head.jsp"%>
<link href="${util.path}/css/shop/shop.css" rel="stylesheet" />
<title>产品搜索</title>
</head>
<body style="padding-top: 60px;">
	<%@ include file="/commonjsp/topbar.jsp"%>
	<div class="container">
		<div class="row" style="width: 960px;">
			<c:forEach var="productVo" items="${page.list }">
				<div class="span-one-third">
					<h4>${productVo.name}</h4>
					<p>
						<a href="${util.path }/product/viewProduct.do?productId=${productVo.id}&pageNo=1">
							<img class="product" src="${util.path}${util.repository}${productVo.path}${productVo.postfix}" />
						</a>
					</p>
				</div>
			</c:forEach>
		</div>

		<%@ include file="/commonjsp/footer.jsp"%>
	</div>
</body>
</html>