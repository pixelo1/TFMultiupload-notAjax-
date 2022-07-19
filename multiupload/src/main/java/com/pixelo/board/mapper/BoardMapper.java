package com.pixelo.board.mapper;

import java.util.List;

import com.pixelo.board.vo.BoardVO;
import com.webjjang.util.PageObject;

public interface BoardMapper {

	// 게시판 리스트 pageObject가 들어가서 리스트가 나온다
	public List<BoardVO> list(PageObject pageObject) throws Exception;		
	
	//전체 데이터 개수 가져오기
	public long getTotalRow(PageObject pageObject) throws Exception;
	
	//조회수 1증가
	public int increase(long no)throws Exception;
	
	//게시판 글보기
	public BoardVO view(long no)throws Exception;
	
	//게시판 글쓰기
	public int write(BoardVO vo) throws Exception;
	
	//게시판 글수정
	public int update(BoardVO vo) throws Exception;
	
	//게시판 글 삭제
	public int delete(long no) throws Exception;
	
	//가장 큰 글번호 받아오기
	public long maxNo(String writer) throws Exception;
	

}
