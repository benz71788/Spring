package com.naver.myhome1.sample4;

import org.springframework.stereotype.Component;

@Component("en")
public class MessageBeanEn implements MessageBean{
	
	public MessageBeanEn() {
		System.out.println("이곳은 MessageBeanEn 생성자입니다.");
	}

	@Override
	
	public void sayHello(String name) {
		// TODO Auto-generated method stub
		System.out.println("Hello! " + name);
	}

}
