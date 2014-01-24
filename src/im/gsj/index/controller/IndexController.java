package im.gsj.index.controller;

import im.gsj.index.service.IndexService;
import im.gsj.product.service.ProductService;
import im.gsj.util.Page;
import im.gsj.util.Util;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/index")
public class IndexController {
	@Resource
	private IndexService indexService;
	@Resource
	private ProductService productService;
	
	@RequestMapping(value="/index.do", method=RequestMethod.GET)
	public String index() {
		return "/index";
	}
	
	@RequestMapping(value="/home.do", method=RequestMethod.GET)
	public String home(@RequestParam("phone") String phone, @RequestParam("pageNo") int pageNo, ModelMap model){
		model = indexService.home(phone,pageNo, model);
		return "/shop/home";
	}
	
	/**
	 * 首页搜索
	 */
	@RequestMapping(value="search", method=RequestMethod.GET)
	public String search(@RequestParam("q") String q, int pageNo, ModelMap model){
		String viewResult = "/searchResult";
		//如果是手机号，直接跳转到商店首页
		if(q !=null &&q.matches("^(0|1)\\d{10,19}$")){
			viewResult = "redirect:" + Util.path() + "/" + q;
		}
		else{
			Page<im.gsj.index.vo.ProductVo> page =productService.indexSearch(q, pageNo);
			model.addAttribute("page", page);
			model.addAttribute("q", q);
		}
		
		return viewResult;
	}
}
