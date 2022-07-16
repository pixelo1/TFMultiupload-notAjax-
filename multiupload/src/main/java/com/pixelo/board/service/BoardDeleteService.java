package com.pixelo.board.service;

import javax.inject.Inject;

import com.pixelo.board.mapper.BoardMapper;
@org.springframework.stereotype.Service
public class BoardDeleteService implements com.pixelo.myapp.Service {

	private BoardMapper mapper;
	@Inject
	public void setMapper(BoardMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public Object Service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		long no = (long) obj;
		
		return mapper.delete(no);
	}

}
