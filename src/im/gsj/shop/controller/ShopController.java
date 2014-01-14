package im.gsj.shop.controller;

import im.gsj.category.service.CategoryService;
import im.gsj.entity.Category;
import im.gsj.entity.Shop;
import im.gsj.shop.service.ShopService;
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

@Controller
@RequestMapping("/admin/shop")
public class ShopController {
	@Resource
	private ShopService shopService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private Uploadify uploadify;
	
	/**
	 * 上传大门图片
	 */
	@RequestMapping(value = "upload.do", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String phone = (String)request.getSession().getAttribute(Constant.phone);
		String widthXheight = request.getParameter("widthXheight");
		String result = uploadify.uploadGate(phone,request,widthXheight);
		PrintWriter pw = response.getWriter();
		pw.append(result);
		return null;
	}
	
	@RequestMapping(value = "saveShop.do", method = RequestMethod.POST)
	public String saveShop(@ModelAttribute("shop") Shop shop, HttpSession session, ModelMap model) throws IllegalAccessException, InvocationTargetException {
		String phone = (String)session.getAttribute(Constant.phone);
		shop = shopService.dealShop(shop, phone);
		
		//查出当前商店的分类
		List<Category> categoryList = categoryService.list(phone);
		model.addAttribute("categoryList", categoryList);
		return "/admin/category/addCategory";
	}
	
	/**
	 * 跳转到商店信息页面
	 */
	@RequestMapping(value = "addShop.do", method = RequestMethod.GET)
	public String addShop(HttpSession session, ModelMap model) throws IllegalAccessException, InvocationTargetException {
		String phone = (String)session.getAttribute(Constant.phone);
		model = shopService.toShop(phone, model);
		return "/admin/shop/addShop";
	}
}
