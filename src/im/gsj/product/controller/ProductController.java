package im.gsj.product.controller;

import im.gsj.category.service.CategoryService;
import im.gsj.dao.ProductDao;
import im.gsj.entity.Category;
import im.gsj.entity.Product;
import im.gsj.product.service.ProductService;
import im.gsj.product.vo.ProductVo;
import im.gsj.util.Constant;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Resource
	private ProductService productService;
	@Resource
	private ProductDao productDao;
	@Resource
	private CategoryService categoryService;
	
	private ProductVo productVo;
	private Product product;

	/**
	 * 跳转到增加产品页面
	 */
	@RequestMapping(value="toProduct.do", method=RequestMethod.GET)
	public String toProduct(HttpServletRequest request, ModelMap model) {
		String phone = (String)request.getSession().getAttribute(Constant.phone);
		//查出当前商店的分类
//		List<Category> categoryList = categoryService.list(phone);
//		model.addAttribute("categoryList", categoryList);
//		model.addAttribute("categoryId", categoryId);
		return "/admin/product/addProduct";
	}
	

	public String saveProduct() throws IllegalAccessException,
			InvocationTargetException {
		HttpServletRequest request = null;
		String description = request.getParameter("description");
		HttpSession session = request.getSession();
		String phone = (String) session.getAttribute(Constant.phone);
		productService.save(product, phone, description);
		productVo = productService.get(product.getId());
		return "/admin/product/addProductImage";
	}

	public String editProduct() {
		product = productDao.get(productVo.getId());
		return "/admin/product/addProduct";
	}

	public String editProductById(String productId) {
		product = productDao.get(productId);
		return "/admin/product/addProduct";
	}

//	public String deleteProduct(String productId, String curCategory) {
//		productDao.deleteById(productId);
//		category = curCategory;
//		HttpSession session = null;
//		String phone = (String) session.getAttribute(Constant.phone);
//		productList = productService.getFirstCategoryProduct(phone);
//		return "/admin/image/addImage";
//	}



	public List<Object> getCategoryList() {
		HttpSession session = null;
		String phone = (String) session.getAttribute(Constant.phone);
		List<Object> categoryList = productService.getCategoryList(phone);
		
		return categoryList;
	}

//	public ProductVo getProductVo() throws IllegalAccessException, InvocationTargetException {
//		HttpServletRequest request =null;
//		String productId = request.getParameter("productId");
//		if(productVo.getId() == null && StringUtils.isNotBlank(productId))
//			productVo = productService.get(productId);
//		return productVo;
//	}


//	public List<Product> getProductList() {
//		HttpSession session = null;
//		String phone = (String) session.getAttribute(Constant.phone);
//		if(category!=null)
//		productList = productDao.queryList("category_id", category);
//		
//		if (productList == null) 
//			productList = productService.getFirstCategoryProduct(phone);
//		
//		return productList;
//	}

}
