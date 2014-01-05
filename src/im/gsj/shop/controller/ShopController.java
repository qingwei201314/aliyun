package im.gsj.shop.controller;

import im.gsj.city.service.CityService;
import im.gsj.dao.ShopDao;
import im.gsj.entity.Shop;
import im.gsj.shop.CityVo;
import im.gsj.shop.service.ShopService;
import im.gsj.util.Constant;
import im.gsj.util.Util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/shop")
public class ShopController {
	@Resource
	private ShopService shopService;
	@Resource
	private ShopDao shopDao;
	@Resource
	private CityService cityService;
	private Shop shop = new Shop();
	private Integer province;
	private Integer town;
	private Integer city;
	private List<Object> townList;//市集合
	private List<Object> cityList; //县集合
	private static final Integer noChoice = -1; //页面请选择项的值
	
	@RequestMapping(value = "upload.do", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, ServletContext application) throws IOException {
		Util util = (Util)application.getAttribute("util");
		System.out.println("kevin");
		System.out.println(util.getUpload());
		
		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			
			// store the bytes somewhere
			return "redirect:uploadSuccess";
			}
			return "redirect:uploadFailure";
	}
	
	public String saveShop() throws IllegalAccessException, InvocationTargetException{
		String result = "/admin/category/addCategory";
		HttpSession session = null;
		String phone = (String)session.getAttribute(Constant.phone);
		if(this.city == 0){
//			FacesContext context = FacesContext.getCurrentInstance(); 
//			context.addMessage(null, new FacesMessage("地址不能为空!"));
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
			//控制页面是否显示图片
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
	 * 根据省查出市
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
