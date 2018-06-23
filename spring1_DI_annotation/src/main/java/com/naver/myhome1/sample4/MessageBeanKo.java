package com.naver.myhome1.sample4;

import org.springframework.stereotype.Component;

@Component("ko")
public class MessageBeanKo implements MessageBean{
	
	public MessageBeanKo() {
		System.out.println("이곳은 MessageBeanKo 생성자입니다.");
	}

	@Override
	public void sayHello(String name) {
		// TODO Auto-generated method stub
		System.out.println("안녕하세요! " + name);
	}

}
