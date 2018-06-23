package com.naver.myhome1.sample8;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx 
			= new GenericXmlApplicationContext(
					"com/naver/myhome1/sample8/applicationContext.xml");
		
//		CollectionBean bean = (CollectionBean)ctx.getBean("collectionBean");
		CollectionBean bean = ctx.getBean("collectionBean", CollectionBean.class);
		
		Map<String, Object> map = bean.getAddressList();
		
		Iterator<String> key = map.keySet().iterator();
		
		while(key.hasNext()) {
			Object value = key.next();
			System.out.println(value + " : " + map.get(value));
		}
		
		System.out.println("==================================");
		
		Set<String> keys = map.keySet();
		for(String k : keys) {
			System.out.println(k + " : " + map.get(k));
		}
	}
}
