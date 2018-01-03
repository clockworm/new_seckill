package com.bioodas.seckill.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 * @author TangLingYun
 * @describe 效验工具库
 * @date 2018年1月3日
 */
public class ValidatorUtil {
	
	/** 手机号码表达式*/
	private static final Pattern MOBILE_PATTERN = Pattern.compile("1\\d{10}");
	
	/** 手机号码是否正确*/
	public static boolean isMobile(String mobile) {
		if(StringUtils.isBlank(mobile)) {
			return false;
		}else{
			Matcher matcher = MOBILE_PATTERN.matcher(mobile);
			return matcher.matches();
		}
	}

}
