package com.bioodas.seckill.web.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.bioodas.seckill.annotation.IsMobile;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserForm {
	
	@NotNull
	@IsMobile
	private String mobile;
	@NotNull
	@Length(min=6)
	private String password;

}
