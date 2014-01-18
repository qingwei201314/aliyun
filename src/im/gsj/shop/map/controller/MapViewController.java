package im.gsj.shop.map.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/shop/map")
public class MapViewController {

	@RequestMapping(value="viewMap.do", method=RequestMethod.GET)
	public String viewMap(){
		
		return "/shop/map/viewMap";
	}
}
