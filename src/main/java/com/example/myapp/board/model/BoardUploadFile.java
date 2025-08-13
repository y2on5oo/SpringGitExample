package com.example.myapp.board.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude="fileData")
public class BoardUploadFile { //게시판에 첨부될 파일 정보를 저장할 클래스
	
	private int fileId;
	private int boardId;
	private String fileName;
	private long fileSize;
	private String fileContentType;
	private byte[] fileData;

}
