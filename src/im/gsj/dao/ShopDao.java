package im.gsj.dao;

import im.gsj.entity.Shop;
import im.gsj.entity.User;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShopDao extends CommonDao<Shop>{
	@Resource
	private UserDao userDao;
	
	@Transactional(readOnly=true)
	public Shop getByPhone(String phone){
		User user = userDao.query("phone", phone);
		Shop shop =user.getShop();
		return shop;
	}
}
