package com.bioodas.seckill.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
	
	/** ID编号*/
	private String id;
	/** 用户名*/
	private String name;
	
	public User() {}
	
	public User(String genUniqueKey, String name) {
		this.id = genUniqueKey;
		this.name = name;
	}
	
}
