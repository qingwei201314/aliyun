package im.gsj.user.service;

import im.gsj.dao.UserDao;
import im.gsj.entity.User;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	@Resource
	private UserDao userDao;
	
	/**
	 * 检查用户是否密码正确
	 */
	@Transactional(readOnly=true)
	public boolean login(User user){
		boolean pass= false;
		User user_db = userDao.query("phone", user.getPhone());
		if(user_db!=null && user_db.getPassword().equals(user.getPassword()))
			pass = true;
		return pass;
	}
	
	/**
	 * 新增用户
	 */
	public int addUser(User user){
		int result = 1;
		User oriUser= userDao.query("phone", user.getPhone());
		if(oriUser == null){
			//如果用户不存在，则保存,返回1
			userDao.save(user);
		}
		else{
			//如果 用户存在，返回2
			result = 2;
		}
		return result;
	}
	
	/**
	 * 更新密码
	 */
	@Transactional
	public void updateUser(String phone,String password){
		User user = userDao.query("phone", phone);
		user.setPassword(password);
		userDao.update(user);
	}
}
