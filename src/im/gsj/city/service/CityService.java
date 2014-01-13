package im.gsj.city.service;

import im.gsj.dao.CityDao;
import im.gsj.entity.City;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class CityService {
	@Resource
	private CityDao cityDao;
	
	public String getByShopDistrict(Integer district){
		City city  = cityDao.get(district);
		City town = city.getParent();
		City province = town.getParent();
		String provinceTownCity = province.getName() + town.getName() + city.getName();
		return provinceTownCity;
	}
}
