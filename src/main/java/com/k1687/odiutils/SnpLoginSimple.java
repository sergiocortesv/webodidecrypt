package com.k1687.odiutils;

/**
 * Represents a basic ODI SNPLogin with only the user login data 
 * @author scortes
 */
public class SnpLoginSimple {

	private String loginName;
	private String loginUser;
	private String loginPassword;
	private String loginDbUser;
	private String loginDbPassword;
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getLoginDbUser() {
		return loginDbUser;
	}
	public void setLoginDbUser(String loginDbUser) {
		this.loginDbUser = loginDbUser;
	}
	public String getLoginDbPassword() {
		return loginDbPassword;
	}
	public void setLoginDbPassword(String loginDbPassword) {
		this.loginDbPassword = loginDbPassword;
	}
	
	
	
}
