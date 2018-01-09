package com.bioodas.seckill.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bioodas.seckill.web.form.UserForm;

public interface LoginService {

	boolean login(HttpServletResponse response,UserForm userForm);

	void logout(HttpServletRequest request, HttpServletResponse response);

	
}
