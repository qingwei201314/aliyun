package im.gsj.dao;

import java.util.List;

import im.gsj.entity.City;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityDao extends CommonDao<City>{
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<City> getProvince(){
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.isNull("parentId"));
		List<City> list = criteria.list();
		return list;
	}
}
