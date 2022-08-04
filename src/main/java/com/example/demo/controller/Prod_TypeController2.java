package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.Prod_type_dao;
import com.example.demo.models.Prod_type;

@RestController
@CrossOrigin(origins= "*")
public class Prod_TypeController2 {
	
	@Autowired
	Prod_type_dao p_dao;
	
	@GetMapping("getProductsType")
	public List<Prod_type> getAllProduct(){
		return p_dao.getAllProd_type();
	}
	
	@GetMapping("getOneProductType/{id}")
	public Prod_type getOneProd(@PathVariable("id") int id) {
		return p_dao.getOneProdType(id);
	}
	
	@PostMapping("newProductType")
	public Prod_type newProd(@RequestBody Prod_type e) {
		return p_dao.newProd(e);
	}
	
	@PutMapping("updateProductType")
	public Prod_type updateProductType(@RequestBody Prod_type e) {
		System.out.println(e);
		return p_dao.update(e);
	}
	
	@DeleteMapping("delteProductType/{id}")
	public boolean deleteProd(@PathVariable("id") int id) {
		return p_dao.delete_prod_type(id);
	}

}
