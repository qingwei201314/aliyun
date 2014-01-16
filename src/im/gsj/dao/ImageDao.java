package im.gsj.dao;

import im.gsj.dao.dto.ImageDto;
import im.gsj.entity.Image;
import im.gsj.util.Page;

import java.util.List;

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
							"and p.category_id = ?";
		Page<ImageDto> page =super.pageByHql(hql,"select count(i.id)", pageNo, ImageDto.class, categoryId);
		return page.getList();
	}
}
