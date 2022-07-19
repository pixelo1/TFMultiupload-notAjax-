package com.pixelo.fileupload.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pixelo.board.vo.BoardVO;
import com.pixelo.board.vo.FileUploadVO;

public interface BoardFileUploadMapper {

	
	//파일 업로드내용 db에 저장
	public int fileUpload(List<FileUploadVO> list) throws Exception;
	
	//fileName 가져오기
	public List<FileUploadVO> getFileName(long no) throws Exception;
}
