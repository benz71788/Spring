package com.naver.myhome1.sample5;

import java.io.IOException;

import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp2 {
	public static void main(String[] args) throws IOException {
		
		GenericXmlApplicationContext ctx
			= new GenericXmlApplicationContext(
					"com/naver/myhome1/sample5/applicationContext.xml");
		
		// Object getBean(String name) :
		//			argument로 지정된 이름의 bean 인스턴스를 반환합니다.
		// applicationContext.xml에서 id가 "m3"인 bean을 가져옵니다.
		MessageBean bean = (MessageBean) ctx.getBean("m3");
		
		bean.sayHello();
		ctx.close();
	}
}
