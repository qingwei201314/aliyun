package im.gsj.index.service;

import im.gsj.city.service.CityService;
import im.gsj.dao.CategoryDao;
import im.gsj.dao.ProductDao;
import im.gsj.dao.ShopDao;
import im.gsj.entity.Category;
import im.gsj.entity.Image;
import im.gsj.entity.Product;
import im.gsj.entity.Shop;
import im.gsj.index.vo.ProductVo;
import im.gsj.shop.service.ShopService;
import im.gsj.util.Constant;
import im.gsj.util.Page;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

@Service
public class IndexService {
	@Resource
	private ShopService shopService;
	@Resource
	private CategoryDao categoryDao;
	@Resource
	private ProductDao productDao;
	@Resource
	private CityService cityService;
	@Resource
	private ShopDao shopDao;
	
	@Transactional(readOnly=true)
	public ModelMap home(String phone, int pageNo, ModelMap model){
		Shop shop = shopService.getShopByPhone(phone);
		model.addAttribute("shop", shop);
		model.addAttribute("userPhone", shop.getUser().getPhone());
		//取出省市区
		Integer cityId = shop.getDistrict();
		String provinceTownCity = cityService.getByShopDistrict(cityId);
		model.addAttribute("provinceTownCity", provinceTownCity);
		List<Category> categoryList = categoryDao.getByShop(shop.getId());
		model.addAttribute("categoryList", categoryList);
		Page<Product> originalPage = productDao.getNewProduct(shop.getId(), pageNo);
		Page<ProductVo> page  =  getFirstProductImage(originalPage);
		model.addAttribute("page", page);
		return model;
	}
	
	/**
	 * 取得每个产品的第一张图片
	 */
	private Page<ProductVo> getFirstProductImage(Page<Product> page){
		Page<ProductVo> newPage = new Page<ProductVo>(page.getPageNo(), page.getTotal());
		List<ProductVo> productVoList = newPage.getList();
		List<Product> productList = page.getList();
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
		return newPage;
	}
	
	/**
	 * 取出头部和尾部
	 */
	public ModelMap getHeadAndFooter(String shopId, ModelMap model){
		Shop shop = shopDao.get(shopId);
		model.addAttribute("shop", shop);
		model.addAttribute("userPhone", shop.getUser().getPhone());
		List<Category> categoryList = categoryDao.getByShop(shop.getId());
		model.addAttribute("categoryList", categoryList);
		return model;
	}
}
