<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="image" items="${productVo.imageList }">
	<dd>
		<img onclick="showImage(this.src, '${productVo.name}')"
			class="product_detail"
			src="${util.path }${util.repository }${image.path }${image.postfix}">
	</dd>
</c:forEach>

<c:if test="${productVo.imageList==null || productVo.imageList.size() <=0}">
	<p id='theLast' style="text-align: center;">已经是最后一页</p>
</c:if>