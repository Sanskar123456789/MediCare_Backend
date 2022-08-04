package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.CloudinaryDao;
import com.example.demo.dao.Prod_type_dao;
import com.example.demo.dao.prod_dao;
import com.example.demo.models.CloudImage;
import com.example.demo.models.Prod_type;
import com.example.demo.models.Product;

import lombok.Data;


@Data
class product {
	private int price;
	private int type;
	private String description;
	private String prod_name;
	private String cloud_id;
	private String image_url;
}

@RestController
@CrossOrigin(origins = "*")
public class ProdController {
	
	Logger log = Logger.getAnonymousLogger();
	@Autowired
	CloudinaryDao cloud;
	
	@Autowired
	prod_dao p_dao;
	
	@Autowired
	Prod_type_dao p_t_dao;
	
	@GetMapping("/getProducts")
	public List<Product> getProducts(){
		return p_dao.getAllProd();
	}
	
	@GetMapping("/getOneProduct/{id}")
	public Product getOneProd(@PathVariable("id") int id) {
		return p_dao.getOneProd(id);
	}
	
	@PostMapping("/newProduct")
	public Product newProduct
	(
			@RequestParam(value = "image", required = false) MultipartFile image,
			@RequestParam(value="prod_name") String prod_name,
			@RequestParam(value="price") int price,
			@RequestParam(value="description") String description,
			@RequestParam(value="type") int type
		) throws IOException{
				Product objProduct = new Product();
				CloudImage img =cloud.insert(image);
				if(img==null) {
					return null;
				}else {			
					objProduct.setDescription(description);
					objProduct.setPrice(price);
					objProduct.setProd_name(prod_name);
					Prod_type t=p_t_dao.getOneProdType(type);
					objProduct.setType(t);
					objProduct.setCloud_id(img.getImage_id());
					objProduct.setImage_url(img.getImage_Url());
					return p_dao.newProd(objProduct);
				}
		}
	
	
	@PutMapping("/updateProduct")
	public Product updateProduct(
			@RequestParam(value = "image") MultipartFile image,
			@RequestParam(value="prod_name") String prod_name,
			@RequestParam(value="cloud_id") String cloud_id,
			@RequestParam(value="price") int price,
			@RequestParam(value="description") String description,
			@RequestParam(value="type") int type ,
			@RequestParam(value="Prod_id") int Prod_id )
					throws IOException {
		Product objProduct = new Product();
		Map<?,?> del =cloud.delete(cloud_id);
		if(del==null) {
			return null;
		}else {
			CloudImage img =cloud.insert(image);
			if(img==null) {
				return null;
			}else {				
				objProduct.setProd_id(Prod_id);
				objProduct.setDescription(description);
				objProduct.setPrice(price);
				objProduct.setProd_name(prod_name);
				Prod_type t=p_t_dao.getOneProdType(type);
				objProduct.setType(t);
				objProduct.setCloud_id(img.getImage_id());
				objProduct.setImage_url(img.getImage_Url());
				return p_dao.updateProd(objProduct);
			}
		}
	}
	@PutMapping("/updateProductNotImage")
	public Product updateProductNotImage(
			@RequestParam(value="prod_name") String prod_name,
			@RequestParam(value="cloud_id") String cloud_id,
			@RequestParam(value="image_url") String image_url,
			@RequestParam(value="price") int price,
			@RequestParam(value="description") String description,
			@RequestParam(value="type") int type ,
			@RequestParam(value="Prod_id") int Prod_id 
			)
					throws IOException {
		Product objProduct = new Product();
		log.info("DATA ->" + prod_name + price + Prod_id);
		objProduct.setProd_id(Prod_id);
		objProduct.setDescription(description);
		objProduct.setPrice(price);
		objProduct.setProd_name(prod_name);
		Prod_type t=p_t_dao.getOneProdType(type);
		objProduct.setType(t);
		objProduct.setCloud_id(cloud_id);
		objProduct.setImage_url(image_url);
		return p_dao.updateProd(objProduct);
	}
	
	@DeleteMapping("/delteProduct/{id}")
	public boolean deleteProd(@PathVariable("id") int id) throws IOException {
		Product p = p_dao.getOneProd(id);
		String cid = p.getCloud_id();
		Map<?,?> r = cloud.delete(cid);
		if(r==null) {
			return false;
		}else {			
			return p_dao.deleteProd(id);
		}
	}
	
	@PostMapping("/fileCheck")
	public Product file(@RequestParam(value = "image", required = false) MultipartFile image,
						@RequestParam(value="prod_name") String prod_name,
						@RequestParam(value="price") int price,
						@RequestParam(value="description") String description,
						@RequestParam(value="type") int type
						) throws IOException{
							Product objProduct = new Product();
							CloudImage img =cloud.insert(image);
							if(img==null) {
								return null;
							}else {			
								objProduct.setDescription(description);
								objProduct.setPrice(price);
								objProduct.setProd_name(prod_name);
								Prod_type t=p_t_dao.getOneProdType(type);
								objProduct.setType(t);
								objProduct.setCloud_id(img.getImage_id());
								objProduct.setImage_url(img.getImage_Url());
								return p_dao.newProd(objProduct);
							}
					}

}
