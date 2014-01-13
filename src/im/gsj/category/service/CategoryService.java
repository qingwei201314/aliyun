package im.gsj.category.service;

import im.gsj.dao.CategoryDao;
import im.gsj.dao.ProductDao;
import im.gsj.dao.ShopDao;
import im.gsj.entity.Category;
import im.gsj.entity.Image;
import im.gsj.entity.Product;
import im.gsj.entity.Shop;
import im.gsj.index.vo.ProductVo;
import im.gsj.util.Constant;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

@Service
public class CategoryService {
	@Resource
	private CategoryDao categoryDao;
	@Resource
	private ShopDao shopDao;
	@Resource
	private ProductDao productDao;
	
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
	
	/**
	 * 查出某一商店某一类别的产品
	 */
	public ModelMap listProduct(String shopId, String categoryId, ModelMap model){
		List<Product> productList = productDao.listProduct(shopId, categoryId);
		List<ProductVo> productVoList = getFirstProductImage(productList);
		model.addAttribute("productVoList", productVoList);
		
		
		return model;
	}
	
	/**
	 * 取得每个产品的第一张图片
	 */
	private List<ProductVo> getFirstProductImage(List<Product> productList){
		 List<ProductVo> productVoList = new ArrayList<ProductVo>();
		for(Product product: productList){
			ProductVo productVo =new ProductVo();
			BeanUtils.copyProperties(product, productVo);
			//第一张图片
			List<Image> images = product.getImages();
			if(images!=null && images.size() >0){
				productVo.setPath(images.get(0).getPath()+ Constant.S);
				productVo.setPostfix(images.get(0).getPostfix());
			}
			productVoList.add(productVo);
		}
		return productVoList;
	} 
}
