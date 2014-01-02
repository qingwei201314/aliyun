package im.gsj.dao;

import im.gsj.entity.Image;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ImageDao  extends CommonDao<Image>{
	/**
	 * 根据产品Id找出产品图片
	 */
	public List<Image> getImageListByProductId(String productId){
		List<Image> imageList = queryList("product_id", productId);
		return imageList;
	}
}
