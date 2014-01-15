<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach var="imageVo" items="${imageDtoList}">
	<dd id="post">
		<h4>新${imageVo.name}</h4>
		<img class="product_detail"
			src="${util.path }${util.repository }${imageVo.path }${imageVo.postfix }">
	</dd>
</c:forEach>