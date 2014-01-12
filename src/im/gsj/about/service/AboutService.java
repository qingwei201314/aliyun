package im.gsj.about.service;

import im.gsj.dao.AboutDao;
import im.gsj.dao.ShopDao;
import im.gsj.entity.About;
import im.gsj.entity.Shop;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AboutService {
	@Resource
	private AboutDao aboutDao;
	@Resource
	private ShopDao shopDao;
	
	@Transactional
	public About saveAbout(About about, String phone){
		Shop shop = shopDao.getByPhone(phone);
		about.setShop_id(shop.getId());
			if(StringUtils.isEmpty(about.getId())){
				aboutDao.save(about);
			}
			else{
				aboutDao.update(about);
			}
			return about;
	}
	
	public About getByPhone(String phone){
		Shop shop = shopDao.getByPhone(phone);
		About  about = aboutDao.query("shop_id", shop.getId());
		return about;
	}
}
