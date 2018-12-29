package com.example.demo.constants;

public class DemoException extends RuntimeException{
	
	private static final long serialVersionUID = 4469004050656405136L;
	private String errCode = "";
	private String errMessage = "";

	public DemoException(String _errCode, String _errMessage) {
		this.errCode = _errCode;
		this.errMessage = _errMessage;
	}

	public DemoException(ReturnCodeEnum msg) {
		this.errCode = msg.getCode();
		this.errMessage = msg.getDesc();
	}
	
	public DemoException(ReturnCodeEnum msg, String descRemark) {
		this.errCode = msg.getCode();
		this.errMessage = msg.getDesc() + "[" + descRemark + "]";
	}

	public DemoException(String _errCode, String _errMessage, Throwable e) {
		super(e);
		this.errCode = _errCode;
		this.errMessage = _errMessage;
	}

	public DemoException(ReturnCodeEnum msg, Throwable e) {
		super(e);
		this.errCode = msg.getCode();
		this.errMessage = msg.getDesc();
	}
	
	public String getErrCode() {
		return this.errCode;
	}

	@Override
	public String getMessage() {
		return this.errMessage;
	}
	@Override
	public String toString() {
		return  "[" + this.errCode + "]" + this.errMessage;
	}
}
