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
				<p><a href="${util.path }/product/viewProduct.do?productId=${productVo.id}&pageNo=1"><img class="product" src="${util.statics}${util.repository}${productVo.path}${productVo.postfix}" onError="this.src='${util.path}/img/product/none.jpg'" /></a></p>
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
			    	<img onclick="showImage(this.src, '${productVo.name}')" class="product_detail" src="${util.statics }${util.repository }${imageVo.path }${imageVo.postfix }" />
		    	</dd>
	    	</c:forEach>
    	</div>
      
    <!-- 加载更多信息 -->
    <c:if test="${totalPage > 1 }">
	  <div id="loader"  class="alert-message block-message success" style="margin-top: 28px;width: 400px;margin-left: auto;margin-right: auto;">
      	<p style="text-align: center;"><img src="${util.path}/img/loader.gif" style="margin-right: 10px;vertical-align: top;">数据加载中</p>
      </div>
    </c:if>
	  <%@ include file="/commonjsp/footer.jsp"%>
	</div>
	
<script type="text/javascript">
	$("#${categoryId}").attr("class", "active");

	//点击图片时，弹出图片查看窗口
	function showImage(imageSrc,productName){
		$("body").addClass("backgroud_gray");
		var viewImagePath = "${util.path}/product/viewImage.do?imageSrc=" + imageSrc + "&productName="+productName;
		window.showModalDialog(viewImagePath,"", "dialogWidth:820px;dialogHeight:620px;status:no;");
		$("body").removeClass("backgroud_gray");
	}
	
	//加载更多页面
	<c:if test="${totalPage > 1 }">
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
    					date: new Date(),
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
    </c:if>
</script>
</body>
</html>