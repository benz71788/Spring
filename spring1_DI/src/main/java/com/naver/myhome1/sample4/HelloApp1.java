package com.naver.myhome1.sample4;

public class HelloApp1 {
	public static void main(String[] args) {

		MessageBeanImpl beanImpl = new MessageBeanImpl("스프링");
		
		beanImpl.setGreeting("안녕~");
		beanImpl.sayHello();
	}
}
