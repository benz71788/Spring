package com.naver.myhome1.sample4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp2 {
	public static void main(String[] args) {
		
		/*
		 * Spring 컨테이너 구동
		 * 환경 설정 파일인 applicationContext.xml을 로딩하여 스프링 컨테이너 중 하나인
		 * ClassPathXmlApplicationContext 객체가 생성되어 스프링 컨테이너가 구동합니다.
		 * 스프링 컨테이너는 applicationContext.xml에 설정대로 빈을 생성합니다.
		 */
		
		GenericXmlApplicationContext ctx
			= new GenericXmlApplicationContext(
					"com/naver/myhome1/sample4/applicationContext.xml");
		
		MessageBeanImpl beanImpl = (MessageBeanImpl) ctx.getBean("m2");

		beanImpl.sayHello();
		
		ctx.close();
	}
}
