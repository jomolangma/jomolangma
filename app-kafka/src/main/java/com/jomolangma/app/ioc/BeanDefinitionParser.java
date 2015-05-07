package com.jomolangma.app.ioc;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BeanDefinitionParser {
	private XmlBeanFactory xmlBeanFactory;
	private Document document;
	
	public BeanDefinitionParser(XmlBeanFactory xmlBeanFactory,Document document){
		this.xmlBeanFactory = xmlBeanFactory;
		this.document = document;
	}

	public void parse(){
		Node node = XmlOperationUtil.searchFirstNodeByXPath(document,"/beans");
		NodeList nodeList = node.getChildNodes();
		int beanCount = nodeList.getLength();
		
		for (int i=0;i<beanCount;i++){
			node = nodeList.item(i);
			
			if (node instanceof Element){
				if ("bean".equals(node.getNodeName()))
					parseBeanNode(node);
			}
		}
	}
	
	private void parseBeanNode(Node node){
		BeanDefinition beanDefinition = new BeanDefinition();

		NamedNodeMap namedNodeMap = node.getAttributes();
		Node attrNode = namedNodeMap.getNamedItem("id");
		String id = attrNode.getNodeValue();
	
		attrNode = namedNodeMap.getNamedItem("class");
		String className = attrNode.getNodeValue();
		
		beanDefinition.setId(id);
		beanDefinition.setClassName(className);
		
		this.xmlBeanFactory.beanDefinitionMap.put(id, beanDefinition);
		
		if (node.getChildNodes() != null && node.getChildNodes().getLength() > 0 )
			parsePropertyValue (node,beanDefinition);
	}
	
	private void parsePropertyValue (Node node,BeanDefinition beanDefinition){
		NodeList propertyNodeList = node.getChildNodes();
		int propertyCount = propertyNodeList.getLength();
		
		Map<String,Object> properties = new HashMap<String,Object>();
		
		for (int i=0;i<propertyCount;i++){
			node = propertyNodeList.item(i);
			
			if (node instanceof Element){
				if ("property".equals(node.getNodeName())){
					NamedNodeMap namedNodeMap = node.getAttributes();
					
					Node nameNode = namedNodeMap.getNamedItem("name");
					Node valueNode = namedNodeMap.getNamedItem("value");
					Node refNode = namedNodeMap.getNamedItem("ref");
					
					if (nameNode != null && valueNode != null){
						StringValueProperty property = new StringValueProperty();
						String propertyName = nameNode.getNodeValue();
						property.setName(propertyName);
						property.setValue(valueNode.getNodeValue());
						properties.put(propertyName, property);
					} else if (nameNode != null && refNode != null){
						RefValueProperty property = new RefValueProperty();
						String propertyName = nameNode.getNodeValue();
						property.setName(propertyName);
						property.setValue(refNode.getNodeValue());
						properties.put(propertyName, property);
					}
				}
			}
		}
		beanDefinition.setProperties(properties);
	}
}
