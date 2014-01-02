package im.gsj.dao;

import im.gsj.entity.City;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

@Service
public class CityDao extends CommonDao<City>{
	/**
	 * 取出第一层的城市
	 */
	@SuppressWarnings("unchecked")
	public List<City> queryFirst(){
		Query query = super.getSessionFactory().getCurrentSession().createQuery("from " + City.class.getName() + " t where t.parentId is null");
		List<City> result = (List<City>)query.list();
		return result;
	}

}
