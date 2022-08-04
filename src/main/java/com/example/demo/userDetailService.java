package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.dao.user_dao;
import com.example.demo.models.user;

@Service
public class userDetailService implements UserDetailsService{
	@Autowired
	user_dao obj;
	@Override
	public com.example.demo.UserDetails loadUserByUsername(String s) throws UsernameNotFoundException{
		System.out.println("UserName ------->  "+s);
		user user =  obj.finduser(s);
		System.out.println("UserName ------->  "+user);
		return new com.example.demo.UserDetails(user);
	}

}
