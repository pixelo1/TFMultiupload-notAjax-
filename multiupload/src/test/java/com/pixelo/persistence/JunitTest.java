package com.pixelo.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pixelo.board.service.BoardListService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)//파라미터를 클래스 전체로 넣어줘야함
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class JunitTest {

	@Setter(onMethod_ = {@Autowired})
	private BoardListService boardListService;
	
	@Test
	public void testExist() {
		
		log.info(boardListService); //자동 DI 가 되었는지 확인 
		log.info("-------------------------------------");
		
		if(boardListService == null) log.info("null 입니다");
		else log.info("null이 아닙니다.");


	}
}
