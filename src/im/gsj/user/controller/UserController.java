package im.gsj.user.controller;

import im.gsj.dao.CityDao;
import im.gsj.dao.UserDao;
import im.gsj.entity.City;
import im.gsj.entity.User;
import im.gsj.shop.service.ShopService;
import im.gsj.user.service.UserService;
import im.gsj.util.Constant;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	private User user = new User();

	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login(Model model) {
		return "/user/login";
	}

	/**
	 * 用户登录
	 */
	@RequestMapping(value = "loginValidate.do", method = RequestMethod.POST)
	public String loginValidate(@ModelAttribute("user") User user, HttpServletRequest request, ModelMap model) {
		String resultPath = "/user/login";
		boolean pass = userService.login(user);
		if (pass) {
			resultPath = "/admin/shop/addShop";
			request.getSession().setAttribute(Constant.phone, user.getPhone());
			//查出区划信息
			List<City> provinces = cityDao.getProvince();
			model.addAttribute("provinces", provinces);
		} else {
			model.addAttribute("message", "电话或密码错误!");
		}
		return resultPath;
	}

	/**
	 * 用户注册
	 */
	public String saveUser() {
		userDao.save(user);
		HttpSession session = null;
		session.setAttribute(Constant.phone, user.getPhone());
		return "/shop/addShop";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
