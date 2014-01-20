package im.gsj.dao;

import im.gsj.entity.Product;
import im.gsj.util.Page;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
public class ProductDao extends CommonDao<Product>{
	/**
	 * 取得某一商店最近的产品
	 */
	public Page<Product> getNewProduct(String shop_id, int pageNo){
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("shop_id", shop_id));
		criteria.addOrder(Order.desc("create_time"));
		Page<Product> page = getPage(criteria, pageNo);
		return page;
	}
	
	/**
	 * 取得某一商店某一类别的产品
	 */
	@SuppressWarnings("unchecked")
	public List<Product> listProduct(String categoryId){
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("category_id", categoryId));
		criteria.addOrder(Order.desc("create_time"));
		List<Product> productList = criteria.list();
		return productList;
	}
	
	/**
	 * 根据关键字查询某一商店的产品
	 */
	public Page<Product> searchProduct(String shopId, String q, int pageNo){
		q = "%" + q + "%";
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("shop_id", shopId));
		criteria.add(Restrictions.or(Restrictions.like("name", q), Restrictions.like("description", q)));
		Page<Product> page= getPage(criteria, pageNo);
		return page;
	}
}
