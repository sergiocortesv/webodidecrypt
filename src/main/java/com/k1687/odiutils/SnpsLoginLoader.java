package com.k1687.odiutils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Retrieves simplified SNPLogin objects from a ODI SnpsLogin data from a snps_login_work.xml file
 */
public class SnpsLoginLoader{
	
	private XPathExpression exprObject;
	private XPathExpression exprLoginName;
	private XPathExpression exprLoginUser;
	private XPathExpression exprLoginPass;
	private XPathExpression exprLoginDbuser;
	private XPathExpression exprLoginDbpass;
	
	public SnpsLoginLoader(){
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		
		try {
			exprObject = xpath.compile("/SnpsLogin/Object");
			exprLoginName = xpath.compile("Field[@name='LoginName']/text()");
			exprLoginUser = xpath.compile("Field[@name='LoginUser']/text()");
			exprLoginPass = xpath.compile("Field[@name='LoginPass']/text()");
			exprLoginDbuser = xpath.compile("Field[@name='LoginDbuser']/text()");
			exprLoginDbpass = xpath.compile("Field[@name='LoginDbpass']/text()");
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves a list of all SnpsLogin objects from a ODI snps_login_work.xml file input stream 
	 * @param odiSnpInstream snps_login_work.xml file input stream
	 * @return List of SnpsLogin
	 * @throws IOException When the input stream cannot be read
	 */
	public List<SnpLoginSimple> loadSnpLogin(InputStream odiSnpInstream) throws IOException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(odiSnpInstream);
		}catch(ParserConfigurationException ex){
			throw new TechnicalException(ex);
		} catch (SAXException e) {
			throw new TechnicalException(e);
		}
		
		try {
			List<SnpLoginSimple> snpLoginList = new ArrayList<SnpLoginSimple>();
			NodeList nl = (NodeList) exprObject.evaluate(doc, XPathConstants.NODESET);
			for(int i = 0;i < nl.getLength(); i++){
				Node snpObjectNode = nl.item(i);
				SnpLoginSimple snpLogin = new SnpLoginSimple();
				snpLogin.setLoginName((String) exprLoginName.evaluate(snpObjectNode, XPathConstants.STRING));
				snpLogin.setLoginUser((String) exprLoginUser.evaluate(snpObjectNode, XPathConstants.STRING));
				snpLogin.setLoginPassword((String) exprLoginPass.evaluate(snpObjectNode, XPathConstants.STRING));
				snpLogin.setLoginDbUser((String) exprLoginDbuser.evaluate(snpObjectNode, XPathConstants.STRING));
				snpLogin.setLoginDbPassword((String) exprLoginDbpass.evaluate(snpObjectNode, XPathConstants.STRING));
				snpLoginList.add(snpLogin);
			}
			return snpLoginList;
		} catch (XPathExpressionException e) {
			throw new TechnicalException(e);
		}
	}
	
}
