<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/commonjsp/head.jsp"%>
<link href="${util.path}/uploadify/uploadify.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${util.path}/uploadify/jquery.uploadify.min.js" ></script>
<title>用户登录</title>
</head>
<body>
<body style="padding-top: 60px;">
	<%@ include file="/commonjsp/admin/topbar.jsp"%>
	<div class="container">
		<form id="myform" action="${util.path}/uploadify/upload.do" method="post" enctype="multipart/form-data">
			<fieldset>
				<div class="clearfix">
					<label for="xlInput" class="label_width">名称</label>
					<div class="div_margin">
						<input type="text" id="name" name="name" size="30" maxlength="32"
							class="xlarge" />
					</div>
				</div>
				<div class="clearfix">
					<label for="xlInput" class="label_width">简称</label>
					<div class="div_margin">
						<input type="text" id="shortName" name="shortName" size="30"
							maxlength="16" class="xlarge" />
					</div>
				</div>
				<div class="clearfix">
					<label for="xlInput" class="label_width">联系人</label>
					<div class="div_margin ">
						<input type="text" id="contact" name="contact" size="30"
							maxlength="12" class="xlarge" />
					</div>
				</div>
				<div class="clearfix">
					<label for="xlInput" class="label_width">地址</label>
					<div class="div_margin ">
						<select id="province" onchange="getCity(this.value, '#town')" style="width: 150px;">
							<c:forEach var="province" items="${provinces}">
								<option value="${province.id}">${province.name}</option>
							</c:forEach>
						</select>
						<select id="town" onchange="getCity(this.value, '#city')" style="width: 150px;"></select>
						<select id="city" style="width: 150px;"></select>
					</div>
					<div class="div_margin ">
						<input type="text" id="address" name="address" size="30"
							maxlength="64" class="xlarge"
							style="width: 440px; margin-top: 5px;" />
					</div>
				</div>
				<div class="clearfix">
					<label for="xlInput" class="label_width">大门图片</label>
					<div class="div_margin ">
						<img id="gatePhone"
							src="${util.path}${util.repository}${url}"
							style="width: 580px; height: 290px; display: none;" />
						<input type="file" name="file_upload" id="file_upload" />
					</div>
				</div>
				<div class="actions" style="padding-left: 360px;">
					<input type="submit" value="保存" class="btn primary" />
				</div>
			</fieldset>
		</form>
		<%@ include file="/commonjsp/footer.jsp"%>
	</div>
	<script type="text/javascript">
		$("#shop").attr("class", "active");
		//验证信息
		new LiveValidation('name').add(Validate.Presence);
		new LiveValidation('shortName').add(Validate.Presence);
		new LiveValidation('contact').add(Validate.Presence);
		new LiveValidation('address').add(Validate.Presence);

		//根据区划id查找子类，并加载到子下拉框中
		function getCity(cityId, renderItem){
			$.ajax({
				  url: "${util.path}/city/getCityList.do",
				  data: {
			      	cityId: cityId
				  },
				  success: function(html) {
				    $(renderItem).html(html);
				  }
			});
		}

		//上传大门图片
		$(function() {
			  var phone = '${phone}';
			  var jspPaht = '${util.path}/uploadify/upload.do?phone=${phone}&widthXheight=580x290';
		      $('#file_upload').uploadify({
		         'swf'      : '${util.path}/uploadify/uploadify.swf',
		         'uploader' : jspPaht,
		         'buttonText' : '上传图片', 
		         'width'    :  '90px',
		         'height'   :  '28px',
		         'fileTypeExts' : '*.gif; *.jpg; *.png',
		         'onUploadSuccess' : function(file, data, response) {
					data = data.substring(0, data.length-1);
		         	var path = "${util.path}${util.repository}" + data;
		        	$("#gatePhone").show();
		        	$("#gatePhone").attr("src", path); 
		        	//$("#span_gate_url").children().val(data);
		          },
		         'buttonClass' : 'btn info'
		      });
		});
	    
		function beforeSubmit() {
			if ($("#myform\\:city").val() == null
					|| $("#myform\\:city").val() == -1) {
				alert("请选择地址.");
				return false;
			}
			if ($("#myform\\:gate_url").val() == 'none') {
				alert("请上传大门图片.");
				return false;
			}
		}
	</script>
</body>
</html>
