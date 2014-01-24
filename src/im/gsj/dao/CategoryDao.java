package im.gsj.dao;

import im.gsj.entity.Category;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
public class CategoryDao extends CommonDao<Category>{
	public List<Category> getByShop(String shop_id){
		List<Category> categoryList = queryList("shop_id", shop_id);
		return categoryList;
	}
	
	/**
	 * 查出除指定类别外的某一商店的所有类别
	 */
	@SuppressWarnings("unchecked")
	public List<Category> getByShopExceptCurrent(String shop_id, String category_id){
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("shop_id", shop_id));
		criteria.add(Restrictions.not(Restrictions.eq("id", category_id)));
		List<Category> categoryList = criteria.list();
		return categoryList;
	}
	
	/**
	 * 查出指定商店的类别数
	 */
	public Long getCategoryCount(String shop_id){
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("shop_id", shop_id));
		Long count = getCount(criteria);
		return count;
	}
}
