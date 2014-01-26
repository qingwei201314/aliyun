package im.gsj.dao;

import im.gsj.dao.dto.CategoryDto;
import im.gsj.entity.Category;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

@Service
public class CategoryDao extends CommonDao<Category>{
	public List<Category> getByShop(String shop_id){
		List<Category> categoryList = queryList("shop_id", shop_id);
		return categoryList;
	}
	
	@SuppressWarnings("unchecked")
	public List<CategoryDto> getCategoryByShop(String shop_id){
		String hql = "SELECT c.id as id, c.name as name, c.shop_id as shop_id, p.id as productId from category c left join product p on p.category_id = c.id " + 
						"where c.shop_id = :shop_id group by c.id";
		SQLQuery sQLQuery = getSession().createSQLQuery(hql);
		sQLQuery.setParameter("shop_id", shop_id);
		sQLQuery.setResultTransformer(Transformers.aliasToBean(CategoryDto.class));
		return sQLQuery.list();
	}
			
	/**
	 * 查出除指定类别外的某一商店的所有类别
	 */
	@SuppressWarnings("unchecked")
	public List<CategoryDto> getByShopExceptCurrent(String shop_id, String category_id){
		String hql = "SELECT c.id as id, c.name as name, c.shop_id as shop_id, p.id as productId from category c left join product p on p.category_id = c.id " + 
						"where c.id != :id and c.shop_id = :shop_id";
		SQLQuery sQLQuery = getSession().createSQLQuery(hql);
		sQLQuery.setParameter("id", category_id);
		sQLQuery.setParameter("shop_id", shop_id);
		sQLQuery.setResultTransformer(Transformers.aliasToBean(CategoryDto.class));
		return sQLQuery.list();
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
