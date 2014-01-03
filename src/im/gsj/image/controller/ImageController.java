package im.gsj.image.controller;

import im.gsj.image.service.ImageService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageController {
	@Resource
	private ImageService imageService;
	private String product_id;
	private String url;
	
	public String addImage(String productId){
		return "/admin/image/addImage";
	}
	
	public String getSaveImage() throws IOException{
		HttpServletRequest request =null;
		HttpServletResponse response = null;
		product_id = request.getParameter("product_id");
		url = request.getParameter("url");
		String result = imageService.saveImage(product_id, url);
		PrintWriter pw = response.getWriter();
		pw.append("result." + result + ".result");
		return null;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
