package im.gsj.shop.controller;

import im.gsj.city.service.CityService;
import im.gsj.dao.ShopDao;
import im.gsj.entity.Shop;
import im.gsj.shop.CityVo;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/shop")
public class ShopController {
	@Resource
	private ShopService shopService;
	@Resource
	private ShopDao shopDao;
	@Resource
	private CityService cityService;
	@Resource
	private Uploadify uploadify;
	
	private Shop shop = new Shop();
	private Integer province;
	private Integer town;
	private Integer city;
	private List<Object> townList;//甯傞泦鍚�
	private List<Object> cityList; //鍘块泦鍚�
	private static final Integer noChoice = -1; //椤甸潰璇烽�鎷╅」鐨勫�

	/**
	 * 上传大门图片
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "upload.do", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String widthXheight = request.getParameter("widthXheight");
		String result = uploadify.uploadGate(request,widthXheight);
		PrintWriter pw = response.getWriter();
		pw.append(result);
		return null;
	}
	
	@RequestMapping(value = "saveShop.do", method = RequestMethod.POST)
	public String saveShop(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
		String result = "/admin/category/addCategory";
		String phone = (String)request.getSession().getAttribute(Constant.phone);
		if(this.city == 0){
			result= "/admin/shop/addShop";
		}else{
			shop = shopService.dealShop(shop, phone, city);
		}
		return result;
	}
	
	
	public List<Object> getTowns(){
		townList = shopService.getTown(province);
		return townList;
	}
	
	public Shop getShop() {
		HttpSession session = null;
		String phone = (String)session.getAttribute(Constant.phone);
		if(shop == null ||shop.getId() == null)
			shop = shopDao.getByPhone(phone);
		if(shop !=null){
			CityVo cityVo = cityService.getByShopDistrict(shop.getDistrict());
			this.province = cityVo.getProvince();
			this.town = cityVo.getTown();
			this.city = cityVo.getCity();
			cityList = shopService.getTown(town);
			//鎺у埗椤甸潰鏄惁鏄剧ず鍥剧墖
			if(shop.getGate_url()==null)
				shop.setGate_url("none");
		}
		else{
			shop = new Shop();
			shop.setGate_url("none");
		}
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Integer getProvince() {
		return province;
	}

	/**
	 * 鏍规嵁鐪佹煡鍑哄競
	 */
	public void setProvince(Integer province) {
		this.province = province;
		this.town =  noChoice;
		this.city = noChoice;
		this.townList = null;
	    this.cityList = null;
		townList = shopService.getTown(province);
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getCity() {
		return city;
	}

	public List<Object> getCities(){
		return cityList;
	}
	
	public void setTown(Integer town) {
		this.town = town;
		cityList = shopService.getTown(town);
	}
	
	public Integer getTown() {
		return town;
	}
}
