package com.chengjuiot.exception;
import com.chengjuiot.result.CodeMsg;
import com.chengjuiot.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	@ExceptionHandler(value = Exception.class) // 拦截任何的异常
	public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
		if (e instanceof GlobalException) {
			e.printStackTrace();
			GlobalException ex = (GlobalException) e;
			return Result.error(ex.getCm());
		} else if (e instanceof BindException) {
			e.printStackTrace();
			BindException ex = (BindException) e;
			List<ObjectError> errors = ex.getAllErrors();
			ObjectError error = errors.get(0);
			String msg = error.getDefaultMessage();
			return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
		} else {
			e.printStackTrace();
			return Result.error(CodeMsg.ERROR);
		}
	}
}
