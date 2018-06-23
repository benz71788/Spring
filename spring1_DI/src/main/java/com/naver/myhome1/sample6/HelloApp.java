package com.naver.myhome1.sample6;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx 
			= new GenericXmlApplicationContext(
					"com/naver/myhome1/sample6/applicationContext.xml");
		
//		CollectionBean bean = (CollectionBean)ctx.getBean("collectionBean");
		CollectionBean bean = ctx.getBean("collectionBean", CollectionBean.class);
		
		List<String> list = bean.getAddressList();
		
//		for(int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
		
		Iterator<String> it = list.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		ctx.close();
		
	}
}
