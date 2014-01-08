<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="topbar">
	<div class="fill">
		<div class="container">
			<ul class="nav">
				<li id="shop"><a href="${util.path}/shop/addShop.do">商店信息</a></li>
				<li class="arrow_img"></li>
				<li id="category"><a href="${util.path}/category/addCategory.do">产品类别</a></li>
				<li class="arrow_img"></li>
				<li id="product"><a href="${util.path}/product/addProduct.do?categoryId=">添加产品</a></li>
				<li class="arrow_img"></li>
				<li id="image"><a href="${util.path}/admin/image/addImage.jsp">添加图片</a></li>
			</ul>
		</div>
	</div>
</div>