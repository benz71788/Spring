package com.naver.myhome1.sample3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp1 {
	public static void main(String[] args) {
		/*
		 * Spring 컨테이너 구동
		 * 환경 설정 파일인 applicationContext.xml을 로딩하여 스프링 컨테이너 중 하나인
		 * ClassPathXmlApplicationContext 객체가 생성되어 스프링 컨테이너가 구동합니다.
		 * 스프링 컨테이너는 applicationContext.xml에 설정대로 빈을 생성합니다.
		 */
		ClassPathXmlApplicationContext ctx
			= new ClassPathXmlApplicationContext(
					"com/naver/myhome1/sample3/applicationContext.xml");
		
		MessageBean beanEn = (MessageBean) ctx.getBean("m");
		MessageBean beanKo = (MessageBean) ctx.getBean("m1");
		
		beanEn.sayHello("Spring");
		beanKo.sayHello("Spring");
		
		ctx.close();
	}
}
