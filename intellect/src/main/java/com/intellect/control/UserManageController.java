package com.intellect.control;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intellect.modal.ResponseWrapper;
import com.intellect.modal.User;
import com.intellect.service.UserManageService;

@RestController
@RequestMapping("/api")
public class UserManageController {


	@Autowired
	private UserManageService userService;

	@Autowired
	@Qualifier("userval")
	private Validator validator;
	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		binder.addValidators(validator);//setValidator(validator);
	}

	@RequestMapping("/user/create")
	public ResponseWrapper createUser(@RequestBody @Validated User user, BindingResult bindingResult)
	{
		return userService.createUser(user,bindingResult);

	}


	@RequestMapping("/user/update")
	public ResponseWrapper updateUser(@RequestBody User user, BindingResult bindingResult)
	{
		return userService.updateUser(user,bindingResult);

	}

	@RequestMapping("/user/delete/{userid}")
	public ResponseWrapper updateUser(@PathVariable String userid)
	{
		return userService.updateUser(userid);

	}

	@RequestMapping("/usercollection")
	public List<User> collectionUsers()
	{
		return userService.collectionUsers();
	}



}
