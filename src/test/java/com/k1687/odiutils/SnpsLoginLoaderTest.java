package com.k1687.odiutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SnpsLoginLoaderTest {

	private final static String SNPS_LOGIN_SINGLE = "snps_login_work_1object.xml";
	private final static String SNPS_LOGIN_FIVE = "snps_login_work_5object.xml";
	private final static String SNPS_LOGIN_MALFORMED = "snps_login_work_1object_malformed.xml";
	
	private SnpsLoginLoader snpsLoginLoader; 
	
	@Before
	public void setup(){
		snpsLoginLoader = new SnpsLoginLoader();
	}
	
	@Test
	public void loadSnpLogin_singleLogin() throws IOException{
		ClassLoader classLoader = getClass().getClassLoader();
		File odiSnpFile = new File(classLoader.getResource(SNPS_LOGIN_SINGLE).getFile());
		InputStream istream = new FileInputStream(odiSnpFile);
		
		List<SnpLoginSimple> logins = snpsLoginLoader.loadSnpLogin(istream);
		Assert.assertEquals(1,logins.size());
	}
	
	@Test
	public void loadSnpLogin_singleLogin_loginDataComplete() throws IOException{
		ClassLoader classLoader = getClass().getClassLoader();
		File odiSnpFile = new File(classLoader.getResource(SNPS_LOGIN_SINGLE).getFile());
		InputStream istream = new FileInputStream(odiSnpFile);
		
		List<SnpLoginSimple> logins = snpsLoginLoader.loadSnpLogin(istream);
		Assert.assertEquals("WORK_SPA_VF",logins.get(0).getLoginName());
		Assert.assertEquals("SUPERVISOR",logins.get(0).getLoginUser());
		Assert.assertEquals("fDyX5mBYMBbAnPA6N1Vy",logins.get(0).getLoginPassword());
		Assert.assertEquals("BFDEV_ODI_MASTER_REP",logins.get(0).getLoginDbUser());
		Assert.assertEquals("aYyHzTx4bshElIvSaIIHBp",logins.get(0).getLoginDbPassword());
	}
	
	@Test
	public void loadSnpLogin_fiveLogin() throws IOException{
		ClassLoader classLoader = getClass().getClassLoader();
		File odiSnpFile = new File(classLoader.getResource(SNPS_LOGIN_FIVE).getFile());
		InputStream istream = new FileInputStream(odiSnpFile);
		
		List<SnpLoginSimple> logins = snpsLoginLoader.loadSnpLogin(istream);
		Assert.assertEquals(5,logins.size());		
	}
	
	@Test(expected = TechnicalException.class)
	public void loadSnpLogin_malformedOdi() throws IOException{
		ClassLoader classLoader = getClass().getClassLoader();
		File odiSnpFile = new File(classLoader.getResource(SNPS_LOGIN_MALFORMED).getFile());
		InputStream istream = new FileInputStream(odiSnpFile);
		
		snpsLoginLoader.loadSnpLogin(istream);
	}
}
