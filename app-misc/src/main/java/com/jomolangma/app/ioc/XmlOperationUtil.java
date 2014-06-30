package com.jomolangma.app.ioc;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlOperationUtil {
	
	public static Document getDocument(File file){
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Document getDocument(String xmlStr){
		try {
			InputStream inputStream = new ByteArrayInputStream(xmlStr.getBytes("utf-8"));
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
    public static String getDocumentString(Document document){
        String resultString = "";
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            StringWriter write = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(write));
            resultString = write.toString();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return resultString;
    }
    
	public static List<Node> searchNodeByXPath(Document document,String xPathExpr) {
		List<Node> nodeList = new ArrayList<Node>();
		if (document != null) {
			XPath xpath = XPathFactory.newInstance().newXPath();
			try {
				XPathExpression expr = xpath.compile(xPathExpr);
				NodeList nodes = (NodeList) expr.evaluate(document,
						XPathConstants.NODESET);
				if (nodes != null) {
					for (int i = 0; i < nodes.getLength(); i++)
						nodeList.add(nodes.item(i));
					return nodeList;
				}

			} catch (XPathExpressionException e) {
				throw new RuntimeException(e);
			}
		}
		return nodeList;
	}
	
	public static Node searchFirstNodeByXPath(Document document,String xPathExpr) {
		List<Node> nodeList = searchNodeByXPath(document,xPathExpr);
		return nodeList.isEmpty() ? null : nodeList.get(0);
	}
    
    public static Element generateElement(Document document,String elementName){
    	return document.createElement(elementName);
    }
    
    
    public static String getSimpleDateStr(Date date){
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    	if (date == null)
			date = new Date();
    	
    	return df.format(date);
    }
    
    public static Date getDateFromStr(String dateStr){
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    	if (dateStr == null)
			return new Date();
    	
    	try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return new Date();
    }
    
    public static Date getDateFromStrWithNoSecond(String dateStr){
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
    	if (dateStr == null)
			return new Date();
    	
    	try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return new Date();
    }
    
    public static Date getDateFromStrWithNoTime(String dateStr){
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
    	if (dateStr == null)
			return new Date();
    	
    	try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return new Date();
    }
    
    public static String getMediumDateStr(Date date){
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	if (date == null)
			date = new Date();
    	
    	return df.format(date);
    }   
    
    public static void main(String[] args){
    	String filePath = "D:/workspace/Jomolangma/app-misc/src/main/resources/ORDER_CANCEL_AND_STOP_LIST_INFO_V1.24.xml";
    	File file = new File(filePath);
    	String xmlStr = getDocumentString(getDocument(file));
    	
    	System.out.println(xmlStr);
    }
}
