package im.gsj.user.service;

import im.gsj.dao.UserDao;
import im.gsj.entity.User;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Resource
	private UserDao userDao;
	
	/**
	 * 检查用户是否密码正确
	 */
	public boolean login(User user){
		boolean pass= false;
		User user_db = userDao.query("phone", user.getPhone());
		if(user_db!=null && user_db.getPassword().equals(user.getPassword()))
			pass = true;
		return pass;
	}
}
