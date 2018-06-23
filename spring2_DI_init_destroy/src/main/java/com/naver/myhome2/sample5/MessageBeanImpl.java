package com.naver.myhome2.sample5;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

public class MessageBeanImpl implements MessageBean{

	private String name;
	private String greeting;
	private Outputter outputter;
	
	//객체를 생성한 훙 init-method 속성으로 지정된 이 메서드를 호출합니다.
	public void initMethod() {
		System.out.println("   MessageBeanImpl의 init-method()을 실행합니다.   ");
	}
	
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
		System.out.println("4. MessageBeanImpl의 setGreeting()에서 값 저장");
	}	//setter DI 설정(스프링 용어)
	
	//추가된 부분
	@Autowired	//해당 타입에 할당할 수 있는 빈 객체를 찾아서 자동 주입됩니다.
				//반드시 주입할 의존 객체가 존재해야 합니다.
	public void setOutputter(Outputter outputter) {
		this.outputter = outputter;
		System.out.println("5. MessageBeanImpl의 setOutputter()에서 값 저장");
	}	//setter DI 설정(스프링 용어)

}
