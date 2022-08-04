package com.example.demo.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import com.example.demo.models.CloudImage;

@Service
public class CloudinaryDao {
	Cloudinary cloudinary;
	
	Logger log = Logger.getAnonymousLogger();
	private Map <String, String> cloudinaryValues = new  HashMap<String, String>();
	
	public CloudinaryDao() {
		cloudinaryValues.put("cloud_name", "dsv4yzr1h");
		cloudinaryValues.put("api_key", "678177167121235");
		cloudinaryValues.put("api_secret", "sHBb6Bjkr_6hWxzi1_g2UuhNZBY");
		cloudinary = new Cloudinary(cloudinaryValues);
	}
	
	public CloudImage insert(MultipartFile m ) throws IOException {
		File file = convert(m);
		Map<?, ?> result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
		file.delete();
		CloudImage obj = new CloudImage();
		if(result.containsKey("url")) {			
			obj.setImage_Url(result.get("url").toString());
			obj.setImage_id(result.get("public_id").toString());
			log.info("Result ---> " +obj);
			return obj;
		}else {
			return null;
		}
		
	}
	
	public Map<?, ?> delete(String id) throws IOException {
		Map<?, ?> result  = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
		if(result.get("result").toString().equals("ok")) {			
			return result;
		}else {
			return null;
		}
	}
	
	public File convert(MultipartFile m) throws IOException {
		File file = new File(m.getOriginalFilename());
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(m.getBytes());
		fo.close();
		return file;
	}

}
