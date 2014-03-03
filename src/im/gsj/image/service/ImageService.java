package im.gsj.image.service;

import im.gsj.category.service.CategoryService;
import im.gsj.dao.ImageDao;
import im.gsj.dao.ProductDao;
import im.gsj.dao.ShopDao;
import im.gsj.entity.Category;
import im.gsj.entity.Image;
import im.gsj.entity.Product;
import im.gsj.entity.Shop;
import im.gsj.shop.service.ShopService;
import im.gsj.util.Constant;
import im.gsj.util.Util;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ImageService {
	@Resource
	private ImageDao imageDao;
	@Resource
	private CategoryService categoryService;
	@Resource
	private ProductDao productDao;
	@Resource
	private ShopService shopService;
	@Resource
	private ShopDao shopDao;
	
	/**
	 * 保存图片的数据库记录
	 */
	public ImageResult saveImage(String product_id, String url){
		url = StringUtils.substringBefore(url, ",");
		String path = StringUtils.substringBeforeLast(url, "_");
		String postfix= "." + StringUtils.substringAfter(url, ".");
		Image image = new Image(product_id,path);
		image.setPostfix(postfix);
		imageDao.save(image);
		String resut = path + Constant.S + postfix;
		
		ImageResult imageResult =new ImageResult();
		imageResult.setImageId(image.getId());
		imageResult.setResut(resut);
		return imageResult;
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
		
		//使头部能显示
		Shop shop = shopService.getShopByPhone(phone);
		model.addAttribute("shop", shop);
		return model;
	}
	
	/**
	 * 删除图片：先删除数据库记录，再删除物理文件
	 */
	@Transactional
	public String deleteImage(String phone , String imageId, String uploadPath){
		String result = "failure";
		Image image = imageDao.get(imageId);
		Product product = productDao.get(image.getProduct_id());
		Shop shop = shopDao.get(product.getShop_id());
		//如果是当前用户
		if(phone.equals(shop.getUser().getPhone())){
			imageDao.delete(image);
			//删除物理文件
			String path = image.getPath();
			String postfix = image.getPostfix();
			try {
				String filePath = uploadPath + path + Constant.B + postfix;
				File file = new File(filePath);
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String filePath = uploadPath + path + Constant.S + postfix;
				File file = new File(filePath);
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			result= "success";
		}
		return result;
	}
}
