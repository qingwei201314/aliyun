package im.gsj.product.controller;

import im.gsj.category.service.CategoryService;
import im.gsj.dao.ProductDao;
import im.gsj.entity.Category;
import im.gsj.entity.Product;
import im.gsj.product.service.ProductService;
import im.gsj.product.vo.ProductVo;
import im.gsj.uploadify.service.Uploadify;
import im.gsj.util.Constant;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	/**
	 * 跳转到增加产品页面
	 */
	@RequestMapping(value="addProduct.do", method=RequestMethod.GET)
	public String addProduct(@RequestParam("categoryId") String categoryId, HttpSession session, ModelMap model) {
		String phone = (String)session.getAttribute(Constant.phone);
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
	public String saveProduct(@ModelAttribute("product") Product product, HttpSession session, ModelMap model) throws IllegalAccessException, InvocationTargetException  {
		String phone = (String)session.getAttribute(Constant.phone);
		productService.save(product, phone);
		ProductVo productVo = productService.get(product.getId());
		model.addAttribute("productVo", productVo);
		
		return "/admin/product/addProductImage";
	}

	@RequestMapping(value="editProduct.do", method=RequestMethod.GET)
	public String editProduct(@RequestParam("productId") String productId, HttpSession session, ModelMap model) {
		String phone = (String)session.getAttribute(Constant.phone);
		model =productService.toEditProduct(phone, productId, model);
		return "/admin/product/addProduct";
	}
	
	/**
	 * 上传产品图片
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "upload.do", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, HttpServletResponse response) throws Exception{
			String widthXheight = request.getParameter("widthXheight");
			String result = productService.upload(request, widthXheight);
			PrintWriter pw = response.getWriter();
			pw.append(result);
			return null;
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
