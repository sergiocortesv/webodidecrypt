package com.k1687.odiutils.webodidecrypt;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.k1687.odiutils.ODIDecrypt;
import com.k1687.odiutils.SnpLoginSimple;
import com.k1687.odiutils.SnpsLoginLoader;
import com.k1687.odiutils.TechnicalException;

@Service
public class OdiDecryptService {

	private ODIDecrypt odiDecrypt;
	private SnpsLoginLoader loginLoader;
	private static final String INVALID_PASSWORD_ERROR = "<ERROR INVALID ENCRYPTED PASSWORD>";
	
	public OdiDecryptService(){
		odiDecrypt = new ODIDecrypt();
		loginLoader = new SnpsLoginLoader();
	}
	
	public List<SnpLoginSimple> decryptAll(List<SnpLoginSimple> logins) {
		for(SnpLoginSimple login : logins){
			try{
				String decryptedStr = login.getLoginDbPassword() != null ? odiDecrypt.decrypt(login.getLoginDbPassword()) : ""; 
				login.setLoginDbPassword(decryptedStr);
			}catch(IllegalArgumentException ex){
				login.setLoginDbPassword(INVALID_PASSWORD_ERROR);
			}
			
			try{
				String decryptedStr = login.getLoginPassword() != null ? odiDecrypt.decrypt(login.getLoginPassword()) : "";
				login.setLoginPassword(decryptedStr);
			}catch(IllegalArgumentException ex){
				login.setLoginPassword(INVALID_PASSWORD_ERROR);
			}
		}
		return logins;
	}
	
	public List<SnpLoginSimple> decryptAll(InputStream odiXmlInstream){
		try {
			return decryptAll(loginLoader.loadSnpLogin(odiXmlInstream));
		} catch (IOException e) {
			throw new TechnicalException(e);
		}
	}
}
