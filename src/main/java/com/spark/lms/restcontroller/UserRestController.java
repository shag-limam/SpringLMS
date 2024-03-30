package com.spark.lms.restcontroller;

import com.spark.lms.model.User;
import com.spark.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/user")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value = {"/", "/list"})
	public List<User> all() {
		return userService.getAllUsers();
	}


	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.get(id);
	}
}
