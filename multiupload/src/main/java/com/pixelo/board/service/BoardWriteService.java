package com.pixelo.board.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pixelo.board.mapper.BoardMapper;
import com.pixelo.board.vo.BoardVO;


@org.springframework.stereotype.Service
public class BoardWriteService implements com.pixelo.myapp.Service {

	private static final Logger log = LoggerFactory.getLogger(BoardViewService.class);

	private BoardMapper mapper;
	
	
	@Inject
	public void setMapper(BoardMapper mapper) {
		this.mapper = mapper;
	}



	@Override
	public Object Service(Object obj) throws Exception {

		BoardVO vo = (BoardVO)obj;
		
		mapper.write(vo);
		//등록후 바로 글번호 받아오기
		
		
		return mapper.maxNo(vo.getWriter());
	}

}
