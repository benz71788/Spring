package com.naver.myhome3.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.naver.myhome3.model.BbsBean;

/*
 *	@Controller 애노테이션을 사용한 컨트롤러 클래스를 이용해서 특정 요청 URL을 처리합니다.
 *	@Component를 상속한 @Controller는 @Controller가 붙은 클래스의 객체를 메모리에
 *	생성하는 기능을 제공합니다.
 *	단순히 객체를 생성하는 것에 그치지 않고 DispatcherServlet이 인식하는
 *	Controller객체로 만들어 줍니다.
 *
 *	만약 @Controller를 사욯하지 않으면 spring3_MVC프로젝트의
 *	BbsWriteController.java처럼 handleRequestInternal()메서드를
 *	반드시 재정의하여 작성해야 합니다.
 *	이렇게 구현한 Controller는 스프링 프레임워크가 지향하는
 *	POJO(Plain Old Java Object)스타일의 클래스가 아닙니다.
 *	따라서 Controller를 POJO 스타일의 클래스로 구현하려면
 *	"extends AbstractController"를 제거하고 클래스 위에 @Controller를 선언합니다.
 *	스프링 컨테이너는 @Controller가 선언된 객체를 자동으로 Controller 객체로 인식합니다.
 */
@Controller
public class BoardAction {
	
	/*
	 * @RequestMapping 애노테이션을 사용해서 메서드에 처리할 요청 경로를 지정합니다.
	 * 	value="요청 경로", method는 전송 방식 지정합니다.
	 * @RequestMapping 뒤에는 반드시 메서드가 나와야 하고 자동 호출 됩니다.
	 * 이름은 다른 메서드들과 중복되지 않게만 작성합니다.
	 * get방식 전송의 경우 method=RequestMethod.GET 이고 생략 가능합니다.
	 * post방식과 get방식을 모두 적용할 경우에는
	 * method={RequestMethod.POST, RequestMethod.GET}라고 작성합니다.
	 */
	@RequestMapping(value="/login_ok.do", method=RequestMethod.POST)
	public ModelAndView board_write(HttpServletRequest request) throws Exception {
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		BbsBean bbs = new BbsBean();
		bbs.setId(id);
		bbs.setPass(pass);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("list");
		//두 문장과 같은 의미로 ModelAndView mv = new ModelAndView("list");
		//ViewResolver에 의해 webapp/view/list.jsp를 찾아가게 됩니다.
		
		mv.addObject("bkey", bbs);
		//addObject의 첫 번째 매개변수는 키(Key), 두 번째 매개변수는 값(Value)을 의미합니다.
		//b는 id와 비밀번호가 저장되어 있는 DTO 객체입니다.
		//ModelAndView에 값을 저장한 것은 webapp/view/list.jsp에서 EL로 나타낼수 있습니다.
		//ID : ${bkey.id} <br>
		//비밀번호 : ${bkey.pass} <br>
		
		return mv;
	}
}
