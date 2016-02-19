package cn.cnct.repository.service.impl;

import java.util.List;

import javax.annotation.Resource;


import cn.cnct.repository.model.User;
import cn.cnct.repository.service.UserService;
import cn.cnct.repository.util.BaseDAO;
import cn.cnct.repository.util.BaseService;
import cn.cnct.repository.util.RequestParam;
import cn.cnct.repository.util.ReturnMessage;

public class UserServiceImpl implements UserService{
	private BaseService<User> baseService;
	private BaseDAO dao;

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	public BaseService<User> getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService<User> baseService) {
		this.baseService = baseService;
	}

	public ReturnMessage<User> addObject(User obj) {
		return baseService.addObject(obj);
	}

	public ReturnMessage<User> deleteObjectById(User t, long id) {
		return baseService.deleteObjectById(t, id);
	}

	public ReturnMessage<User> updateObject(User obj) {
		return baseService.updateObject(obj);
	}

	public ReturnMessage<User> queryObject(User obj, long id) {
		return baseService.queryObject(obj, id);
	}

	public ReturnMessage<User> list(RequestParam requestParam, Object... obj) {
		return baseService.list(requestParam, obj);
	}

	public ReturnMessage<User> queryByNameAndPwd(User user) {
		String hql = "from User where name=? and pwd=?";
		ReturnMessage<User> returnMessage = null;
		try{
			@SuppressWarnings("unchecked")
			List<User> list = dao.query(hql, user.getName(),user.getPwd());
			if(list == null || list.size()<1){
				returnMessage = new ReturnMessage<User>("999","用户名或者密码错误");
				return returnMessage;
			}
			returnMessage = new ReturnMessage<User>("100","成功");
			returnMessage.setObject(list.get(0));
		}catch(Exception e){
			e.printStackTrace();
			returnMessage = new ReturnMessage<User>("999","查询异常");
		}
		return returnMessage;
	}


	
}
