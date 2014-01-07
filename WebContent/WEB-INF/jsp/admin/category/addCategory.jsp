<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/commonjsp/head.jsp"%>
<title>用户登录</title>
</head>
<body style="padding-top: 60px;">
	<%@ include file="/commonjsp/admin/topbar.jsp"%>
	<div class="container">
		<form action="${util.path}/category/saveCategory.do" method="post">
			<fieldset>
				<div style="width: 500px;margin:0 auto;">
					<table class="bordered-table zebra-striped" id="sortTableExample">
						<thead>
							<tr>
								<th class="blue">名称</th>
								<th class="blue">操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="category" items="${categoryList}" >
							<tr>
								<td>${category.name}</td>
								<td>
									<a href="${util.path}/category/deleteCategory.do?deleteCategoryId=${category.id}">删除</a>
									<a href="${util.path}/product/toProduct.do?categoryId=${category.id}">添加产品</a>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="clearfix">
					<label for="xlInput" class="label_width">类别名称</label>
					<div class="div_margin">
						<input type="text" name="name" size="30" class="xlarge" />
					</div>
					<div class="actions" style="padding-left: 360px;">
						<input type="submit" class="btn primary" value="保存"/>
					</div>
				</div>
			</fieldset>
		</form>
	</div>	
	<script type="text/javascript">
		$("#category").attr("class","active");
	</script>
</body>
</html>