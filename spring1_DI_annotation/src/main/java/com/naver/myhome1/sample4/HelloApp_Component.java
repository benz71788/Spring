package com.naver.myhome1.sample4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp_Component {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = 
				new GenericXmlApplicationContext(
						"com/naver/myhome1/sample4/applicationContext.xml");
		
		MessageMultiple multi = (MessageMultiple) ctx.getBean("multi");
		multi.print();
		
		ctx.close();
		
	}
}
