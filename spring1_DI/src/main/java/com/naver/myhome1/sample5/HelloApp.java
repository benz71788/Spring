package com.naver.myhome1.sample5;

import java.io.IOException;

public class HelloApp {
	public static void main(String[] args) throws IOException {
		
		MessageBeanImpl beanImpl = new MessageBeanImpl("Spring");
		FileOutputter fileOutputter = new FileOutputter();
		fileOutputter.setFilePath("out.txt");
		
		beanImpl.setGreeting("안녕하세요~");
		beanImpl.setOutputter(fileOutputter);
		
		beanImpl.sayHello();
	}
}
