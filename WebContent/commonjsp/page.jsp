<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${page.totalPage >=2 }">
	<div class="pagination" style="padding-left: 296px;">
		<ul>
			<li class="prev <c:if test="${page.pageNo==1}">disabled</c:if>"><a
				href="gotoPage('${pageForm}',1)">&larr; 上一页</a></li>
			<c:forEach var="displayPageNo" items="${page.displayPage }">
				<li <c:if test="${page.pageNo==displayPageNo}">class="active"</c:if>>
					<a href="gotoPage('${pageForm}',${displayPageNo})">${displayPageNo}</a>
				</li>
			</c:forEach>
			<li class="next <c:if test="${page.pageNo==page.totalPage}">disabled</c:if>">
				<a href="gotoPage('${pageForm}',${page.totalPage})">下一页 &rarr;</a>
			</li>
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
			
<script type="text/javascript">
	//跳转到第n页面
	function gotoPage(pageForm, pageNo){
		var pageForm = "#" + pageForm;
		var pageNoHidden = "<input type='hidden' name='pageNo' value='" + pageNo + "' />" ;
		$(pageForm).append(pageNoHidden);
		$(pageForm).submit();
	}
</script>