package im.gsj.image.service;

import im.gsj.dao.ImageDao;
import im.gsj.entity.Image;
import im.gsj.util.Constant;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
	@Resource
	private ImageDao imageDao;
	
	public String saveImage(String product_id, String url){
		url = StringUtils.substringBefore(url, ",");
		String path = StringUtils.substringBeforeLast(url, "_");
		String postfix= "." + StringUtils.substringAfter(url, ".");
		Image image = new Image(product_id,path);
		image.setPostfix("." + postfix);
		imageDao.save(image);
		String resut = path + Constant.S + postfix;
		return resut;
	}
}
