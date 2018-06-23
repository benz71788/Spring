package com.naver.myhome1.sample7;

import java.util.Iterator;
import java.util.Set;

import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx 
			= new GenericXmlApplicationContext(
					"com/naver/myhome1/sample7/applicationContext.xml");
		
//		CollectionBean bean = (CollectionBean)ctx.getBean("collectionBean");
		CollectionBean bean = ctx.getBean("collectionBean", CollectionBean.class);
		
		
		Set<String> set = bean.getAddressList();
		
//		for(int i = 0; i < set.size(); i++) {
//		System.out.println(set.get(i));
//	}

		Iterator<String> it = set.iterator();
		
		//Set 컬렉션은 같은 데이터를 중복해서 저장하지 않습니다.
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		ctx.close();
		
	}
}
