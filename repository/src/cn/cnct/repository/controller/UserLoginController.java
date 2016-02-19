package cn.cnct.repository.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cnct.repository.model.User;
import cn.cnct.repository.service.UserService;
import cn.cnct.repository.util.ReturnMessage;

@Controller
@RequestMapping(value="/user")
public class UserLoginController{
	@Resource(name="userService")
	private UserService userSerivce;
	
	@RequestMapping(value="/login")
	public String login(Model model,User user){
		ReturnMessage<User> returnMessage = userSerivce.queryByNameAndPwd(user);
		
		model.addAttribute("user",user);
		return "index";
	}
	@RequestMapping(value="/query/{id}")
	public @ResponseBody User queryUser(@PathVariable("id") long id){
		User user = new User();
		ReturnMessage<User> returnMessage = userSerivce.queryObject(user, id);
		user = returnMessage.getObject();
		return user;
		
	}
	
}
