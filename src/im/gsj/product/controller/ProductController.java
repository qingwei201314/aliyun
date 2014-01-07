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

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Resource
	private ProductService productService;
	@Resource
	private ProductDao productDao;
	@Resource
	private CategoryService categoryService;

	/**
	 * 跳转到增加产品页面
	 */
	@RequestMapping(value="toProduct.do", method=RequestMethod.GET)
	public String toProduct(HttpServletRequest request, ModelMap model) {
		String phone = (String)request.getSession().getAttribute(Constant.phone);
		String categoryId = request.getParameter("categoryId");
		//查出当前商店的分类
		List<Category> categoryList = categoryService.list(phone);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("categoryId", categoryId);
		return "/admin/product/addProduct";
	}
	
	/**
	 * 保存产品信息
	 */
	@RequestMapping(value="saveProduct.do", method=RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product, HttpServletRequest request, ModelMap model) throws IllegalAccessException, InvocationTargetException  {
		String phone = (String)request.getSession().getAttribute(Constant.phone);
		productService.save(product, phone);
		ProductVo productVo = productService.get(product.getId());
		model.addAttribute("productVo", productVo);
		
		return "/admin/product/addProductImage";
	}

	public String editProduct() {
//		Product product = productDao.get(productVo.getId());
		return "/admin/product/addProduct";
	}

	public String editProductById(String productId) {
		Product product = productDao.get(productId);
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
