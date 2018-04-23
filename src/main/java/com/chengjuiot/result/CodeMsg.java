package com.chengjuiot.result;

public class CodeMsg {
	private Integer code;
	private String msg;

	
	
	public CodeMsg(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	
	//通用的错误码
	public static  CodeMsg SUCCESS = new CodeMsg(1,"suceess");
	public static CodeMsg ERROR = new CodeMsg(0,"error");
	public static CodeMsg BIND_ERROR = new CodeMsg(500500,"参数绑定错误：%s");
	//登录模块的错误码
	public static CodeMsg PASSWORD_EMPTY= new CodeMsg(500210,"用户名或者密码不能为空");
	public static CodeMsg SESSION_ERROR= new CodeMsg(500211,"Session不存在或者已经失效");
	public static CodeMsg MOBILE_ERROR= new CodeMsg(500212,"手机号格式错误");
	public static CodeMsg USER_NULL= new CodeMsg(500213,"用户不存在");
	public static CodeMsg PASSWORD_ERROR= new CodeMsg(500214,"密码不正确");
	//秒杀模块
	public static CodeMsg MIAOSHA_OVER= new CodeMsg(300100,"秒杀商品已空");
	public static CodeMsg MIAOSHA_REPEATE= new CodeMsg(300101,"不能重复秒杀");
	public CodeMsg fillArgs(Object... args) {
		int code  = this.code;
		String message = String.format(this.msg, args);
		return new CodeMsg(code,message);
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
