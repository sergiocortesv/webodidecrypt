package com.k1687.odiutils.webodidecrypt;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.k1687.odiutils.ODIDecrypt;

@RestController
@EnableAutoConfiguration
public class WebDecrypt {

	@RequestMapping(path = "/odidecrypt",method = RequestMethod.POST)
	public ResponseEntity<Map<String,String>> decrypt(@RequestBody String encryptedStr){
		ODIDecrypt odiDecrypt = new ODIDecrypt();
		ResponseEntity responseEntity;
		Map<String,String> map = new HashMap<String,String>();
		String decrypted = "";
		try{
			decrypted = odiDecrypt.decrypt(encryptedStr);
			map.put("decryptedStr", decrypted);
			responseEntity = new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
		}catch(IllegalArgumentException ex){
			map.put("error",ex.getMessage());
			responseEntity = new ResponseEntity<Map<String,String>>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return responseEntity;
	}
	
	public static void main(String[] args)throws Exception{
		SpringApplication.run(WebDecrypt.class, args);
	}
	
}
