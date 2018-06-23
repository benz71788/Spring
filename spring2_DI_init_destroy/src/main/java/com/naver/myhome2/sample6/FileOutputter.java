package com.naver.myhome2.sample6;

import java.io.FileWriter;
import java.io.IOException;

import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component("outputter")
public class FileOutputter implements Outputter {
	private String filePath;	//출력파일 경로와 파일이름을 저장할 변수
	
	public FileOutputter() {
		System.out.println("2. 여기는 FileOutputter 생성자입니다.");
	}
	
	@Override
	public void output(String message) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("7. 여기는 FileOutputter.java의 output()입니다.");
		FileWriter out = new FileWriter(filePath);
		out.write(message);	//메시지를 기록함
		out.close();	//출력 객체를 닫음
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
		System.out.println("3. 여기는 FileOutputter.java의 setFilePath() 입니다.");
	}	//스프링에서 setter메서드를 활용한 setter DI설정
	
	@PreDestroy	//destory-method="destroyMethod"에 해당하는 annotation
	public void destroyMethod() {
		System.out.println("FileOutputter의 destroyMethod()입니다.");
	}
	
}
