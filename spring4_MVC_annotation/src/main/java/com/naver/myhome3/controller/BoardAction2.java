package com.naver.myhome3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
public class BoardAction2 {
	
	/*
	 * @RequestMapping 애노테이션을 사용해서 메서드에 처리할 요청 경로를 지정합니다.
	 * 	value="요청 경로", method는 전송 방식 지정합니다.
	 * @RequestMapping 뒤에는 반드시 메서드가 나와야 하고 자동 호출 됩니다.
	 * 이름은 다른 메서드들과 중복되지 않게만 작성합니다.
	 * get방식 전송의 경우 method=RequestMethod.GET 이고 생략 가능합니다.
	 * post방식과 get방식을 모두 적용할 경우에는
	 * method={RequestMethod.POST, RequestMethod.GET}라고 작성합니다.
	 */
	@RequestMapping(value="/login_ok2.do", method=RequestMethod.POST)
	public String board_write(BbsBean bbs //command 객체
			) throws Exception {
		
		/*
		 * command 객체란?
		 * 스프링은 요청 파라미터의 값을 command 객체에 담아주는 기능을 제공합니다.
		 * HTTP 요청 파라미터 값을 전달 받을 때 사용되는 객체입니다.
		 * command 객체로 사용될 클래스에 제한은 없으며 자바빈 규칙에 맞춰 알맞은
		 * setter 메서드만 제공하면 됩니다.
		 * command 객체에는 파라미터로 넘어온 값들을 저장할 setter 메서드가 존재하면 됩니다.
		 * => 파라미터의 이름과 property의 이름이 같으면 됩니다.
		 * 예로 loginForm.jsp에서 <input type=text name=id>에서 입력한 값을 저장하기
		 * 위해 setId()메서드가 존재하면 됩니다.
		 * 
		 * String id = request.getParameter("id");
		 * String pass = request.getParameter("pass");
		 * 
		 * BbsBean bbs = new BbsBean();
		 * bbs.setId(id);
		 * bbs.setPass(pass);
		 * 
		 * command 객체를 사용하면 이전에 사용되었던 위의 코드를 기능을 하게 되어 필요 없게 됩니다.
		 * 
		 * command 객체에 저장된 값을 JSP에서 사용하기 위해서는
		 * ${bbsBean.id} 또는 ${bbsBean.pass}로 사용합니다.
		 * 즉, list2.jsp에서
		 * command 객체에 클래스 이름(첫 글자를 소문자로 바꿉니다.)과
		 * 동일한 속성 이름을 사용해서 command 객체를 뷰에 전달합니다.
		 * 예로 command 객체에 클래스 이름이 BbsBean인 경우 JSP 코드에서는
		 * bbsBean이라는 이름을 사용해서 command 객체에 접근할 수 있게 됩니다.
		 */
		
		return "list2";
	}
}
