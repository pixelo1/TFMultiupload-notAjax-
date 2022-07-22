package com.pixelo.board.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pixelo.board.vo.BoardVO;
import com.pixelo.board.vo.FileUploadVO;
import com.pixelo.controller.UploadController;
import com.pixelo.fileupload.service.BoardFileUploadService;
import com.pixelo.myapp.Service;
import com.webjjang.util.PageObject;

import lombok.Setter;

//자동으로 new 하게 하려고 / servlet-context.xml 에 myapp만 되어있는상태 수정해준다
// 자동생성 - @Cotroller, @Service, @Repository, @Component, @RestController(Ajax쓸때), @Advice(예외처리할때)
// 단, servlet-context.xml 에서 component-scan 안에 base-package 속성 안에  작성되어있어야한다
@Controller
// 슬레쉬 board 를 잡아온다
@RequestMapping("/board")
public class BoardController {

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	//변수명 사용이라 소문자로써야함 - service 인터페이스를 거쳐서 맞는것을 찾아간다
	private Service boardListService;
	private Service boardViewService;
	private Service boardWriteService;
	private Service boardUpdateService;
	private Service boardDeleteService;
	
	private Service boardFileUploadService;
	private Service boardFileNameService;
	private Service boardFileDeleteService;
	
	
	//boardListService를 자동으로 연결시켜 쓴다 - 
	//DI 적용 Spring 단 - @Autowired, java 단 - @Inject 
	//jsp 할때는 디스패쳐에서 직접 넣어줬어야함
	@Autowired
	public void setBoardListService(Service boardListService) {
		this.boardListService = boardListService;
	}
	@Autowired
	public void setBoardViewService(Service boardViewService) {
		this.boardViewService = boardViewService;
	}
	@Autowired
	public void setBoardWriteService(Service boardWriteService) {
		this.boardWriteService = boardWriteService;
	}
	@Autowired
	public void setBoardUpdateService(Service boardUpdateService) {
		this.boardUpdateService = boardUpdateService;
	}
	@Autowired
	public void setBoardDeleteService(Service boardDeleteService) {
		this.boardDeleteService = boardDeleteService;
	}
	@Autowired
	public void setBoardFileUploadService(Service boardFileUploadService) {
		this.boardFileUploadService = boardFileUploadService;
	}
	@Autowired
	public void setBoardFileNameService(Service boardFileNameService) {
		this.boardFileNameService = boardFileNameService;
	}
	@Autowired
	public void setBoardFileDeleteService(Service boardFileDeleteService) {
		this.boardFileDeleteService = boardFileDeleteService;
	}
	// 게시판 리스트 return 으로 String 넘겨준다 (), model 로 request에 저장시킨다
	@GetMapping("/list.do")
	//
	public String list(PageObject pageObject, Model model) throws Exception {

		log.info("게시판 리스트 처리");
		// 알아서 jsp로 간다 - servlet-context에서 /WEB-INF/views/ + board/list + .jsp 라고 붙여준다
		//어차피 슬레시는 두개 붙어도 괜춘
		//model에 담으면 request에 담기게 된다
		model.addAttribute("list", boardListService.Service(pageObject));
		model.addAttribute("pageObject",pageObject);
		return "board/list";
	}

	// 게시판 글보기
	@GetMapping("/view.do")
	// 변수이름과 key를 맞춰서 받는다 / 파라미터는 반드시 넣어줘야 에러가 안난다
	// null을 형변환 시킬수는 없으니 숫자데이터는 오류가난다
	public String view(Long no, int inc, Model model) throws Exception {

		log.info("게시판 글 보기 no: " + no +"inc : "+ inc);

		model.addAttribute("vo", boardViewService.Service(new Object[] {no, inc}));
		
		List<FileUploadVO> fileNameList = (List<FileUploadVO>) boardFileNameService.Service(no);
		log.info("fileNameList 반환값 log : " + fileNameList.toString());
		model.addAttribute("fileNameList", fileNameList);

		return "board/view";
	}
	
	// 게시판 글쓰기 폼
	@GetMapping("/write.do")
	public String writeForm() throws Exception {

		log.info("게시판 글쓰기 폼");

		return "board/write";
	}

	// 게시판 글쓰기 처리
	@PostMapping("/write.do")
	public String write(BoardVO vo,MultipartFile[] uploadFile, Model model, HttpServletRequest request) throws Exception {

		log.info("게시판 글쓰기 처리- vo :" +vo);
		vo.setNo((long) boardWriteService.Service(vo));
		
		UploadController uploadController = new UploadController();
		List<FileUploadVO> list = uploadController.uploadFormPost(uploadFile, model, request, vo.getNo());
		
		log.info("vo.getNo :" +vo.getNo() + " list : " + list.toString());
		
		boardFileUploadService.Service(list);
		//파일업로드 vo 생성후 거기에 파일 이름 ... 넣고 db도 추가로 생성해야함
		
		// 같은 위치에있다 list.do 만 붙여준다 - 처리한 데이터를갖고 /list.do getMapping 되어 메서드 실행됨(바로 jsp 가는것이 아니기떄문에 처리하는것이 남았다)
		return "redirect:list.do";
	}



	// 게시판 글 수정폼
	@GetMapping("/update.do")
	public String updateForm(Long no, Model model) throws Exception {

		log.info("게시판 글 수정  폼 no: " + no);
		//수정할 데이터를 가져온다 - boardViewService(obj[]) 사용 inc를 직접넣어주면 된다
		model.addAttribute("vo", boardViewService.Service(new Object[] {no, 0}));
		
		List<FileUploadVO> fileNameList = (List<FileUploadVO>) boardFileNameService.Service(no);
		log.info("fileNameList 반환값 log : " + fileNameList.toString());
		model.addAttribute("fileNameList", fileNameList);
		
		
		return "board/update";
	}

	// 게시판 글 수정 처리
	@PostMapping("/update.do")
	public String update(BoardVO vo,MultipartFile[] uploadFile,@RequestParam(defaultValue = "0") String[] del, Model model, HttpServletRequest request) throws Exception {

		log.info("게시판 글수정 처리  -vo :" +vo);
		boardUpdateService.Service(vo);
		
		UploadController uploadController = new UploadController();
		
		log.info("기존 파일 배열" + del.length);
		boolean fileCheck = false;
		//write + del 서비스 2개가 되어야함 del먼저 만들자
		//배열이 있으면 기존 db 정보삭제, 실제파일 삭제 + 파일업로드
		//없으면(기존에 파일이 없다는뜻 ) 파일 업로드만 
		//하나가 있다면 디폴트는 무조건 안들어감, 없다면 디폴트가 0일것
		if(del[0] != "0") {
			fileCheck = true;
			//기존에 파일이 있다
		}
		
		if(fileCheck) {
			List<FileUploadVO> fileNameList = (List<FileUploadVO>) boardFileNameService.Service(vo.getNo());
			
			//실제 파일 삭제
			
			uploadController.delete(fileNameList);
			//db 삭제
			boardFileDeleteService.Service(vo.getNo());
			
			
		}
		//기존파일 삭제처리 이후 또는 기존에 파일이 없는 상황 -> 파일 업로드 
		List<FileUploadVO> list = uploadController.uploadFormPost(uploadFile, model, request, vo.getNo());
		
		boardFileUploadService.Service(list);
		
		
		return "redirect:view.do?no="+vo.getNo()+"&inc=0";
	}

	// 게시판 글삭제
	@GetMapping("/delete.do")
	public String delete(long no, HttpServletRequest request) throws Exception {

		log.info("게시판 글삭제 처리 no :" + no);
		List<FileUploadVO> fileNameList = (List<FileUploadVO>) boardFileNameService.Service(no);
		
		//실제 파일 삭제
		UploadController uploadController = new UploadController();
		uploadController.delete(fileNameList);
	
		boardFileDeleteService.Service(no);
		boardDeleteService.Service(no);
		
		return "redirect:list.do";
	}
}
