package com.example.myapp.member.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Member {
	
	private String userid;
	private String name;
	private String password;
	private String password2;
	private String phone;
	private String email;
	private String role;

}