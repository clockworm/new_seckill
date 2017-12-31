package com.bioodas.seckill.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author TangLingYun
 * @describe 明文加密
 * @date 2017年12月31日
 */
public class MD5Util {

	/**盐值*/
	private final static String salt = "bd066ff2be84a16d7c19c8455291054d";
	
	/**MD5加密*/
	public static String md5(String str) {
		return DigestUtils.md5Hex(str);
	}
	
	/**生成随机盐值*/
	public static String genUniqueSalt() {
		return KeyUtil.genUniqueKey();
	}

	/**明文加密成表单MD5*/
	public static String inputPassToFormPass(String inputPass) {
		String str = "" + salt.charAt(1) + salt.charAt(4) + salt.charAt(0) + inputPass + salt.charAt(2) + salt.charAt(3);
		return md5(str);
	}
	
	/**表单MD5换数据库MD5*/
	public static String formPassToDBPass(String formPass,String salt) {
		String str = "" + salt.charAt(1) + salt.charAt(4) + salt.charAt(0) + formPass + salt.charAt(2) + salt.charAt(3);
		return md5(str);
	}
	
	/**明文加密成数据库MD5   saltDb数据库随机盐值*/
	public static String inputPassToDbPass(String passWord,String saltDb) {
		System.err.println(saltDb);
		String formPass = inputPassToFormPass(passWord);
		String dbpass = formPassToDBPass(formPass,saltDb);
		return dbpass;
	}
	
	public static void main(String[] args) {
//		5ce6b5edb7d11813fb2b62bc2fdb6330 明文加密
//		1514742677242850681 盐值
//		37a0d56bffb1524b9f8719e0a1faa942 数据库MD5
		System.err.println(inputPassToFormPass("tang0624"));
		System.err.println(inputPassToDbPass("tang0624",genUniqueSalt()));
	}

}
