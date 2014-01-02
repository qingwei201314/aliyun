package im.gsj.city.service;

import im.gsj.dao.CityDao;
import im.gsj.entity.City;
import im.gsj.shop.CityVo;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class CityService {
	@Resource
	private CityDao cityDao;
	
	public CityVo getByShopDistrict(Integer district){
		City city  = cityDao.get(district);
		CityVo cityVo = new CityVo();
		cityVo.setCity(city.getId()); //区
		City town = city.getParent();
		cityVo.setTown(town.getId()); //市
		cityVo.setProvince(town.getParentId()); //省
		return cityVo;
	}
}
