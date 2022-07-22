package com.pixelo.fileupload.service;

import java.util.List;

import javax.inject.Inject;

import com.pixelo.board.vo.FileUploadVO;
import com.pixelo.fileupload.mapper.BoardFileUploadMapper;
import com.pixelo.myapp.Service;

import lombok.Setter;
@org.springframework.stereotype.Service
public class BoardFileDeleteService implements Service {

	@Setter
	@Inject
	private BoardFileUploadMapper mapper;
	
	
	
	@Override
	public Object Service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		long boardNo = (long) obj;
		
		
		return mapper.deleteFile(boardNo);
	}

}
