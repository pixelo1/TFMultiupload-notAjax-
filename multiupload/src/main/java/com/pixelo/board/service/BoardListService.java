package com.pixelo.board.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pixelo.board.mapper.BoardMapper;
import com.webjjang.util.PageObject;


//service 어노테이션 안붙어있어서 자동생성 안된다 - service 붙여준다
// 자동생성 - @Controller, @service, @Repository, @ Component, @RestController
@org.springframework.stereotype.Service
public class BoardListService implements com.pixelo.myapp.Service {

	private static final Logger log = LoggerFactory.getLogger(BoardListService.class);

	//root-context 에mybatis-spring:scan 으로 지정해놔서 생성된다
	private BoardMapper mapper;
	
	//mvc 가 아니라 인젝트로 함 / DB값 가져오기
	@Inject 
	public void setMapper(BoardMapper mapper) {
		this.mapper = mapper;
	}



	@Override
	public Object Service(Object obj) throws Exception {
		
		PageObject pageObject = (PageObject) obj;
		
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		
		log.info("pageObject - "+pageObject);
		
		return mapper.list(pageObject);
	}

	
}
