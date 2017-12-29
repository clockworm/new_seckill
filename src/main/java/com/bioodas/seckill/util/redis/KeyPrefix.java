package com.bioodas.seckill.util.redis;

public interface KeyPrefix {

	/**redis存储过期时间*/
	int expireSeconds();

	/**生成redis存储Key前缀*/
	String generateKeyPrefix();

}
