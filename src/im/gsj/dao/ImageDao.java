package im.gsj.dao;

import im.gsj.dao.dto.ImageDto;
import im.gsj.entity.Image;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImageDao  extends CommonDao<Image>{
	/**
	 * 根据产品Id找出产品图片
	 */
	public List<Image> getImageListByProductId(String productId){
		List<Image> imageList = queryList("product_id", productId);
		return imageList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<ImageDto> pageByCategory(String categoryId, int pageNo){
		String hql =  "select i.id as id, i.product_id as product_id, i.path as path,i.postfix as postfix, p.name as name " + 
						"from im.gsj.entity.Image i, im.gsj.entity.Product p " + 
						"where i.product_id = p.id "+
							"and p.category_id = :categoryId";
		Query query =super.listByHql(hql, pageNo, 12);
		query.setParameter("categoryId", categoryId);
		query.setResultTransformer(Transformers.aliasToBean(ImageDto.class));
		List<ImageDto> imageList= query.list();
		return imageList;
	}
}
