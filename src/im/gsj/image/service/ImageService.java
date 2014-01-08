package im.gsj.image.service;

import im.gsj.category.service.CategoryService;
import im.gsj.dao.ImageDao;
import im.gsj.dao.ProductDao;
import im.gsj.entity.Category;
import im.gsj.entity.Image;
import im.gsj.entity.Product;
import im.gsj.util.Constant;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class ImageService {
	@Resource
	private ImageDao imageDao;
	@Resource
	private CategoryService categoryService;
	@Resource
	private ProductDao productDao;
	
	/**
	 * 保存图片的数据库记录
	 * @param product_id
	 * @param url
	 * @return
	 */
	public String saveImage(String product_id, String url){
		url = StringUtils.substringBefore(url, ",");
		String path = StringUtils.substringBeforeLast(url, "_");
		String postfix= "." + StringUtils.substringAfter(url, ".");
		Image image = new Image(product_id,path);
		image.setPostfix(postfix);
		imageDao.save(image);
		String resut = path + Constant.S + postfix;
		return resut;
	}
	
	/**
	 * 查出跳转到添加图片页面的各参数
	 */
	public ModelMap addImage(String phone, ModelMap model){
		List<Category> categoryList = categoryService.list(phone);
		model.addAttribute("categoryList", categoryList);
		if(categoryList!=null && categoryList.size()>0){
			Category category= categoryList.get(0);
			List<Product> productList =  productDao.queryList("category_id", category.getId());
			model.addAttribute("productList", productList);
		}
		return model;
	}
}
