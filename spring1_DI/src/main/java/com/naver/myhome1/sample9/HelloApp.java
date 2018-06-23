package com.naver.myhome1.sample9;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx
			= new GenericXmlApplicationContext(
					"com/naver/myhome1/sample9/applicationContext.xml");
		
		CollectionBean bean = ctx.getBean("collectionBean", CollectionBean.class);
		
		Properties prop = bean.getAddressList();
		
		//Properties에서 사용된 키값들을 가져옵니다.
		Set<String> keys = prop.stringPropertyNames();
		
		for(String key : keys) {
			System.out.println(key + " : " + prop.getProperty(key));
		}
		
		System.out.println("======================");
		
		Iterator<Object> keySet = prop.keySet().iterator();
		
		while(keySet.hasNext()) {
			Object value = keySet.next();
			System.out.println(value + " : " + prop.get(value));
		}
	}
}
