package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.user_dao;
import com.example.demo.models.user;
@RestController
@CrossOrigin(origins = "*")
public class userController {
	
	@Autowired
	user_dao dao;
	
	@PostMapping("newUser")
	public user newUser(@RequestBody user req) {
		user search = dao.finduser(req.getEmail());
		if(search == null) {			
			user obj = dao.create(req);
			if(obj!=null) {
				return obj;
			}else {
				return null;
				
			}
		}else {
			return null;
		}
	}
	
	@PostMapping("user")
	public user user(@RequestBody String email) {
		return dao.finduser(email);
	}

	@GetMapping("allUsers")
	public List<user> allUsers(){
		return dao.read();
	}
	
}
