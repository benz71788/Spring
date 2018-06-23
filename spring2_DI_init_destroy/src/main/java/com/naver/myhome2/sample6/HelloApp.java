package com.naver.myhome2.sample6;

import java.io.IOException;

import org.springframework.context.support.GenericXmlApplicationContext;


public class HelloApp {
	public static void main(String[] args) throws IOException {
		
		GenericXmlApplicationContext ctx
			= new GenericXmlApplicationContext(
					"com/naver/myhome2/sample6/applicationContext.xml");
		
//		MessageBean bean = (MessageBean) ctx.getBean("impl");
		MessageBeanImpl bean = ctx.getBean("impl", MessageBeanImpl.class);
		
		bean.setGreeting("안녕하세요~");
		
//		FileOutputter outtputer = (FileOutputter) ctx.getBean("outputter");
		FileOutputter outputter = ctx.getBean("outputter", FileOutputter.class);
		
		outputter.setFilePath("out6.txt");
		
		System.out.println("\n=====   main에서 sayHello 호출합니다.   =====");
		
		bean.sayHello();	//메서드를 호출합니다.
		ctx.close();
	}
}
