package im.gsj.shop.map.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/shop/map")
public class MapController {

	@RequestMapping(value="addAxis.do", method=RequestMethod.GET)
	public String addAxis(){
		
		return "/admin/shop/map/addAxis";
	}
}
