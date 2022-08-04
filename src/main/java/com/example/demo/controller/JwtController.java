package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.JWTUtility;
import com.example.demo.userDetailService;
import com.example.demo.Security.env;
import com.example.demo.models.JwtReq;
import com.example.demo.models.JwtRes;


@CrossOrigin(origins = "*")
@RestController
public class JwtController {
	@Autowired
	private JWTUtility jwt;
	@Autowired
	private userDetailService userService;
	@Autowired
	private env key;
	
	
	@PostMapping("authenticate")
	public JwtRes login(@RequestBody JwtReq req) throws Exception {
		System.out.println("Key----------->" + key.getSecret());
		try {					
			final com.example.demo.UserDetails userdata = userService.loadUserByUsername(req.getEmail());
			if(userdata.getEmail()==null) {
				final String token = "Invalid Email";
				return new JwtRes(token);
			}else {				
				if(userdata.getPassword().equals(req.getPassword())){				
					final String token = jwt.generateToken(userdata);
					return new JwtRes(token);
				}else {
					final String token = "Invalid";
					return new JwtRes(token);
				}
			}
		}catch(BadCredentialsException e) {
			
			System.out.println("Key----------->" + key.getSecret());
			throw new Exception("Invalid Credentials",e);
		}
		
		
	}
	
}
