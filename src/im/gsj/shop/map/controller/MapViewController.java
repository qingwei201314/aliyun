package im.gsj.shop.map.controller;

import im.gsj.dao.MapDao;
import im.gsj.entity.Map;
import im.gsj.index.service.IndexService;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shop/map")
public class MapViewController {
	@Resource
	private MapDao mapDao;
	@Resource
	private IndexService indexService;

	@RequestMapping(value="viewMap.do", method=RequestMethod.GET)
	public String viewMap(@RequestParam("shopId") String shopId, ModelMap model){
		Map map = mapDao.query("shop_id", shopId);
		model.addAttribute("map", map);
		//查出头部及尾部信息
		model = indexService.getHeadAndFooter(shopId, model);
		return "/shop/map/viewMap";
	}
}
