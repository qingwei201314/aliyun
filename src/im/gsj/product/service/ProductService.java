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
import im.gsj.index.service.IndexService;
import im.gsj.product.vo.ProductVo;
import im.gsj.uploadify.service.Uploadify;
import im.gsj.util.Constant;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
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
	@Resource
	private IndexService indexService;

	public void save(Product product, String phone) {
		Shop shop = shopDao.getByPhone(phone);
		product.setShop_id(shop.getId());
		if (StringUtils.isEmpty(product.getId())) {
			product.setCreate_time(new Date());
			productDao.save(product);
		} else {
			Product oldProduct = productDao.get(product.getId());
			product.setCreate_time(oldProduct.getCreate_time());
			BeanUtils.copyProperties(product, oldProduct);
			productDao.update(oldProduct);
		}
	}

	/**
	 * 取得产品基本信息
	 */
	@Transactional(readOnly = true)
	public ProductVo get(String productId) throws IllegalAccessException,
			InvocationTargetException {
		Product product = productDao.get(productId);
		ProductVo productVo = new ProductVo();
		BeanUtils.copyProperties(product, productVo);
		productVo.setCategory_id(product.getCategory().getId());
		productVo.setCategoryName(product.getCategory().getName());
		List<Image> imageList = imageDao.getImageListByProductId(product
				.getId());
		productVo.setImageList(imageList);
		return productVo;
	}

	public ModelMap toEditProduct(String phone, String productId, ModelMap model) {
		Product product = productDao.get(productId);
		// 查出当前商店的分类
		List<Category> categoryList = categoryService.list(phone);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("categoryId", product.getCategory_id());
		model.addAttribute("product", product);
		return model;
	}

	/**
	 * 将图片写到磁盘，并保存数据库记录
	 * 
	 * @param request
	 * @param widthXheight
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public String upload(HttpServletRequest request, String widthXheight)
			throws Exception {
		String productId = request.getParameter("productId");
		String phone = (String) request.getSession().getAttribute("phone");
		String result = uploadify.upload(phone, request, widthXheight);
		result = imageService.saveImage(productId, result);
		return result;
	}

	@Transactional
	public ModelMap deleteProduct(String phone, String productId,
			String uploadPath, ModelMap model) {
		// 删除产品图片
		List<Image> imageList = imageDao.getImageListByProductId(productId);
		for (Image image : imageList) {
			String path = image.getPath();
			String postfix = image.getPostfix();
			imageDao.delete(image);
			// 删除物理文件
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
		}
		//删除产品信息
		Product product = productDao.get(productId);
		String categoryId = product.getCategory_id();
		productDao.delete(product);
		
		//查出页面要用到的值
		List<Category> categoryList = categoryService.list(phone);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("categoryId", categoryId);
		
		List<Product> productList =  productDao.queryList("category_id", categoryId);
		model.addAttribute("productList", productList);

		return null;
	}
	
	/**
	 * 取出产品的详细
	 */
	@Transactional(readOnly = true)
	public ModelMap viewProduct(String productId, int pageNo, ModelMap model) throws IllegalAccessException, InvocationTargetException{
		ProductVo productVo = viewProductWithImage(productId, pageNo);
		model.addAttribute("productVo", productVo);
		model.addAttribute("categoryId", productVo.getCategory_id()); //类别id,用于在头部的高亮
		
		//取出头部和尾部的参数
		model = indexService.getHeadAndFooter(productVo.getShop_id(), model);
		
		return model;
	}
	
	/**
	 * 取得产品信息以及图片信息
	 */
	@Transactional(readOnly = true)
	public ProductVo viewProductWithImage(String productId, int pageNo) throws IllegalAccessException,
			InvocationTargetException {
		Product product = productDao.get(productId);
		ProductVo productVo = new ProductVo();
		BeanUtils.copyProperties(product, productVo);
		productVo.setCategory_id(product.getCategory().getId());
		productVo.setCategoryName(product.getCategory().getName());
		List<Image> imageList = imageDao.pageImageByProduct(product.getId(), pageNo);
		imageList = addFormate(imageList);
		productVo.setImageList(imageList);
		return productVo;
	}
	
	/**
	 * 增加图片规格
	 */
	private List<Image> addFormate(List<Image> imageList){
		for(Image image: imageList){
			image.setPath(image.getPath() + Constant.B);
		}
		return imageList;
	}
}
