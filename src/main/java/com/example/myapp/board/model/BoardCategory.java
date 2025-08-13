package com.example.myapp.board.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BoardCategory {
	
	private  int categoryId;
	private String categoryName;
	private String categoryDescription;

}
