package im.gsj.category.controller;

import im.gsj.category.service.CategoryService;
import im.gsj.dao.CategoryDao;
import im.gsj.entity.Category;
import im.gsj.util.Constant;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Resource
	private CategoryService categoryService;
	@Resource
	private CategoryDao categoryDao;

	@RequestMapping(value="saveCategory.do", method=RequestMethod.POST)
	public String saveCategory(@ModelAttribute("category") Category category,HttpServletRequest request,  ModelMap model) throws UnsupportedEncodingException{
		String phone = (String)request.getSession().getAttribute(Constant.phone);
		categoryService.save(category, phone);
		
		//查出当前商店的分类
		List<Category> categoryList = categoryService.list(phone);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("categoryId", category.getId());
		return "/admin/product/addProduct";
	}
	
	@RequestMapping(value="deleteCategory.do", method=RequestMethod.GET)
	public String deleteCategory(@RequestParam("deleteCategoryId") String deleteCategoryId, HttpServletRequest request, ModelMap model){
		categoryDao.deleteById(deleteCategoryId);
		
		//查出当前商店的分类
		String phone = (String)request.getSession().getAttribute(Constant.phone);
		List<Category> categoryList = categoryService.list(phone);
		model.addAttribute("categoryList", categoryList);
		return "/admin/category/addCategory";
	}
	
	/**
	 * 跳转到类别页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addCategory.do", method=RequestMethod.GET)
	public String addCategory(HttpServletRequest request, ModelMap model) {
		String phone = (String)request.getSession().getAttribute(Constant.phone);
		//查出当前商店的分类
		List<Category> categoryList = categoryService.list(phone);
		model.addAttribute("categoryList", categoryList);
		return "/admin/category/addCategory";
	}
}
