package im.gsj.product.service;

import im.gsj.dao.CategoryDao;
import im.gsj.dao.ImageDao;
import im.gsj.dao.ProductDao;
import im.gsj.dao.ShopDao;
import im.gsj.entity.Category;
import im.gsj.entity.Image;
import im.gsj.entity.Product;
import im.gsj.entity.Shop;
import im.gsj.product.vo.ProductVo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Resource
	private ShopDao shopDao;
	@Resource
	private CategoryDao categoryDao;
	@Resource
	private ProductDao productDao;
	@Resource
	private ImageDao imageDao;
	
	/**
	 * 增加产品页面的类别下拉列表
	 */
	public List<Object> getCategoryList(String phone){
		Shop shop =shopDao.getByPhone(phone);
		List<Object> selectItemList = new ArrayList<Object>();
		List<Category> categoryList = categoryDao.getByShop(shop.getId());
		if(categoryList!=null && categoryList.size() >0){
			for(Category category:categoryList){
//				Object item =new SelectItem(category.getId(),category.getName());
				Object item =null;
				selectItemList.add(item);
			}
		}
		return selectItemList;
	}
	
	public void save(Product product, String phone, String description){
		Shop shop =shopDao.getByPhone(phone);
		product.setShop_id(shop.getId());
		product.setDescription(description);
		if(StringUtils.isBlank(product.getId())){
			product.setId(null);
			productDao.save(product);
		}
		else{
			productDao.update(product);
		}
	}
	
	public ProductVo get(String productId) throws IllegalAccessException, InvocationTargetException{
		Product product = productDao.get(productId);
		ProductVo productVo = new ProductVo();
		BeanUtils.copyProperties(productVo, product);
		productVo.setCategory_id(product.getCategory().getId());
		productVo.setCategoryName(product.getCategory().getName());
		List<Image> imageList= imageDao.getImageListByProductId(product.getId());
		productVo.setImageList(imageList);
		return productVo;
	}
	
	public List<Product> getFirstCategoryProduct(String phone){
		List<Product> productList = new ArrayList<Product>();
		List<Object> categoryList  = getCategoryList(phone);
		if(categoryList!=null && categoryList.size() >0){
			Object fist = categoryList.get(0);
//			productList = productDao.queryList("category_id", fist.getValue());
			productList = productDao.queryList("category_id", null);
		}
		return productList;
	}
	
}
