package im.gsj.category.service;

import im.gsj.dao.CategoryDao;
import im.gsj.dao.ImageDao;
import im.gsj.dao.ProductDao;
import im.gsj.dao.ShopDao;
import im.gsj.dao.dto.CategoryDto;
import im.gsj.dao.dto.ImageDto;
import im.gsj.entity.Category;
import im.gsj.entity.Image;
import im.gsj.entity.Product;
import im.gsj.entity.Shop;
import im.gsj.index.service.IndexService;
import im.gsj.index.vo.ProductVo;
import im.gsj.util.Constant;
import im.gsj.util.Page;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
	@Resource
	private ImageDao imageDao;
	@Resource
	private IndexService indexService;
	
	/**
	 * 取得当前店的所有分类
	 */
	@Transactional(readOnly=true)
	public List<Category> list(String phone) {
		Shop shop =shopDao.getByPhone(phone);
		List<Category> categoryList = categoryDao.getByShop(shop.getId());
		return categoryList;
	}
	
	@Transactional(readOnly=true)
	public List<CategoryDto> listCategoryDto(String phone) {
		Shop shop =shopDao.getByPhone(phone);
		List<CategoryDto> categoryList = categoryDao.getCategoryByShop(shop.getId());
		return categoryList;
	}
	
	@Transactional
	public boolean save(Category category, String phone) {
		boolean success = true;
		Shop shop =shopDao.getByPhone(phone);
	
		Long categoryCount = categoryDao.getCategoryCount(shop.getId());
		if(categoryCount >=5){
			//如果类别数大于5
			success = false;
		}
		else{
			//保存类别
			
			category.setShop_id(shop.getId());
			if(StringUtils.isEmpty(category.getId())){
				categoryDao.save(category);
			}
			else{
				categoryDao.update(category);
			}
		}
		return success;
	}
	
	/**
	 * 查出某一类别的产品
	 */
	@Transactional(readOnly=true)
	public ModelMap listProduct(String shopId, String categoryId,int pageNo, ModelMap model){
		model.addAttribute("categoryId", categoryId);
		List<Product> productList = productDao.listProduct(categoryId);
		List<ProductVo> productVoList = getFirstProductImage(productList);
		model.addAttribute("productVoList", productVoList);
		Page<ImageDto> page = imageDao.page(categoryId,pageNo);
		model.addAttribute("totalPage", page.getTotalPage());
		List<ImageDto> imageDtoList = page.getList();
		for(ImageDto imageDto: imageDtoList)
			imageDto.setPath(imageDto.getPath() + Constant.B);
		model.addAttribute("imageDtoList", imageDtoList);
		//取出头部和尾部的值
		model = indexService.getHeadAndFooter(shopId, model);
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
	
	/**
	 * 查出更多图片
	 */
	@Transactional(readOnly=true)
	public ModelMap moreImage(String categoryId,int pageNo, ModelMap model){
		List<ImageDto> imageDtoList = imageDao.pageByCategory(categoryId,pageNo);
		for(ImageDto imageDto: imageDtoList)
			imageDto.setPath(imageDto.getPath() + Constant.B);
		model.addAttribute("imageDtoList", imageDtoList);
		return model;
	}
	
	/**
	 * 查出某一商店的所有类别，并将当前类别取出
	 */
	@Transactional(readOnly=true)
	public ModelMap editCategory(String phone, String categoryId, ModelMap model) {
		Shop shop =shopDao.getByPhone(phone);
		List<CategoryDto> categoryList = categoryDao.getByShopExceptCurrent(shop.getId(), categoryId);
		model.addAttribute("categoryList", categoryList);
		
		//当前的选中项
		Category category = categoryDao.get(categoryId);
		model.addAttribute("category", category);
		
		//使头部能显示
		model.addAttribute("shop", shop);
		return model;
	}
}
