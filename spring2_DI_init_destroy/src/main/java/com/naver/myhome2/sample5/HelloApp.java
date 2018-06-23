package com.naver.myhome2.sample5;

import java.io.IOException;

import org.springframework.context.support.GenericXmlApplicationContext;


public class HelloApp {
	public static void main(String[] args) throws IOException {
		
		GenericXmlApplicationContext ctx
			= new GenericXmlApplicationContext(
					"com/naver/myhome2/sample5/applicationContext.xml");
		
//		MessageBean bean = (MessageBean) ctx.getBean("m3");
		MessageBean bean = ctx.getBean("m3", MessageBean.class);
		
		System.out.println("\n=====   main에서 sayHello 호출합니다.   =====");
		
		bean.sayHello();	//메서드를 호출합니다.
		ctx.close();
	}
}
