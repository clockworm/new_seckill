package com.bioodas.seckill.exception;


import com.bioodas.seckill.enums.ResultEnum;

import lombok.Getter;

/**
 * @author TangLingYun
 * @describe 全局异常处理
 * @date 2018年1月4日
 */
@Getter
public class SeckillException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private Integer code;

    public SeckillException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SeckillException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
