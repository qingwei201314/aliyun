<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="topbar">
	<div class="fill">
		<div class="container">
			<ul class="nav">
				<li id="shop"><a href="${util.path}/admin/shop/addShop.do">商店信息</a></li>
				<c:if test="${shop!=null }">
					<li class="arrow_img"></li>
					<li id="category"><a
						href="${util.path}/admin/category/addCategory.do">产品类别</a></li>
					<li class="arrow_img"></li>
					<li id="product"><a
						href="${util.path}/admin/product/addProduct.do?categoryId=">添加产品</a></li>
					<li class="arrow_img"></li>
					<li id="image"><a href="${util.path}/admin/image/addImage.do">添加图片</a></li>
					<li class="arrow_img"></li>
					<li id="about"><a href="${util.path}/admin/about/addAbout.do">关于我们</a></li>
					<li class="arrow_img"></li>
					<li id="map"><a href="${util.path}/admin/shop/map/addAxis.do">地图定位</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>