package com.bioodas.seckill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bioodas.seckill.entity.SeckillUser;
import com.bioodas.seckill.enums.ResultEnum;
import com.bioodas.seckill.exception.SeckillException;
import com.bioodas.seckill.service.LoginService;
import com.bioodas.seckill.service.SeckillUserService;
import com.bioodas.seckill.util.MD5Util;
import com.bioodas.seckill.web.form.UserForm;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private SeckillUserService seckillUserService;

	@Override
	public boolean login(UserForm userForm) {
		System.err.println(userForm.getMobile()+":"+userForm.getPassword());
		/**密码效验*/
		SeckillUser seckillUser = seckillUserService.findByMobile(userForm.getMobile());
		if(seckillUser==null) throw new SeckillException(ResultEnum.USER_NOT_REGISTER);
		System.err.println(seckillUser);
		String calcPass = MD5Util.formPassToDBPass(userForm.getPassword(), seckillUser.getSalt());
		System.err.println("calcPass:"+calcPass);
		if (calcPass.equals(seckillUser.getPassword())) {
			return true;
		}
		throw new SeckillException(ResultEnum.LOGIN_FAIL);
	}

}
