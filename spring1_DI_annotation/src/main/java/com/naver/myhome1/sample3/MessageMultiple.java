package com.naver.myhome1.sample3;


import javax.annotation.Resource;

/*
 * @Qualifier("m1") 생략한 경우 오류 발생
 * 	
 * 	Error creating bean with name 'm2' : Unsatisfied dependency expressed throw
 * 	nested exception is org.springframework.beans.factory.NoUniqueBeanDefinition
 *	No qualifying bean of type 'com.naver.myhome1.sample2.MessageBean'
 *	available: expected single matching bean but found 2: m, m1
 * 
 * 	MessageBean 타입의 빈이 m과 m1 두 개가 존재해서 어떤 객체를 주입할지 모르기 때문에
 * 	발생하는 예외입니다.
 * 
 * 	@Qualifier annotation을 이용해서 주입될 객체의 아이디나 이름을 지정할 수 있습니다.
 * 
 * 	@Autowired와 @Qualifier을 한 번에 처리할 수 있는 것이 @Resource입니다.
 */
public class MessageMultiple {
	
	@Resource(name="m")
	//해당 타입에 할당할 수 있는 빈 객체를 찾아서 자동 주입됩니다.
	//반드시 주입할 의존 객체가 존재해야 합니다.
	private MessageBean messageBean;
	
	public MessageMultiple() {
		System.out.println("MessageMultiple 생성자입니다.");
	}
	
	
	public void print() {
		messageBean.sayHello("Spring");
	}
}
