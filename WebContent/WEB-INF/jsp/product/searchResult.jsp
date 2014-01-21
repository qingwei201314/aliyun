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

			<c:if test="${page.totalPage()>0 }">
				<div class="pagination" style="padding-left: 296px;">
					<ul>
						<li class="prev disabled">
						<a href="#">&larr; 上一页</a></li>
						<li class="active"><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li class="next"><a href="#">下一页 &rarr;</a></li>
					</ul>
				</div>
			</c:if>
			
			<c:if test="${page.list==null||page.list.size()<=0 }">
			    <div class="span4">
			      <h2>Popovers</h2>
			      <p>Use popovers to provide subtextual information to a page without affecting layout.</p>
			      <p><a class="btn js-btn" href="./javascript.html#popover">Get the javascript &raquo;</a></p>
			    </div>
			    <div class="span12">
			      <div class="well popover-well">
			         <div class="popover-wrapper">
			          <div class="popover left">
			            <div class="arrow"></div>
			            <div class="inner">
			              <h3 class="title">Popover Title</h3>
			              <div class="content">
			                <p>Etiam porta sem malesuada magna mollis euismod. Maecenas faucibus mollis interdum. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.</p>
			              </div>
			            </div>
			          </div>
			          <img class="large-bird" src="assets/img/bird.png" >
			        </div>
			      </div>
			    </div>
			</c:if>
		<%@ include file="/commonjsp/footer.jsp"%>
	</div>
</body>
</html>