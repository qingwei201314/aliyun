package im.gsj.category.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/category")
public class CategoryViewController {
	
	/**
	 * 取得某一商店某一类别的所有产品
	 */
	@RequestMapping(value="listProduct.do", method=RequestMethod.GET)
	public String listProduct(@RequestParam("shopId") String shopId, @RequestParam("categoryId") String categoryId, ModelMap model){
		
		return "";
	}
}
