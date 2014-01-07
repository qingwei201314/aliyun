package im.gsj.shop.service;

import im.gsj.dao.CityDao;
import im.gsj.dao.ShopDao;
import im.gsj.dao.UserDao;
import im.gsj.entity.City;
import im.gsj.entity.Shop;
import im.gsj.entity.User;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

@Service
public class ShopService {
	@Resource
	private UserDao userDao;
	@Resource
	private ShopDao shopDao;
	@Resource
	private CityDao cityDao;

	
	/**
	 * 淇濆瓨瀵硅薄
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@Transactional
	public Shop dealShop(Shop shop, String phone) throws IllegalAccessException, InvocationTargetException{
		User user = userDao.query("phone", phone);
		Shop shop_db = user.getShop();
		String id = null;
		if(shop_db==null){
			shop_db = shop;
		}
		else{
			id = shop_db.getId();
		}
		BeanUtils.copyProperties(shop, shop_db);
		shop_db.setId(id);
		shop_db.setUser_id(user.getId());
		shopDao.saveOrUpdate(shop_db);
		return shop_db;
	}
	
	/**
	 * 根据电话号码查出商店信息
	 * @param phone
	 * @return
	 */
	@Transactional(readOnly=true)
	public Shop getShopByPhone(String phone){
		User user = userDao.query("phone", phone);
		return user.getShop();
	}
	
	/**
	 * 查出进入商店页面的参数
	 */
	@Transactional(readOnly=true)
	public ModelMap toShop(String phone, ModelMap model){
		Shop shop = getShopByPhone(phone);
		if(shop!=null){
			model.addAttribute("shop", shop);
			
			//区
			Integer district = shop.getDistrict();
			City districtCity = cityDao.get(district);
			List<City> cityList = cityDao.queryList("parentId", districtCity.getParentId());
			model.addAttribute("cities",cityList);
			model.addAttribute("shopCity", districtCity.getId());
			
			//市
			City town = districtCity.getParent();
			List<City> townList = cityDao.queryList("parentId", town.getParentId());
			model.addAttribute("towns",townList);
			model.addAttribute("shopTown", town.getId());
			
			//查出区划信息 省
			model.addAttribute("shopProvince", town.getParentId());
		}
		//不管有没商店信息，都会存省的列表
		List<City> provinces = cityDao.getProvince();
		model.addAttribute("provinces", provinces);
		return model;
	}
}
