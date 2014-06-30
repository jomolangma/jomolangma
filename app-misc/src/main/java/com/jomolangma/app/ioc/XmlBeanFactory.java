package com.jomolangma.app.ioc;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Document;

public class XmlBeanFactory implements BeanFactory {
	
	public HashMap<String,BeanDefinition> beanDefinitionMap = new HashMap<String,BeanDefinition>();
	public HashMap <String,Object> singletonObjects = new HashMap <String,Object>();

	
	public XmlBeanFactory(String filePath){
		File file = new File(filePath);
		Document document = XmlOperationUtil.getDocument(file);
		
		BeanDefinitionParser beanDefinitionParser = new BeanDefinitionParser(this,document);
		beanDefinitionParser.parse();
	}

	@Override
	public Object getBean(String name) {
		Object bean = null;
		bean = singletonObjects.get(name);
		
		if(bean == null){
			BeanDefinition beanDefinition = beanDefinitionMap.get(name);
			
			if (beanDefinition != null){
				String className = beanDefinition.getClassName();
				Class beanClass = null;
				Object object = null;
				try {
					beanClass = Class.forName(className);
					object = beanClass.newInstance();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//开始设置Property属性
				Map<String,Object> property = beanDefinition.getProperties();
				if (!property.isEmpty()){
					Set<String> propertyKeys = property.keySet();
					for (String key:propertyKeys){
						Object propertyValue = property.get(key);
						String mehodName = "set" + Character.toUpperCase(key.charAt(0)) + key.substring(1);
						
						if (propertyValue instanceof StringValueProperty){
							String valueName = ((StringValueProperty) propertyValue).getValue();
							try {
								Method invokeMethod = beanClass.getMethod(mehodName,String.class);
								invokeMethod.setAccessible(true);
								try {
									invokeMethod.invoke(object, valueName);
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} catch (NoSuchMethodException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						} else if (propertyValue instanceof RefValueProperty){
							String refName = ((RefValueProperty) propertyValue).getValue();
							Object refObject = this.getBean(refName);
							
							try {
								Method invokeMethod = beanClass.getMethod(mehodName,refObject.getClass());
								invokeMethod.setAccessible(true);
								try {
									invokeMethod.invoke(object, refObject);
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} catch (NoSuchMethodException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
					}
				}
				
				this.singletonObjects.put(name, object);
				return object;
			}
		}
		return bean;
	}
	
}
