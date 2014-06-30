package com.jomolangma.app.spring.beanfactory;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class BeanLifeCycle {
	private static void LifeCycleInBeanFactory() {
		ClassPathResource resource = new ClassPathResource("APPLICATION_BEANFACTORY_BEAN.xml");
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions(resource);

		(new MyBeanFactoryPostProcessor()).postProcessBeanFactory(beanFactory);
		// InstantiationAwareBeanPostProcessor
		((ConfigurableBeanFactory) beanFactory).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
		// BeanPostProcessor
		((ConfigurableBeanFactory) beanFactory).addBeanPostProcessor(new MyBeanPostProcessor());

		Car car1 = (Car) beanFactory.getBean("car");
		car1.introduce();
		car1.setColor("Green");
		Car car2 = beanFactory.getBean("car", Car.class);
		car2.introduce();
		beanFactory.destroySingletons();
	}

	public static void main(String[] args) {
		LifeCycleInBeanFactory();
	}
}
