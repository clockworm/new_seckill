package com.bioodas.seckill.entity;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SeckillUser {

	private String id;
	private String mobile;
	private String nickname;
	private String password;
	private String salt;
	private String head;
	private Date registerDate;
	private Date lastLoginDate;
	private Integer loginCount;
	
}
