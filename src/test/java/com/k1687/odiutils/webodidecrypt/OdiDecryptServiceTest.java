package com.k1687.odiutils.webodidecrypt;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.k1687.odiutils.SnpLoginSimple;

import junit.framework.Assert;

public class OdiDecryptServiceTest {
	
	@Test
	public void decryptAll(){
		OdiDecryptService decryptService = new OdiDecryptService();
		List<SnpLoginSimple> logins = new ArrayList<SnpLoginSimple>();
		SnpLoginSimple login = new SnpLoginSimple();
		login.setLoginName("LoginOdi");
		login.setLoginUser("odiUser");
		login.setLoginPassword("fDyX5mBYMBbAnPA6N1Vy");
		login.setLoginDbUser("dbUser");
		login.setLoginDbPassword("aYyHzTx4bshElIvSaIIHBp");
		logins.add(login);
		
		List<SnpLoginSimple> decryptedLogins = decryptService.decryptAll(logins);
		Assert.assertEquals(1,decryptedLogins.size());
		Assert.assertEquals("SUNOPSIS",decryptedLogins.get(0).getLoginPassword());
		Assert.assertEquals("baufest0",decryptedLogins.get(0).getLoginDbPassword());
	}
	
	@Test
	public void decryptAll_nullPassword(){
		OdiDecryptService decryptService = new OdiDecryptService();
		List<SnpLoginSimple> logins = new ArrayList<SnpLoginSimple>();
		SnpLoginSimple login = new SnpLoginSimple();
		login.setLoginName("LoginOdi");
		login.setLoginUser("odiUser");
		login.setLoginPassword(null);
		login.setLoginDbUser("dbUser");
		login.setLoginDbPassword("aYyHzTx4bshElIvSaIIHBp");
		logins.add(login);
		
		List<SnpLoginSimple> decryptedLogins = decryptService.decryptAll(logins);
		Assert.assertEquals(1,decryptedLogins.size());
		Assert.assertEquals("",decryptedLogins.get(0).getLoginPassword());
		Assert.assertEquals("baufest0",decryptedLogins.get(0).getLoginDbPassword());
	}
	
	@Test
	public void decryptAll_emptyPassword(){
		OdiDecryptService decryptService = new OdiDecryptService();
		List<SnpLoginSimple> logins = new ArrayList<SnpLoginSimple>();
		SnpLoginSimple login = new SnpLoginSimple();
		login.setLoginName("LoginOdi");
		login.setLoginUser("odiUser");
		login.setLoginPassword("");
		login.setLoginDbUser("dbUser");
		login.setLoginDbPassword("aYyHzTx4bshElIvSaIIHBp");
		logins.add(login);
		
		List<SnpLoginSimple> decryptedLogins = decryptService.decryptAll(logins);
		Assert.assertEquals(1,decryptedLogins.size());
		Assert.assertEquals("",decryptedLogins.get(0).getLoginPassword());
		Assert.assertEquals("baufest0",decryptedLogins.get(0).getLoginDbPassword());
	}
	
}
