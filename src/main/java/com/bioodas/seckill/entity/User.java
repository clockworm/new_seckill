package com.bioodas.seckill.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
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
