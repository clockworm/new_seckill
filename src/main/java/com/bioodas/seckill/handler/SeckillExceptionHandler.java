package com.bioodas.seckill.handler;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bioodas.seckill.enums.ResultEnum;
import com.bioodas.seckill.exception.AuthorizeException;
import com.bioodas.seckill.exception.ResponseSpecialException;
import com.bioodas.seckill.exception.SeckillException;
import com.bioodas.seckill.util.ResultVOUtil;
import com.bioodas.seckill.vo.ResultVO;

/**
 * @author TangLingYun
 * @describe 异常控制处理器
 * @date 2018年1月4日
 */
@ControllerAdvice
public class SeckillExceptionHandler {

	/** 通用异常控制器 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResultVO<?> handlerException(HttpServletRequest request, Exception e) {
		if (e instanceof BindException) {
			BindException bdEx = (BindException) e;
			List<ObjectError> errors = bdEx.getAllErrors();
			ObjectError error = errors.get(0);
			return ResultVOUtil.fail("参数错误:".concat(error.getDefaultMessage()));
		}
		return ResultVOUtil.error(ResultEnum.SERVER_ERROR.getCode(), ResultEnum.SERVER_ERROR.getMessage());
	}
	
	/** 拦截登录异常 控制器 */
	@ExceptionHandler(value = AuthorizeException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ModelAndView handlerAuthorizeException() {
		return new ModelAndView("redirect:" + "登录页面");
	}

	/** 业务异常控制器 */
	@ExceptionHandler(value = SeckillException.class)
	@ResponseBody
	public ResultVO<?> handlerSellException(SeckillException e) {
		return ResultVOUtil.error(e.getCode(), e.getMessage());
	}

	/** 特殊业务异常控制器 返回HTTP 头消息状态为403 */
	@ExceptionHandler(value = ResponseSpecialException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public void handlerSellException() {
	}
}
