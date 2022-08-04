package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.order_dao;
import com.example.demo.models.orders;

class Dao {

	
	@Autowired
	private order_dao userdao;
	
	Logger log = Logger.getAnonymousLogger();
	
	@Test
	public void username() {		
		orders obj = userdao.readbyOrderId("order_K17sOgar3iocQP");
		log.info(obj.toString());
		System.out.println(obj);
		if(obj.getRazorpay_id()==null) {			
			assertEquals(true,false);
		}else {
			assertEquals(true,true);
		}
	}

}
