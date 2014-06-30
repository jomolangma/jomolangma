package com.jomolangma.app.spring.lifecycle;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class BeanLifeCycle {
	private static void LifeCycleInBeanFactory() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		ResourceLoader resourceLoader = new PathMatchingResourcePatternResolver();
		Resource resource = resourceLoader.getResource("classpath:beans.xml");
		XmlBeanDefinitionReader xmlDefinitionReader = new XmlBeanDefinitionReader(bf);
		xmlDefinitionReader.loadBeanDefinitions(resource);

		(new MyBeanFactoryPostProcessor()).postProcessBeanFactory(bf);
		// InstantiationAwareBeanPostProcessor
		((ConfigurableBeanFactory) bf).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
		// BeanPostProcessor
		((ConfigurableBeanFactory) bf).addBeanPostProcessor(new MyBeanPostProcessor());
		
		Car car1 = (Car) bf.getBean("car");
		car1.introduce();
		car1.setColor("hongse");
		Car car2 = bf.getBean("car", Car.class);
		car2.introduce();
		bf.destroySingletons();
	}

	public static void main(String[] args) {
		LifeCycleInBeanFactory();
	}
}
