package com.pixelo.fileupload.service;

import javax.inject.Inject;

import com.pixelo.fileupload.mapper.BoardFileUploadMapper;
import com.pixelo.myapp.Service;

import lombok.Setter;

@org.springframework.stereotype.Service
public class BoardFileNameService implements Service{

	@Setter
	@Inject
	private BoardFileUploadMapper fileUploadMapper;
	

	@Override
	public Object Service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		long no = (long) obj;
		
		return fileUploadMapper.getFileName(no);
		
	}

}
