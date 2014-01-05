package im.gsj.shop.service;

import im.gsj.dao.CityDao;
import im.gsj.dao.ShopDao;
import im.gsj.dao.UserDao;
import im.gsj.entity.City;
import im.gsj.entity.Shop;
import im.gsj.entity.User;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
	@Resource
	private UserDao userDao;
	@Resource
	private ShopDao shopDao;
	@Resource
	private CityDao cityDao;
	
	/**
	 * 保存对象
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public Shop dealShop(Shop shop, String phone, Integer city) throws IllegalAccessException, InvocationTargetException{
		User user = userDao.query("phone", phone);
		Shop shop_db = user.getShop();
		if(shop_db==null){
			shop_db = shop;
		}
		else{
			shop.setId(shop_db.getId());
			BeanUtils.copyProperties(shop_db, shop);
		}
		shop_db.setUser_id(user.getId());
		shop_db.setDistrict(city);
		if(shop_db.getId() == null){
			shopDao.save(shop_db);
		}
		else{
			shopDao.update(shop_db);
		}
		return shop;
	}
	
	/**
	 * 取得指id下的子城市
	 */
	public List<Object> getTown(Integer parentId){
		List<Object> itemList= new ArrayList<Object>();
		
		List<City> townList = cityDao.queryList("parentId", parentId);
		if(townList != null & townList.size() > 0){
			//加上默认值
			Object defaultItem = new Object();
//			defaultItem.setLabel("请选择");
//			defaultItem.setValue(-1);
			itemList.add(defaultItem);
			
			for(City town: townList){
				Object item = new Object();
//				item.setLabel(town.getName());
//				item.setValue(town.getId());
				itemList.add(item);
			}
		}

		return itemList;
	}
}
