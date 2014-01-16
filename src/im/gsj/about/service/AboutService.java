package im.gsj.about.service;

import im.gsj.dao.AboutDao;
import im.gsj.dao.ShopDao;
import im.gsj.entity.About;
import im.gsj.entity.Shop;
import im.gsj.index.service.IndexService;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

@Service
public class AboutService {
	@Resource
	private AboutDao aboutDao;
	@Resource
	private ShopDao shopDao;
	@Resource
	private IndexService indexService;
	
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
		About about = aboutDao.query("shop_id", shop.getId());
		return about;
	}
	
	/**
	 * 查出跳转到《关于我们》所需要的参数
	 */
	@Transactional(readOnly=true)
	public ModelMap aboutUs(String phone, ModelMap model){
		Shop shop = shopDao.getByPhone(phone);
		About about = aboutDao.query("shop_id", shop.getId());
		model.addAttribute("about", about);
		model = indexService.getHeadAndFooter(shop.getId(), model);
		return model;
	}
}
