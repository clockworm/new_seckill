package com.bioodas.seckill.util.redis;


public class UserKey extends BasePrefix{

	/**默认30天过期时间*/
	private UserKey(String keyPrefix) {
		super(keyPrefix);
	}
	
	/**自定义多少秒过期时间*/
	public UserKey(int expireSeconds, String keyPrefix) {
		super(expireSeconds, keyPrefix);
	}

	public final static UserKey generateKeyById = new UserKey("id_%s");
	public final static UserKey generateKeyByName = new UserKey(60*30,"name_%s");
}
