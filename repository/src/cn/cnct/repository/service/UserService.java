package cn.cnct.repository.service;

import org.springframework.stereotype.Service;

import cn.cnct.repository.model.User;
import cn.cnct.repository.util.BaseService;
import cn.cnct.repository.util.ReturnMessage;
public interface UserService extends BaseService<User>{
	public ReturnMessage<User> queryByNameAndPwd(User user);
}
