package com.k1687.odiutils.webodidecrypt;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.k1687.odiutils.ODIDecrypt;
import com.k1687.odiutils.SnpLoginSimple;

@RestController
@EnableAutoConfiguration
@ComponentScan 
public class WebDecrypt {

	@Autowired
	private OdiDecryptService decryptService;
	
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
	
	@RequestMapping(path="/xmlodidecrypt",method = RequestMethod.POST)
	public ResponseEntity<List<SnpLoginSimple>> handleXmlOdi(@RequestParam("file") MultipartFile file) throws IOException{
		List<SnpLoginSimple> logins = decryptService.decryptAll(file.getInputStream());
		return new ResponseEntity<List<SnpLoginSimple>>(logins,HttpStatus.OK);
	}
	
	public static void main(String[] args)throws Exception{
		SpringApplication.run(WebDecrypt.class, args);
	}
	
}
