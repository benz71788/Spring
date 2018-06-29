package com.naver.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor_pre extends HandlerInterceptorAdapter{
	
	/*
	 * 요청 URL이 다음과 같은 경우 요청을 처리하기 전에 아래의 메서드를 수행합니다.
	 * <mapping path="/*.nhn"/>
	 * false를 리턴하면 컨트롤러를 실행하지 않습니다.
	 * true를 리턴하면 컨트롤러를 실행합니다.
	 */
	

	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println("preHandle(); : " + request.getRequestURL() + "요청 중");
			//id라는 속성을 가진 값이 null일 경우 로그인페이지로 이동
			if(request.getSession().getAttribute("id") == null) {
				response.sendRedirect("login.net");
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//널이 아니면(세션에 아이디가 있는 경우) 정상적으로 컨트롤러 호출
		return true;
	}

}
