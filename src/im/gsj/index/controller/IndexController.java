package im.gsj.index.controller;

import im.gsj.index.service.IndexService;

import javax.annotation.Resource;

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
	
	@RequestMapping(value="/index.do", method=RequestMethod.GET)
	public String index() {
		return "/index";
	}
	
	@RequestMapping(value="/home.do", method=RequestMethod.GET)
	public String home(@RequestParam("phone") String phone, @RequestParam("pageNo") int pageNo, ModelMap model){
		model = indexService.home(phone,pageNo, model);
		return "/shop/home";
	}
}
