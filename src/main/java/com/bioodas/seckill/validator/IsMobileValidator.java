package com.bioodas.seckill.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.bioodas.seckill.annotation.IsMobile;
import com.bioodas.seckill.util.ValidatorUtil;

/**
 * @author TangLingYun
 * @describe 手机号码校验器具体实现
 * @date 2018年1月3日
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile,String>{

	/**是否必传*/
	private boolean required = false;
	
	/**得到获取注解值 初始化个人参数*/
	@Override
	public void initialize(IsMobile constraintAnnotation) {
		required = constraintAnnotation.required();
	}

	/**是否通过*/
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(required) {
			return ValidatorUtil.isMobile(value);
		}else {
			if(StringUtils.isBlank(value)) {
				return true;
			}else {
				return ValidatorUtil.isMobile(value);
			}
		}
	}

}
