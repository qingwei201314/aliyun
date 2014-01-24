package im.gsj.user.controller;

import im.gsj.dao.CityDao;
import im.gsj.dao.UserDao;
import im.gsj.entity.User;
import im.gsj.shop.service.ShopService;
import im.gsj.user.service.UserService;
import im.gsj.util.Constant;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserDao userDao;
	@Resource
	private UserService userService;
	@Resource
	private ShopService shopService;
	@Resource 
	private CityDao cityDao;

	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login() {
		return "/user/login";
	}

	/**
	 * 用户登录
	 */
	@RequestMapping(value = "loginValidate.do", method = RequestMethod.POST)
	public String loginValidate(@ModelAttribute("user") User user, HttpSession session, ModelMap model) {
		String resultPath = "/user/login";
		boolean pass = userService.login(user);
		if (pass) {
			resultPath = "/admin/shop/addShop";
			session.setAttribute(Constant.phone, user.getPhone());
			//查出商店信息
			model = shopService.toShop(user.getPhone(), model);
		} else {
			model.addAttribute("message", "电话或密码错误!");
		}
		return resultPath;
	}

	/**
	 * 退出登录
	 */
	@RequestMapping(value="logout.do", method=RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute(Constant.phone);
		return "/user/login";
	}
}
