package com.example.demo.dao;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Product;


@Service
public class prod_dao {
	
	@Autowired
	prod_repo obj;
	Logger log = Logger.getAnonymousLogger();
	public Product newProd(Product e) {
		return obj.save(e);
	}
	
	public List<Product> getAllProd(){
		return obj.findAll();
	}
	
	public boolean deleteProd(int id) {
		obj.deleteById(id);
		return true;
	}
	
	public Product getOneProd(int id) {
		return obj.findbyid(id);
	}
	
	public Product updateProd(Product e) {
		Product prod  = new Product();
		prod.setProd_id(e.getProd_id());
		prod.setProd_name(e.getProd_name());
		prod.setDescription(e.getDescription());
		prod.setImage_url(e.getImage_url());
		prod.setCloud_id(e.getCloud_id());
		prod.setPrice(e.getPrice());
		prod.setType(e.getType());
		log.info(" Requested update "+prod);
		return obj.save(prod);
	}

}
