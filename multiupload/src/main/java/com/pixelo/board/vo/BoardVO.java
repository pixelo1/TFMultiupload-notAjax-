package com.pixelo.board.vo;

import java.util.Date;

import lombok.Data;
@Data
public class BoardVO {

	private long no;
	private String title, content, writer;
	private Date writeDate;
	private long hit;
	
	
}
