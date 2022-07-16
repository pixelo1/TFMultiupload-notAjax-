package com.pixelo.board.service;

import javax.inject.Inject;

import com.pixelo.board.mapper.BoardMapper;
import com.pixelo.board.vo.BoardVO;
@org.springframework.stereotype.Service
public class BoardUpdateService implements com.pixelo.myapp.Service {

	
	private BoardMapper mapper;
	@Inject
	public void setMapper(BoardMapper mapper) {
		this.mapper = mapper;
	}



	@Override
	public Object Service(Object obj) throws Exception {

		BoardVO vo = (BoardVO) obj;
		return mapper.update(vo);
	}

}
