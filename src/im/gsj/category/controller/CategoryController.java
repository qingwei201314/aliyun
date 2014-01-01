package im.gsj.category.controller;

import im.gsj.category.service.CategoryService;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/CategoryController")
public class CategoryController {
	@Resource
	private CategoryService categoryService;

	@RequestMapping(value="/addCategory", method=RequestMethod.GET)
	public String addCategory(Model model) {
		model.addAttribute("message", "Hello World!");
		System.out.println("kevin is good");
		categoryService.test();
		
		return "/addCategory";
	}
}
