package com.example.demo.controller;

import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Signature;
import com.example.demo.dao.order_dao;
import com.example.demo.dao.user_dao;
import com.example.demo.models.Product;
import com.example.demo.models.orders;
import com.example.demo.models.user;
import com.razorpay.*;

import lombok.var;

class data {
	String User;
	Product[] Service;
}

class razorpayreq{
	
	 String razorpay_payment_id;
	 String razorpay_order_id;
	 String razorpay_signature;
}


@Controller
@CrossOrigin(origins = "*")
public class OrderController {
	
	@Autowired
	private order_dao obj;
	@Autowired
	private user_dao userObj;
	
	@Value("${PaymentGatewayId}")
	private String GatewayId;
	
	@Value("${PaymentGatewaySecret}")
	private String GatewaySecret;
	
	
	Logger log = Logger.getAnonymousLogger();
	
//	@GetMapping("getOrders/{userId}")
//	public orders getOrders(@PathVariable("userId") int userId) {
//		java.util.Optional<user> data = userObj.read_one(userId);
//		return null;
//		
//	}
	
	
	@ResponseBody
	@PostMapping("/onlinePayment/{email}")
	public String onlinePayment(
			@PathVariable("email") String User,
			@RequestBody Product[] Item
			) throws ParseException
	{
		int total = 0;
		String recipt = String.valueOf(obj.getOrderCount());
		user UserDetail = userObj.finduser(User);
		List<Product> ProdList = new ArrayList<Product>();  
		orders newOrder = new orders();		
		  for(Product prods:Item) {
			  ProdList.add(prods);
			  total+= prods.getPrice(); 
		  }
		  
		  newOrder.setItems(ProdList);
		  newOrder.setTotal_price(total);
		  newOrder.setUser_id(UserDetail);
		  log.info("Created Order ===>>> " + newOrder);
//		  obj.create(newOrder);
		  
		  try {
			  
			  var client = new RazorpayClient(GatewayId, GatewaySecret);
			  
			  log.info(GatewayId+GatewaySecret);

			  JSONObject orderRequest = new JSONObject();

			  orderRequest.put("amount", total*100); // amount in the smallest currency unit

			  log.info("Order Req put");
			  orderRequest.put("currency", "INR");
			  log.info("Order Req put done");
			  orderRequest.put("receipt",recipt );
			  Order order = client.Orders.create(orderRequest);
			  
			  newOrder.setRazorpay_id(order.get("id"));
			  
			  obj.create(newOrder);
			  
			  log.info("Price ------------> "+ total+" "+ newOrder);
			  
			  log.info("Order Created");

			  
			  return  order.toString(); 
			  
			} catch (RazorpayException e) {
			  // Handle Exception
			  System.out.println("Error --> "+e.getMessage());
			  return null;
			}
		  
		  
		
	}

	@ResponseBody
	@PostMapping("/checkOrder")
	public String checkOrder(
		  @RequestBody String razorpay_payment_id,
		  @RequestBody String razorpay_order_id,
		  @RequestBody String razorpay_signature
			) throws SignatureException {
		
		
		String[] raz = razorpay_order_id.split("&");
		
		String[] paymantId = raz[0].split("=");
		String[] orderId = raz[1].split("=");
		String[] sign = raz[2].split("=");
		
		orders data = obj.readbyOrderId(orderId[1]);
		
		if(data!=null) {
			String generated_signature = Signature.calculateRFC2104HMAC(data.getRazorpay_id() + "|" + paymantId[1], GatewaySecret);
			
			if (generated_signature.equals(sign[1])) {
				data.setPaid(true);
				obj.update(data);
				return "Payment Done";
			}else {
				log.info("Condition False");
				log.info(generated_signature);
				log.info(sign[1]);
				return "Payment Not Done";
			}
			
		}else {
			log.info("Condition Not Checked ");
			return "Payment Not Done";
		}
		
	}

	@ResponseBody
	@GetMapping("/getOrder/{id}")
	public List<orders>  getOrder(@PathVariable("id") String id) {
		user users = userObj.finduser(id);
		List<orders> Orders = obj.getOrderDetailByUserEmail(users);
		log.info("Listed Orders "+Orders);
		return Orders ;
	}

	@ResponseBody
	@GetMapping("allOrders")
	public List<orders> allOrders(){
		return obj.read();
	}

}
