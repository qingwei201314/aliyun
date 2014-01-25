<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
   String pageForm = request.getParameter("pageForm");
%>

<c:if test="${page.totalPage >=2 }">
	<div class="pagination" style="padding-left: 296px;">
		<ul>
			<li class="prev <c:if test="${page.pageNo==1}">disabled</c:if>">
				<a href="javascript:void(0)" <c:if test="${page.pageNo>1}">onclick="gotoPage('<%=pageForm%>',1)"</c:if>>&larr; 上一页</a></li>
			<c:forEach var="displayPageNo" items="${page.displayPage }">
				<li <c:if test="${page.pageNo==displayPageNo}">class="active"</c:if>>
					<a href="javascript:void(0)" onclick="gotoPage('<%=pageForm%>',${displayPageNo})">${displayPageNo}</a>
				</li>
			</c:forEach>
			<li class="next <c:if test="${page.pageNo==page.totalPage}">disabled</c:if>">
				<a href="javascript:void(0)" <c:if test="${page.pageNo < page.totalPage}">onclick="gotoPage('<%=pageForm%>',${page.totalPage})"</c:if>>下一页 &rarr;</a>
			</li>
		</ul>
	</div>
</c:if>

<c:if test="${page.list==null||page.list.size()<=0 }">
<div class="popover left" style="display: block;position: static;" >
	<div class="inner" style="margin: 0 auto;">
		<h3 class="title">没有相关产品。</h3>
		<div class="content">
			<p>对不起，没有符合条件的相关产品。</p>
		</div>
	</div>
</div>
</c:if>
			
<script type="text/javascript">
	//跳转到第n页面
	function gotoPage(pageForm, pageNo){
		var pageForm = "#" + pageForm;
		var pageNoHidden = "<input type='hidden' id='pageNo' name='pageNo' value='" + pageNo + "' />" ;
		$("#pageNo").remove();
		$(pageForm).append(pageNoHidden);
		$(pageForm).submit();
	}
</script>