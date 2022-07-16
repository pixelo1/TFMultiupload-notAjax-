package com.pixelo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.pixelo.board.vo.BoardVO;
import com.pixelo.board.vo.FileUploadVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UploadController {

	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload Form ....");
	}
	
	
	@PostMapping("/uploadFormAction")
	public List<FileUploadVO> uploadFormPost(MultipartFile[] uploadFile, Model model, HttpServletRequest request, long no) throws IllegalStateException, IOException {

		List<FileUploadVO> list = null;
		// 저장할 상대 위치 정하기 /webapp/upload - servlet-context.xml에서 허용되어진 폴더이여야 한다.
		// 실제적으로 폴더도 만들어 놔야 한다. 파일 한개를 넣어 두면 반드시 폴더를 만든다. 자원이 없으면 폴더를 안 만들 
		String path = "/upload";
		
		// request.getServletContext().realpath() -> DS에서 ServletContext는 주지 않는다. request를 받아서 쓴다.
		log.info(request);
		//업로드 경로에 날짜를 추가시킴
		File uploadPath = new File(path, getFolder());
		log.info("upload path : "+ uploadPath);

		
		//실제적으로 저장되느 절대 위치 찾기
//		String realPath = request.getServletContext().getRealPath(path);
		String realPath = request.getServletContext().getRealPath(uploadPath.toString());
		log.info("실제적인 저장 위치 : " + realPath);
		
//		폴더 생성
		File folder = new File(realPath);
		boolean isExist = folder.exists();
		if(!isExist) folder.mkdirs();
		
		for (MultipartFile mf : uploadFile) {
			log.info("-------------------------------------------");

			log.info("Upload file name : " + mf.getOriginalFilename());

			log.info("Upload file size : " + mf.getSize());

			String orgFileName = mf.getOriginalFilename();
			//IE 는 file path 가  추가적으로 필요(모든 파일 경로가 다나와서 ), \\인덱스 다음에서 끝까지(파일이름만) 잘라오기
			String onlyFileName = orgFileName.substring(orgFileName.lastIndexOf("\\")+1);
			log.info("only file name :" + onlyFileName);
			
			UUID uuid = UUID.randomUUID();
			String uuidOrgFileName = uuid.toString().replace("-", "") + "_"+onlyFileName;
			
			// c:\\upload+업로드 되는 파일이름
			File saveFile = new File(realPath, uuidOrgFileName);

			if(saveFile.exists()) log.info("저장할 파일이 존재합니다");
				// 저장
			mf.transferTo(saveFile);
			log.info("실제 저장 경로 : "+ saveFile.toString());
			
			//null 이면 에러나서 생성해준다
			if(list ==null) list = new ArrayList<FileUploadVO>();
			FileUploadVO vo = new FileUploadVO();
			vo.setOrgFileName(orgFileName);
			vo.setFileName(uuidOrgFileName);
			vo.setFileSize(mf.getSize());
			vo.setRealSavePath(saveFile.toString());
			vo.setBoardNo(no);
			
			
			list.add(vo);
		}
			
		// jsp에 첨부파일을 보낸다
		model.addAttribute("uploadFile", uploadFile);

		return list;
	}
	
	// 연 월 일 폴더 생성하기 위한 날짜 -> 2022-07-13 의 "-" 를 운영체제에 맞는 구분자로 바꿈
	private String getFolder() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		log.info("날짜 : " + sdf);
		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);

	}

	// 파일이 이미지타입인지 검사
	private Boolean checkImageType(File file) {

		try {
			// probeContentType - Mime타입을 확인하지못하면 null을 반환한다(내용이 아니라 파일 확장자를 이용하면 판단함)
			// MIME 란 파일변환은 이야기한다, file.toPath()는 path 타입으로 바꾸는 메서드 -
			// ex)c:\sample\sample.txt
			// path 의 메서드를 이용하면 상위폴더가 무엇인지, 경로 단계 수가 얼마인지 알수있다
			String contentType = Files.probeContentType(file.toPath());
			// 마입 타입 - 파일의 종류(image)/ 파일포맷(.gif or .jpg)
			return contentType.startsWith("image");
		} catch (Exception e) {
			// 이미파일 아니면 에러남
			e.printStackTrace();
		}

		return false;
	}
	
}
