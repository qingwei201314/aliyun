<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/commonjsp/head.jsp"%>
<link href="${util.path}/css/shop/shop.css" rel="stylesheet" />
<script type="text/javascript" src="${util.path}/js/jquery-ias.js"></script>
<title>产品列表</title>
</head>
<body style="padding-top: 60px;">
	<%@ include file="/commonjsp/topbar.jsp"%>
	
	<div class="container">
		<!-- Example row of columns -->
		<div class="row" style="width: 960px;">
			<c:forEach var="productVo" items="${productVoList}">
			<div class="span-one-third">
				<h4>${productVo.name}</h4>
				<p><a href="viewCanteen.html"><img class="product" src="${util.path}${util.repository}${productVo.path}${productVo.postfix}" /></a></p>
			</div>
			</c:forEach>
		</div>

	<!-- 信息提示栏 -->
	  <div class="alert-message block-message success" style="margin-top: 28px;">
        <p style="text-align: center;"><strong>以下为上述产品的详细图片</strong></p>
      </div>
      
    <!-- 显示详细图片 -->
    	<div class="img_detail">
    		<c:forEach var="imageVo" items="${imageDtoList}">
	    		<dd class="post">
		    		<h4>${imageVo.name}</h4>
			    	<img class="product_detail" src="${util.path }${util.repository }${imageVo.path }${imageVo.postfix }">
		    	</dd>
	    	</c:forEach>
    	</div>
      
    <!-- 加载更多信息 -->
	  <div class="alert-message block-message success" style="margin-top: 28px;width: 400px;margin-left: auto;margin-right: auto;">
      	<a href="${util.path}/category/moreImage.do?categoryId=${categoryId}&pageNo=2" ><p style="text-align: center;">数据加载中</p></a>
      </div>
      
    <div class="navigation">
        <ul>
            <li>1</li>
            <li class="next-posts"><a href="javascript:void(0)" onclick="test()">2</a></li>
        </ul>
    </div>
    
	<%@ include file="/commonjsp/footer.jsp"%>
	</div>
	
<script type="text/javascript">
	$("#${categoryId}").attr("class", "active");

	//加载更多页面
    $(document).ready(function() {
        jQuery.ias({
            container : '.img_detail',
            item: '.post',
            pagination: '.img_detail .navigation',
            next: '.navigation a',
            loader: '<img src="https://raw.github.com/webcreate/infinite-ajax-scroll/master/dist/images/loader.gif"/>',
            triggerPageThreshold: 2
        });
    });

    function test(){
    	var myPath = "${util.path}/category/moreImage.do?categoryId=${categoryId}&pageNo=2";
    	alert(myPath);
    }
</script>
</body>
</html>