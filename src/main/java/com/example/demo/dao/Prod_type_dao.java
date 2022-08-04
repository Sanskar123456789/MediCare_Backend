package com.example.demo.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.Prod_type;

@Service
public class Prod_type_dao {
	
	@Autowired prod_type obj;
	
	public Prod_type newProd(Prod_type e) {
		return obj.save(e);
	}
	
	public List<Prod_type> getAllProd_type() {
		return obj.findAll();
	}
	
	public Prod_type getOneProdType(int id) {
		return obj.findbyid(id);
	}
	
	public boolean delete_prod_type(int id) {
		obj.deleteById(id);
		return true;
	}
	
	public Prod_type update(Prod_type e) {
		Prod_type s = new Prod_type();
		s.setType_name(e.getType_name());
		s.setType_id(e.getType_id());
		return obj.save(s);
	}

}
