package im.gsj.product.service;

import im.gsj.category.service.CategoryService;
import im.gsj.dao.CategoryDao;
import im.gsj.dao.ImageDao;
import im.gsj.dao.ProductDao;
import im.gsj.dao.ShopDao;
import im.gsj.entity.Category;
import im.gsj.entity.Image;
import im.gsj.entity.Product;
import im.gsj.entity.Shop;
import im.gsj.image.service.ImageService;
import im.gsj.product.vo.ProductVo;
import im.gsj.uploadify.service.Uploadify;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

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
	@Resource
	private CategoryService categoryService;
	@Resource
	private Uploadify uploadify;
	@Resource
	private ImageService imageService;
	
	public void save(Product product, String phone){
		Shop shop =shopDao.getByPhone(phone);
		product.setShop_id(shop.getId());
		if(StringUtils.isEmpty(product.getId())){
			productDao.save(product);
		}
		else{
			productDao.update(product);
		}
	}
	
	/**
	 * 取得产品基本信息
	 */
	@Transactional(readOnly=true)
	public ProductVo get(String productId) throws IllegalAccessException, InvocationTargetException{
		Product product = productDao.get(productId);
		ProductVo productVo = new ProductVo();
		BeanUtils.copyProperties(product,productVo);
		productVo.setCategory_id(product.getCategory().getId());
		productVo.setCategoryName(product.getCategory().getName());
		List<Image> imageList= imageDao.getImageListByProductId(product.getId());
		productVo.setImageList(imageList);
		return productVo;
	}
	
	
	public ModelMap toEditProduct(String phone, String productId, ModelMap model){
		Product product = productDao.get(productId);
		//查出当前商店的分类
		List<Category> categoryList = categoryService.list(phone);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("categoryId", product.getCategory_id());
		model.addAttribute("product", product);
		return model;
	}
	
	/**
	 * 将图片写到磁盘，并保存数据库记录
	 * @param request
	 * @param widthXheight
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public String upload(HttpServletRequest request, String widthXheight) throws Exception{
		String productId = request.getParameter("productId");
		String phone = (String)request.getSession().getAttribute("phone");
		String result = uploadify.upload(phone, request,widthXheight);
		result = imageService.saveImage(productId, result);
		return result;
	}
}
