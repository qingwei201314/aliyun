package im.gsj.user.controller;

import im.gsj.dao.UserDao;
import im.gsj.entity.User;
import im.gsj.user.service.UserService;
import im.gsj.util.Constant;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserController {
	@Resource
    private UserDao userDao;
	@Resource
	private UserService userService;
	private User user = new User();
	
    /**
     * 用户登录
     */
	public String login(){
		String resultPath = "/user/login";
		boolean pass = userService.login(user);
		if(pass){
			resultPath = "/admin/shop/addShop";
			HttpSession session = null;
			session.setAttribute(Constant.phone, user.getPhone());
		}
		else{
//			FacesContext context = FacesContext.getCurrentInstance(); 
//			context.addMessage(null, new FacesMessage("电话或密码错误!"));
		}
		return resultPath;
	}
	
	/**
	 * 用户注册
	 */
	public String saveUser(){
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
