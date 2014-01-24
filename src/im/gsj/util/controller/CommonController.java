package im.gsj.util.controller;

import im.gsj.entity.Shop;
import im.gsj.shop.service.ShopService;
import im.gsj.util.Constant;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 公共controller
 * @author Kevin
 *
 */
public class CommonController {
	@Resource
	private ShopService shopService;
	
	/**
	 * 当有异常时，跳转到其他页面
	 */
	@ExceptionHandler(Exception.class)
	public String handleIOException(Exception ex, HttpServletRequest request) {
		//使头部能显示
		String phone = (String) request.getSession().getAttribute(Constant.phone);
		Shop shop = shopService.getShopByPhone(phone);
		request.setAttribute("shop", shop);
		return "/error/admin/error";
	}
}
