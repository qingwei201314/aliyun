package im.gsj.dao;

import im.gsj.entity.Product;
import im.gsj.util.Page;

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
}
