package com.chengjuiot.result;

public class Result<T> {
	private Integer code;
	private String msg;
	private Object data;

	public Result() {
	}

	public Result(Object data) {
		this.code = 1;
		this.msg = "success";
		this.data = data;
	}

	public static <T> Result<T> error(CodeMsg codemsg) {
		return new Result<T>(codemsg);
	}

	public Result(CodeMsg cm) {
		if (cm == null) {
			return;
		}
		this.code = cm.getCode();
		this.msg = cm.getMsg();
	}

	public static <T> Result<T> success(T data) {
		return new Result<T>(data);
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
