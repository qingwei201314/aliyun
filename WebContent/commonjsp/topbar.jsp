<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="topbar">
	<div class="fill">
		<div class="container">
			<a class="brand" href="#">${shop.shortName}</a>
			<ul class="nav">
				<c:forEach var="category" items="${categoryList}">
					<li><a id="${category.id }" href="canteen/listCanteen.html">${category.name}</a></li>
				</c:forEach>
				<li><a href="aboutUs.html">关于我们</a></li>
			</ul>
			<div style="float: right; width: 232px; margin-top: 6px;">
				<input type="text" name="q" style="line-height: 19px;" /> <span
					style="position: absolute;"> <input type="submit" id="fdj"
					title="搜索" value="" />
				</span>
			</div>
		</div>
	</div>
</div>