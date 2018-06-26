package com.naver.myhome6.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.naver.myhome6.dao.BbsService;
import com.naver.myhome6.model.BbsBean;

@Controller
public class BbsAction2 {
	
	@Autowired
	private BbsService bbsService;
	
	private String saveFolder=
			"D:\\workspace-sts\\Spring\\spring6_MVC_bbs\\src\\main\\webapp\\resources\\upload";
	
	/*자료실 입력폼*/
	@RequestMapping(value="/bbs_write.nhn")
	public String bbs_write() {
		return "bbs/bbs_write";	//bbs 폴더의 bbs_write.jsp 뷰 페이지 실행
	}
	
	/*
	 * 스프링 컨테이너는 매개변수BbsBean객체를 생성하고
	 * BbsBean객체의 setter매서드들을 호출하여 사용자 입력값을 설정합니다.
	 * 매개변수의 이름과 setter의 property가 일치하면 됩니다.
	 */
	@RequestMapping(value="/bbs_write_ok.nhn", method=RequestMethod.POST)
	public String bbs_write_ok(BbsBean bbsbean) throws Exception {
		
		MultipartFile uploadfile = bbsbean.getUploadfile();
		
		if(!uploadfile.isEmpty()) {
			//원래 파일명 구해오기
			String fileName = uploadfile.getOriginalFilename();
			System.out.println(fileName);
			//원래 파일명 저장
			bbsbean.setBbs_original(fileName);
			
			//생성할 폴더 이름 : 오늘 년 + 월 + 일
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int date = c.get(Calendar.DATE);
			String homedir = saveFolder + "/" + year + "-" + month + "-" + date;
			
			//파일 객체를 생성합니다.
			File path1 = new File(homedir);
			
			//폴더가 존재하는지 확인합니다.
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
			uploadfile.transferTo(new File(saveFolder + fileDBName));
			//바뀐 파일명으로 저장
			bbsbean.setBbs_file(fileDBName);
		}
		
		this.bbsService.insertBbs(bbsbean);
		
		return "redirect:bbs_list.nhn";
	}
	
	@RequestMapping(value="/bbs_list.nhn")
	public ModelAndView bbs_list(
			@RequestParam(value="page", defaultValue="1") int page) throws Exception {
		
		List<BbsBean> bbslist = new ArrayList<BbsBean>();
		
		int limit = 10;	// 한 화면에 출력할 레코드 갯수
		
		int listcount = bbsService.getListCount();	//총 리스트 수를 받아옴
		
		int maxpage = (listcount + limit - 1) / limit;
		
		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등 ...);
		int startpage = ((page - 1) / 10) * 10 + 1;
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 = " + startpage);
		
		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등 ...);
		int endpage = startpage + 10 - 1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 = " + endpage);
		
		if(endpage > maxpage) {
			endpage = maxpage;
		}
		
		bbslist = bbsService.getBbsList(page);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/bbs/bbs_list");
		
		mv.addObject("page", page);
		mv.addObject("maxpage", maxpage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("listcount", listcount);
		
		mv.addObject("bbslist", bbslist);
		
		return mv;
	}
	
	@RequestMapping(value="/bbs_cont.nhn")
	public ModelAndView board_cont(
			@RequestParam(value="bbs_num") int bbs_num,
			@RequestParam(value="page") int page,
			@RequestParam(value="state") String state) throws Exception{

		if(state.equals("cont")) {
			bbsService.bbsHit(bbs_num);
		}
		BbsBean bbs = bbsService.getBbsCont(bbs_num);
		
		ModelAndView contM = new ModelAndView();
		contM.addObject("bcont", bbs);
		contM.addObject("page", page);
		
		if(state.equals("cont")) {
			contM.setViewName("bbs/bbs_cont");
		} else if(state.equals("edit")) {
			contM.setViewName("bbs/bbs_edit");
		} else if(state.equals("delete")) {
			contM.setViewName("bbs/bbs_del");
		} else if(state.equals("reply")) {
			contM.setViewName("bbs/bbs_reply");
		}
		
		return contM;
	}
}
