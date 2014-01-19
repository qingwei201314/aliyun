package im.gsj.dao;

import im.gsj.entity.Map;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MapDao extends CommonDao<Map>{
	
	public void deleteByShop(String shop_id){
		List<Map> mapList  = queryList("shop_id", shop_id);
		if(mapList !=null && mapList.size()>0)
			for(Map map: mapList)
				delete(map);
	}
}
