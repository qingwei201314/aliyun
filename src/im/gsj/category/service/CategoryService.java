package im.gsj.category.service;

import im.gsj.dao.CategoryDao;
import im.gsj.dao.ShopDao;
import im.gsj.entity.Category;
import im.gsj.entity.Shop;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
	@Resource
	private CategoryDao categoryDao;
	@Resource
	private ShopDao shopDao;
	
	/**
	 * 取得当前店的所有分类
	 */
	@Transactional(readOnly=true)
	public List<Category> list(String phone) {
		Shop shop =shopDao.getByPhone(phone);
		List<Category> categoryList = categoryDao.getByShop(shop.getId());
		return categoryList;
	}
	
	public void save(Category category, String phone) {
		Shop shop =shopDao.getByPhone(phone);
		category.setShop_id(shop.getId());
		categoryDao.save(category);
	}
}
