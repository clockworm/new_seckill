package com.bioodas.seckill.util.redis;

/**
 * @author TangLingYun
 * @describe redis过期时间和生成key 策略 (保证key不唯一)
 * @date 2017年12月30日
 */
public abstract class BasePrefix implements KeyPrefix {

	/** 过期时间 默认0为永不过期 */
	private int expireSeconds;
	/** Key前缀策略 */
	private String keyPrefix;

	public BasePrefix(int expireSeconds, String keyPrefix) {
		this.expireSeconds = expireSeconds;
		this.keyPrefix = keyPrefix;
	}
	
	/**默认过期时间30天*/
	public BasePrefix(String keyPrefix) {
		this.expireSeconds = 60*60*24*30;
		this.keyPrefix = keyPrefix;
	}

	@Override
	public int expireSeconds() {
		return expireSeconds;
	}

	@Override
	public String generateKeyPrefix() {
		String className = getClass().getSimpleName();
		return className + ":" + keyPrefix;
	}

}
