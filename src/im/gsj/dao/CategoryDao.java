package im.gsj.dao;

import im.gsj.entity.Category;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CategoryDao extends CommonDao<Category>{
	public List<Category> getByShop(String shop_id){
		List<Category> categoryList = queryList("shop_id", shop_id);
		return categoryList;
	}
}
