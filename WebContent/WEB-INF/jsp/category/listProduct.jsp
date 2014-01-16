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
    	<div id="img_detail" class="img_detail">
    		<c:forEach var="imageVo" items="${imageDtoList}">
	    		<dd>
		    		<h4>${imageVo.name}</h4>
			    	<img class="product_detail" src="${util.path }${util.repository }${imageVo.path }${imageVo.postfix }">
		    	</dd>
	    	</c:forEach>
    	</div>
      
    <!-- 加载更多信息 -->
	  <div id="loader"  class="alert-message block-message success" style="margin-top: 28px;width: 400px;margin-left: auto;margin-right: auto;">
      	<p style="text-align: center;"><img src="${util.path}/img/loader.gif" style="margin-right: 10px;vertical-align: top;">数据加载中</p>
      </div>
    
	  <%@ include file="/commonjsp/footer.jsp"%>
	</div>
	
<script type="text/javascript">
	$("#${categoryId}").attr("class", "active");
	
	//加载更多页面
    $(document).ready(function() {
		var pageNo=2;   //加载更多页的页码
		var result="";  //返回的页面
    	$(window).scroll(function(){
    		var scrollTop = $(this).scrollTop();
			var windowHeight = $(this).height();
    		var scrollHeight = $(document).height();
    		if(result.indexOf("id='theLast'")<0 && scrollTop + windowHeight == scrollHeight){
    			$.ajax({
    				url: "${util.path}/category/moreImage.do",
    				data: {
    					categoryId: "${categoryId}",
    					pageNo: pageNo
    				},
    				success: function( data ) {
    					result = data;
    					if(result.indexOf("id='theLast'") <0){
            				$("#img_detail").append(data);
    					}
    					else{
							$("#loader").html(result);
						}
        				pageNo++;
    				}
    			});
    		}
    	});
    });

</script>
</body>
</html>