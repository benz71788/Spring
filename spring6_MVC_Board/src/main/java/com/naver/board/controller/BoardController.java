package com.naver.board.controller;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.naver.board.dao.BoardService;
import com.naver.board.model.BoardBean;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	private String saveFolder=
			"D:\\workspace-sts\\Spring\\spring6_MVC_Board\\src\\main\\webapp\\resources\\upload";
	
	@RequestMapping(value="/board_write.nhn")
	public String board_write() {
		return "/board/board_write";
	}
	
	/*
	 * 스프링 컨테이너는 매개변수BbsBean객체를 생성하고
	 * BbsBean객체의 setter매서드들을 호출하여 사용자 입력값을 설정합니다.
	 * 매개변수의 이름과 setter의 property가 일치하면 됩니다.
	 */
	
	/*
	 * 자료실 저장
	 * 	첨부파일 클릭해서 이미지 보고자 할 경우 : 
	 * 	자동 새로 고침 설정(window -> Preferences -> workspace
	 * 			-> Refresh using native hooks or polling 체크)
	 * 	하고 5초 정도 지난 뒤 확인하세요.
	 * 
	 * 	CommonsMultipartResolver 역할 :
	 * 		파일 업로드 기능을 구현해 놓은 클래스입니다.
	 * 
	 * 	스프링 컨테이너는 매개변수 BbsBean객체를 생성하고
	 * 	BbsBean객체의 setter 메서드들을 호출하여 사용자 입력값을 설정합니다.
	 * 	매개변수의 이름과 setter의 property가 일치하면 됩니다.
	 */
	
	@RequestMapping(value="/board_write_ok.nhn",
			method=RequestMethod.POST)
	public String board_write_ok(BoardBean board) throws Exception{
		MultipartFile uploadfile = board.getUploadfile();
		
		if(!uploadfile.isEmpty()) {
			//원래 파일명 구해오기
			String fileName = uploadfile.getOriginalFilename();
			System.out.println(fileName);
			//원래 파일명 저장
			board.setBoard_original(fileName);
			
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
			board.setBoard_file(fileDBName);
		}
		
		this.boardService.insertBoard(board);	//저장메서드 호출
		return "redirect:/board_list.nhn";
	}
	
	@RequestMapping(value="/board_list.nhn")
	public ModelAndView board_list(
			@RequestParam(value="page", defaultValue="1") int page,
			HttpServletRequest request) throws Exception {
		List<BoardBean> boardlist = new ArrayList<BoardBean>();

		int limit = 10;	// 한 화면에 출력할 레코드 갯수
		
		//자료실 내용 보기 후 목록을 선택했을 때 limit값을 유지합니다.
		//아래 부분을 주석을 달 경우 limit=10으로 설정합니다.
		//이전에 설정된 limit가 있는지 체크
		HttpSession session = request.getSession();
		if(session.getAttribute("limit") != null) {
			limit = Integer.parseInt(session.getAttribute("limit").toString());
		}
		
		//변경된 limit가 있는지 체크
		if(request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
			session.setAttribute("limit", limit);
			System.out.println("limit=" + limit);
		}

		int listcount = boardService.getListCount(); //총 리스트 수를 받아옴
		
		//총 페이지 수
		int maxpage = (listcount + limit - 1)/limit;
		
		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등 ...);
		int startpage = ((page - 1) / 10) * 10 + 1;
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 = " + startpage);
		
		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등 ...);
		int endpage = startpage + 10 - 1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 = " + endpage);
		
		if(endpage > maxpage) {
			endpage = maxpage;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("limit", limit);
		
		boardlist = boardService.getBoardList(map);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/board/board_list");
		
		mv.addObject("page", page);
		mv.addObject("maxpage", maxpage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("listcount", listcount);
		
		mv.addObject("boardlist", boardlist);
		
		return mv;
	}
	
	@RequestMapping(value="/board_cont.nhn")
	public ModelAndView board_cont(
			@RequestParam(value="board_num") int board_num,
			@RequestParam(value="page") int page,
			@RequestParam(value="state") String state) throws Exception{
		
		if(state.equals("cont")) {	//내용보기 일때만
			boardService.boardHit(board_num);
		}
		BoardBean board = boardService.getBoardCont(board_num);
		
		ModelAndView contM = new ModelAndView();
		
		
		if(state.equals("cont")) {
			contM.setViewName("board/board_cont");
			
			String board_cont = board.getBoard_content().replace("\n", "<br/>");
			contM.addObject("board_cont", board_cont);
		} else if(state.equals("edit")) {
			contM.setViewName("board/board_edit");
		} else if(state.equals("delete")) {
			contM.setViewName("board/board_del");
		} else if(state.equals("reply")) {
			contM.setViewName("board/board_reply");
		}
		
		contM.addObject("bcont", board);
		contM.addObject("page", page);

		return contM;
	}
	
	@RequestMapping(value="/board_edit_ok.nhn", method = RequestMethod.POST)
	public String board_eidt_ok(BoardBean bean, 
			@RequestParam(value="page") int page,
			HttpServletResponse response) throws Exception{
		
		System.out.println("넘어온 비밀번호 = " + bean.getBoard_pass());
		System.out.println("page = " + page);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();	//출력스트림 생성
		
		// 번호를 기준으로 DB 내용을 가져옵니다.
		BoardBean board = boardService.getBoardCont(bean.getBoard_num());
		if(!board.getBoard_pass().equals(bean.getBoard_pass())) {
			out.println("<script>");
			out.println("alert('비번이 다릅니다.!');");
			out.println("history.back()");
			out.println("</script>");
		} else {	//비번이 같다면
			MultipartFile uploadfile = bean.getUploadfile();
			
			if(!uploadfile.isEmpty()) {
				File delFile = new File(saveFolder + board.getBoard_file());
				if(delFile.exists()) {
					delFile.delete();	//기존 이진파일을 삭제
				}
				
				//워래 파일명 구해오기
				String fileName = uploadfile.getOriginalFilename();
				System.out.println(fileName);
				
				//원래 파일명 저장
				bean.setBoard_original(fileName);
				
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
				String refileName = "bbs" + year + month + date + random + "."
						+ fileExtension;
				System.out.println("refileName=" + refileName);
				
				//오라클 디비에 저장될 레코드 값
				String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
				System.out.println("fileDBName = " + fileDBName);
				
				//transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
				uploadfile.transferTo(new File(saveFolder + fileDBName));
				
				//바뀐 파일명으로 저장
				bean.setBoard_file(fileDBName);
			}
			boardService.boardEdit(bean);
			
			return "redirect:/board_cont.nhn?board_num=" 
				+ bean.getBoard_num() + "&page=" + page + "&state=cont";
		}
		
		return null;
	}
	
	@RequestMapping(value="/board_del_ok.nhn", method = RequestMethod.POST)
	public String board_del_ok(BoardBean bean,
			@RequestParam("page") int page,
			HttpServletResponse response) throws Exception{
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		BoardBean board = boardService.getBoardCont(bean.getBoard_num());
		
		//기존 파일명 가져옵니다.
		String fname = bean.getBoard_file();
		if(!board.getBoard_pass().equals(bean.getBoard_pass())) {
			out.println("<script>");
			out.println("alert('비번이 다릅니다.');");
			out.println("history.back()");
			out.println("</script>");
		} else {
			if(fname != null) {//기존 이진파일이 존재한다면
			File file = new File(saveFolder + fname);
			file.delete();	//서버 폴더로 부터 기존 이진파일 삭제합나다.
		}
			boardService.boardDelete(bean.getBoard_num());
			return "redirect:/board_list.nhn?page=" + page;
		}
		
		return null;
	}
	
	@RequestMapping(value="/download.file", method= {RequestMethod.GET})
	public void downloadFile(HttpServletResponse response,
			@RequestParam(value="path") String storedFileName,
			@RequestParam(value="original") String originalFileName,
			HttpServletRequest request) throws Exception{
		request.setCharacterEncoding("UTF-8");
		System.out.println("original = " + originalFileName);
		
		byte fileByte[] = 
				FileUtils.readFileToByteArray(
						new File(saveFolder + storedFileName));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition", "attachment; fileName=\""
				+ URLEncoder.encode(originalFileName, "UTF-8") + "\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	@RequestMapping(value="/board_reply_ok.nhn", method = RequestMethod.POST)
	public String board_reply_ok(BoardBean bean,
			@RequestParam("page") int page) throws Exception{
		
		boardService.refUpdate(bean);
		
		bean.setBoard_re_seq(bean.getBoard_re_seq() + 1);
		bean.setBoard_re_lev(bean.getBoard_re_lev() + 1);
		bean.setBoard_readcount(0);
		
		boardService.refInsert(bean);
		
		return "redirect:/board_list.nhn?page=" + page;
	}
	
	@RequestMapping(value="/board_find_ok.nhn", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView board_find_ok(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam("find_name") String find_name,
			@RequestParam("find_field") String find_field) throws Exception {
			
		int limit = 10;
		
		HttpSession session = request.getSession();
		if(session.getAttribute("limit") != null) {
			limit = (Integer) session.getAttribute("limit");
		}
		
		if(request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
			session.setAttribute("limit", limit);
		}
		
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("limit", limit);
		m.put("page", page);
		m.put("find_field", find_field);
		m.put("find_name", "%" + find_name + "%");
		int listcount = this.boardService.getListCount3(m);
		
		//총 페이지 수
		int maxpage = (listcount + limit - 1) / limit;
		
		int startpage = ((page - 1) / 10) * 10 + 1;
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 = " + startpage);
		
		int endpage = startpage + 10 - 1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 = " + endpage);
		
		if(endpage > maxpage) {
			endpage = maxpage;
		}
		
		List<BoardBean> boardlist = boardService.getBoardList3(m);
		
		ModelAndView model = new ModelAndView();
	
		model.setViewName("board/board_find");
		
		model.addObject("find_name", find_name);
		model.addObject("find_field", find_field);
		model.addObject("startpage", startpage);
		model.addObject("endpage", endpage);
		model.addObject("maxpage", maxpage);
		model.addObject("page", page);
		model.addObject("limit", limit);
		model.addObject("listcount", listcount);
		model.addObject("boardlist", boardlist);
		
		return model;
	}
}
