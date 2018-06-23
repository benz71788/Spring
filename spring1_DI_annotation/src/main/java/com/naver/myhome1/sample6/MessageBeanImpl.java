package com.naver.myhome1.sample6;

import java.io.IOException;

import javax.annotation.Resource;

public class MessageBeanImpl implements MessageBean{

	private String name;
	private String greeting;
	private Outputter outputter;
	
	//생성자 호출을 통하여 name="Spring" 할당합니다.
	public MessageBeanImpl(String name) {
		this.name = name;
		System.out.println("1. MessageBeanImpl 생성자 입니다.");
	}

	@Override
	public void sayHello() {
		// TODO Auto-generated method stub
		String message = greeting + name + "!";
		System.out.println("6. MessageBeanImpl의 sayHello() 호출:"  + message);
		
		try {
			outputter.output(message);
		} catch(IOException ie) {
			ie.printStackTrace();
		}
	}
	
	//setGreeting메소드 호출을 통하여 greeting="안녕하세요!" 할당합니다.
	public void setGreeting(String greeting) {
		this.greeting = greeting;
		System.out.println("5. MessageBeanImpl의 setGreeting()에서 값 저장");
	}	//setter DI 설정(스프링 용어)
	
	//추가된 부분 - name속성이 주입하고자 하는 의존 객체의 이름(아이디 값)을 지정합니다.
	@Resource(name="outputter")	// 확실하게 원하는 부위만 지정해줍니다.
	public void setOutputter(Outputter outputter) {
		this.outputter = outputter;
		System.out.println("4. MessageBeanImpl의 setOutputter()에서 값 저장");
	}	//setter DI 설정(스프링 용어)

}
