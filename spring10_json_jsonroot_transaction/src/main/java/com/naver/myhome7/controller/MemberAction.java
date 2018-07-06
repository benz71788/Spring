package com.naver.myhome7.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.naver.myhome7.dao.MemberService;
import com.naver.myhome7.model.MemberBean;
import com.naver.myhome7.model.ZipcodeBean;
import com.naver.myhome7.model.ZipcodeBean2;

@Controller
public class MemberAction {
	
	@Autowired
	private MemberService memberService;
	
	//배포시 필요한 경로
//	private String saveFolder = "C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/myhome7/resources/upload";
	private String saveFolder = "D:\\workspace-sts\\Spring\\spring7_MVC_member\\src\\main\\webapp\\resources\\upload";
	
	
	/*
	 * 수정 부분
	 * 	@CookieValue(value="id", required=false) Cookie readCookie
	 * 	이름이 saveid인 쿠키를 Cookie 타입으로 전달받습니다.
	 * 	지정한 이름의 쿠키가 존재하지 않을 수도 있기 때문에 required=false로 설정해야 합니다.
	 * 	즉, id 기억하기를 선택하지 않을 수도 있기 때문에 required=false로 설정해야 합니다.
	 * 	required=true 상태에서 지정한 이름을 가진 쿠키가 존재하지 않으면 스프링 MVC는 익셉션을 발생시킵니다.
	 */
	
	/*로그인 폼 뷰*/
	@RequestMapping(value="/member_login.nhn")
	public ModelAndView member_login(
			ModelAndView mv,
			@CookieValue(value="saveid", required=false) Cookie readCookie) throws Exception{
		
		if(readCookie != null) {
			mv.addObject("saveid", readCookie.getValue());
		}
		mv.setViewName("member/member_login");
		
		return mv;
		//member 폴더의 member_login.jsp 뷰 페이지 실행
	}
	
	@RequestMapping(value="/member_login_ok.nhn", 
			method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView member_login_ok(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		//HttpSession 플래스는 세션객체를 생성해서 로그인 인증 처리를 하기 위해서입니다.
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id").trim();
		String pwd = request.getParameter("pwd").trim();
		
		MemberBean m = this.memberService.userCheck(id);
		
		if(m == null) {	//등록되지 않은 회원일때
			out.println("<script>");
			out.println("alert('등록되지 않은 회원입니다.');");
			out.println("history.back()");
			out.println("</script>");
		} else {
			if(m.getJoin_pwd().equals(pwd)) {
				session.setAttribute("id", id);
				
				String join_name = m.getJoin_name();
				String join_file = m.getJoin_file();
				session.setAttribute("join_name", join_name);
				session.setAttribute("join_file", join_file);
				
				//"saveid"라는 이름의 쿠기에 id의 값을 저장한 쿠키를 생성합니다.
				Cookie savecookie = new Cookie("saveid", id);
				if(request.getParameter("saveid") != null) {
					savecookie.setMaxAge(60 * 60);
				} else {
					System.out.println("쿠키저장 : 0");
					savecookie.setMaxAge(0);
				}
				response.addCookie(savecookie);
				
				//jsp폴더의 view.jsp로 이동
				ModelAndView loginM = new ModelAndView("view");
				return loginM;
			} else {	//비번이 다를때
				out.println("<script>");
				out.println("alert('비번이 다릅니다.!')");
				out.println("history.go(-1)");
				out.println("</script>");
			}
		}
		
		return null;
	}
	
	@RequestMapping(value="/member_join.nhn")
	public String member_join(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		return "member/member_join";
		//member 폴더의 member_login.jsp 뷰 페이지 실행
	}
	
	@RequestMapping(value="/member_join_ok.nhn", method=RequestMethod.POST)
	public void member_join_ok(
			MemberBean m,
			HttpServletResponse response) throws Exception{
		
		String join_tel = m.getJoin_tel1() + "-" + m.getJoin_tel2() + "-" + m.getJoin_tel3();
		String join_phone = m.getJoin_phone1() + "-" + m.getJoin_phone2() + "-" + m.getJoin_phone3();
		
		//메일 아이디 : m.getJoin_mailid();
		//메일 도메인 : m.getJoin_maildomain();
		String join_email = m.getJoin_mailid() + "@" + m.getJoin_maildomain();
		m.setJoin_tel(join_tel);
		m.setJoin_phone(join_phone);
		m.setJoin_email(join_email);
		
		MultipartFile UpFile = m.getJoin_profile();
		
		if(!UpFile.isEmpty()) {
			String fileName = UpFile.getOriginalFilename();
			m.setJoin_original(fileName);
			Calendar c = Calendar.getInstance();	//추상클래스로서 년월일 시분초 반환
			int year = c.get(Calendar.YEAR);	//년도 값
			int month = c.get(Calendar.MONTH) + 1;	//월값.+1을 한 이유는 1월이 0
			int date = c.get(Calendar.DATE);
			
			String homedir = saveFolder + "/" + year + "-" + month + "-" + date; //새로운 폴더 저장
			File path1 = new File(homedir);
			if(!(path1.exists())) {
				System.out.println("폴더 만들어요");
				path1.mkdirs();	//새로운 폴더를 생성
				
			}
			
			//난수를 구합니다.
			Random r = new Random();
			int random = r.nextInt(100000000);
			
			/***확장자 구하기 시작***/
			int index = fileName.lastIndexOf(".");
			/*
			 * 문자열에서 특정 문자열의 위치 값(index)를 반환한다.
			 * indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
			 * lastIndexOf는 마지막으로 발견되는 문자열의 index를 반환합니다.
			 * (파일명에 점이 여러개 있을 경우 맨 마지막에 발견되는 문자열의 위치를 리턴합니다.)
			 */
			System.out.println("index = " + index);
			
			String fileExtension = fileName.substring(index + 1);
			System.out.println("fileExtension = " + fileExtension);
			/***확장자 구하기 끝***/
			
			//새로운 파일명을 저장
			String refileName = "bbs" + year + month + date + random + "."
					+ fileExtension;
			System.out.println("refileName = " + refileName);
			
			//오라클 디비에 저장될 레코드 값
			String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
			System.out.println("fileDBName = " + fileDBName);
			
			//transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
			UpFile.transferTo(new File(saveFolder + fileDBName));
			//바뀐 파일명으로 저장
			m.setJoin_file(fileDBName);
		}
		
		this.memberService.insertMember(m);
		
		//로그인 페이지로 이동
		response.sendRedirect("member_login.nhn");
	}
	
	@RequestMapping(value="/member_idcheck.nhn", method=RequestMethod.POST)
	public void member_idcheck(
			@RequestParam(value = "join_id") String id,
			HttpServletResponse response) throws Exception{
		
		PrintWriter out = response.getWriter();
		int result = memberService.checkMemberId(id);
		out.print(result);
	}
	
	@RequestMapping(value="/zipcode_find.nhn")
	public String zipcode_find() {
		return "member/zipcode_find";
		//member 폴더의 zipcode_find.jsp 뷰 페이지 실행
	}
	
	@RequestMapping(value="/zipcode_find_ok.nhn", method=RequestMethod.POST)
	@ResponseBody
	public Object zipcode_find_ok(
			@RequestParam String dong) throws Exception {
		
		System.out.println(dong);
		
		List<ZipcodeBean2> zipcodeList = new ArrayList<ZipcodeBean2>();
		
		//동을 기준으로 주소를 검색해서 컬렉션에 저장합니다.
		zipcodeList = this.memberService.findZipcode("%" + dong + "%");
		
		List<ZipcodeBean> zipcodeList2 = new ArrayList<ZipcodeBean>();
		
		for(int i = 0; i < zipcodeList.size(); i++) {
			ZipcodeBean2 zipcode_addr = zipcodeList.get(i);
			
			String zipcode = zipcode_addr.getZipcode();	//우편번호 저장
			String sido = zipcode_addr.getSido();		//서울시, 경기도 저장
			String gungu = zipcode_addr.getSigungu();	//군, 구
			String doro = zipcode_addr.getDoro();		//도로
			String addr = sido + " " + gungu + " " + doro;	//서울시 강남구 당남대로
			
			ZipcodeBean zip = new ZipcodeBean();
			zip.setZipcode(zipcode);
			zip.setAddr(addr);
			
			//컬렉션에 주소를 저장합니다.
			zipcodeList2.add(zip);
		}
		return zipcodeList2;
	}
	
	@RequestMapping(value="/member_edit.nhn")
	public ModelAndView member_edit(
			HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		
		//아아디 키값의 세션아이디를 구함
		String id = (String)session.getAttribute("id");
		
		//interceptor로 처리
		//
		/* response.setContentType("text/html;charset=UTF-8");
		 * PrintWriter out = response.getWriter();
		 * if(id == null){
		 * 		out.println("<script>");
		 * 		out.println("alert('다시 로그인 해주세요!')");
		 * 		out.println("location='member_login.nhn'");
		 * 		out.println("</script>");
		 * }
		 */
		MemberBean bean = memberService.userCheck(id);
		
		bean.setJoin_tel1(bean.getJoin_tel().split("-")[0]);
		bean.setJoin_tel2(bean.getJoin_tel().split("-")[1]);
		bean.setJoin_tel3(bean.getJoin_tel().split("-")[2]);
		
//		String join_tel = bean.getJoin_tel();
//		//java.util 패키지의 StringTokenizer클래스는 첫번째 전달인자를
//		//두번째 -를 기준으로 문자열을 파싱해줍니다.
//		//집전화 번호 저장
//		StringTokenizer st01 = new StringTokenizer(join_tel, "-");
//		String join_tel1 = st01.nextToken();	//첫번째(국번 집전화번호 저장)
//		String join_tel2 = st01.nextToken();	//두번째(가운데 자리)
//		String join_tel3 = st01.nextToken();	//세번째(뒷 자리)
		
		bean.setJoin_phone1(bean.getJoin_phone().split("-")[0]);
		bean.setJoin_phone2(bean.getJoin_phone().split("-")[1]);
		bean.setJoin_phone3(bean.getJoin_phone().split("-")[2]);
		
//		String join_phone = bean.getJoin_phone();
//		//java.util 패키지의 StringTokenizer클래스는 첫번째 전달인자를
//		//두번째 -를 기준으로 문자열을 파싱해줍니다.
//		//휴대폰 번호 저장
//		StringTokenizer st02 = new StringTokenizer(join_phone, "-");
//		String join_phone1 = st02.nextToken();
//		String join_phone2 = st02.nextToken();
//		String join_phone3 = st02.nextToken();
		
		bean.setJoin_mailid(bean.getJoin_email().split("@")[0]);
		bean.setJoin_maildomain(bean.getJoin_email().split("@")[1]);
		
//		String join_email = bean.getJoin_email();
//		//java.util 패키지의 StringTokenizer클래스는 첫번째 전달인자를
//		//두번째 @를 기준으로 문자열을 파싱해줍니다.
//		StringTokenizer st03 = new StringTokenizer(join_email, "@");
//		String join_mailid = st03.nextToken();
//		String join_maildomain = st03.nextToken();
		
		ModelAndView model = new ModelAndView("member/member_edit");
		
		model.addObject("member", bean);
		
//		model.addObject("join_tel1", join_tel1);
//		model.addObject("join_tel2", join_tel2);
//		model.addObject("join_tel3", join_tel3);
//		
//		model.addObject("join_phone1", join_phone1);
//		model.addObject("join_phone2", join_phone2);
//		model.addObject("join_phone3", join_phone3);
//		
//		model.addObject("join_mailid", join_mailid);
//		model.addObject("join_maildomain", join_maildomain);
		
		return model;
	}
	
	@RequestMapping(value="/member_edit_ok.nhn", method=RequestMethod.POST)
	public ModelAndView member_edit_ok(MemberBean m,
			HttpServletResponse response,
			HttpServletRequest request) throws Exception{
		System.out.println("hi");
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		
		
		String join_tel = m.getJoin_tel1() + "-" + m.getJoin_tel2() + "-" + m.getJoin_tel3();
		String join_phone = m.getJoin_phone1() + "-" + m.getJoin_phone2() + "-" + m.getJoin_phone3();
		
		//메일 아이디 : m.getJoin_mailid();
		//메일 도메인 : m.getJoin_maildomain();
		String join_email = m.getJoin_mailid() + "@" + m.getJoin_maildomain();
		
		m.setJoin_tel(join_tel);
		m.setJoin_phone(join_phone);
		m.setJoin_email(join_email);
		m.setJoin_id(id);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		MemberBean bean = memberService.userCheck(m.getJoin_id());
		MultipartFile upFile = m.getJoin_profile();
		
		String fileName1 = upFile.getOriginalFilename();
		System.out.println("hihi" + fileName1 + "hihi");
		if(!upFile.isEmpty()) {
			File delFile = new File(saveFolder + bean.getJoin_file());
			if(delFile.exists()) {
				delFile.delete();
			}
			//원래 파일명 구해오기
			String fileName = upFile.getOriginalFilename();
			System.out.println(fileName);
			
			m.setJoin_original(fileName);
			
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int date = c.get(Calendar.DATE);
			String homedir = saveFolder + "/" + year + "-" + month + "-" + date;
			
			//파일 객체를 생성합니다.
			File path1 = new File(homedir);
			
			//폴더가 존재하는 지 확인합니다.
			if(!(path1.exists())) {
				System.out.println("폴더 만들어요");
				path1.mkdirs();
			}
			
			//난수를 구합니다.
			Random r = new Random();
			int random = r.nextInt(100000000);
			
			int index = fileName.lastIndexOf(".");
			
			/*
			 * 문자열에서 특정 문자열의 위치 값(index)를 반환한다.
			 * indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
			 * lastIndexOf는 마지막으로 발견되는 문자열의 index를 반환합니다.
			 * (파일명에 점이 여러개 있을 경우 맨 마지막에 발견되는 문자열의 위치를 리턴합니다.)
			 */
			System.out.println("index = " + index);
			
			String fileExtension = fileName.substring(index + 1);
			System.out.println("fileExtension = " + fileExtension);
			
			//새로운 파일명을 저장
			String refileName = "bbs" + year + month +date + random + "."
							+ fileExtension;
			System.out.println("refileName = " + refileName);
			
			//오라클 디비에 저장될 레코드 값
			String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
			System.out.println("fileDBName = " + fileDBName);
			
			//transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
			upFile.transferTo(new File(saveFolder + fileDBName));
			
			//바뀐 파일명으로 저장
			m.setJoin_file(fileDBName);
			
		} else if(m.getJoin_original() != null) {//변경하지 않았지만 이전 파일 그대로인 경우
												//데이터 베이스에 있는 내용 그대로 적용합니다.
			m.setJoin_file(bean.getJoin_file());
		} else if(m.getJoin_original() == null) {//파일 제거 선택한 경우
			File DelFile = new File(saveFolder + bean.getJoin_file());
			if(DelFile.exists()) {	//기존이진파일이 존재하면
				DelFile.delete();	//기존 이진파일 삭제
			}
		}
		
		memberService.updateMember(m);
		
		ModelAndView model = new ModelAndView("view");
		
		model.addObject("join_name", m.getJoin_name());
		model.addObject("join_file", m.getJoin_file());
		model.addObject("state", "edit");
		return model;
	}
	
	@RequestMapping(value="/member_del.nhn")
	public ModelAndView member_del(
			MemberBean bean,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		response.setContentType("text/html;charset=UTF-8");
		//PrintWriter out = response.getWriter();	//출력 스트림 객체 생성
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		MemberBean delm = this.memberService.userCheck(id);
		
		ModelAndView model = new ModelAndView("member/member_del");
		model.addObject("delm", delm);
		
		return model;
	}
	
	
	@RequestMapping(value="/member_del_ok.nhn")
	public void member_del_ok(
			MemberBean bean,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");	
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String id = request.getParameter("join_id").trim();
		String pass = request.getParameter("join_pwd").trim();
		String del_cont = request.getParameter("join_delcont").trim();
		
		MemberBean member = this.memberService.userCheck(id);
		if(!member.getJoin_pwd().equals(pass)) {
			out.println("<script>");
			out.println("alert('비번이 다릅니다.!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			String fname = member.getJoin_file();
			
			if(fname != null) {
				File delFile = new File(saveFolder + fname);
				delFile.delete();
			}
		}
		
		MemberBean delm = new MemberBean();
		delm.setJoin_id(id);
		delm.setJoin_delcont(del_cont);
		
		this.memberService.deleteMember(delm);
		
		session.invalidate();
		
		response.sendRedirect("member_login.nhn");
	}
	
	@RequestMapping(value="/member_logout.nhn")
	public String member_logout() {
		return "member/member_logout";
		//member 폴더의 member_logout.jsp 뷰 페이지 실행
	}
	
	/*
	 * servlet-context.xml에서 지정했기 때문에 아래의 문장을 생략 가능합니다.
	 * <view-controller path="/pwd_find.nhn"
	 * 					view-name="member/pwd_find"/>
	 */
	
	/* 비번찾기 폼 */
	/*
	 * @RequestMapping(value="/pwd_find.nhn")
	 * public String pwd_find(){
	 * 		return "member/pwd_find";
	 * 		//member 폴더의 pwd_fins.jsp 뷰 페이지 실행
	 * }
	 */
	
	@RequestMapping(value="/pwd_find_ok.nhn", method=RequestMethod.POST)
	public ModelAndView pwd_find_ok(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id").trim();
		String name = request.getParameter("name").trim();
		
		Map<String, Object> pm = new HashMap<String, Object>();
		
		//컬렉션 Map에 키와 값 저장합니다.
		pm.put("id", id);
		pm.put("name", name);
		
		MemberBean member = this.memberService.findpwd(pm);
		
		if(member == null) {	//회원 아이디와 이름이 맞지 않는 경우
			out.println("<script>");
			out.println("alert('회원아이디의 이름이 맞지 않습니다!');");
			out.println("history.go(-1)");
			out.println("</script>");
		} else {
			ModelAndView pwdM = new ModelAndView("member/pwd_find");
			pwdM.addObject("pwdok", member.getJoin_pwd());
			return pwdM;
		}
		
		return null;
	}
		
		
	
}
