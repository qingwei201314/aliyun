package im.gsj.dao;

import im.gsj.entity.Image;

import java.util.List;

import org.hibernate.Query;
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
	
	public List<Image> pageByShopAndCategory(String categoryId, int pageNo){
		String hql =  "select i.* " + 
						"from image i, product p " + 
						"where i.product_id = p.id "+
							"and p.category_id = :categoryId;";
		Query query = getSession().createQuery(hql);
		query.setParameter("categoryId", categoryId);
		return null;
	}
}
