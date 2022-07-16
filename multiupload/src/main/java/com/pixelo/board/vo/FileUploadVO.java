package com.pixelo.board.vo;

import lombok.Data;

@Data
public class FileUploadVO {

	private long uploadNo, boardNo;
	private String orgFileName, fileName;
	private long fileSize;
	private String realSavePath;

}
