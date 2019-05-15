package com.grom.util;

public enum ResultCode {
	/** 成功 */
	SUCCESS("1", "成功"),
	
	/** 未知的错误 */
	UNKNOWN_ERROR("0", "未知错误"),
	
	/** 操作失败 */
	OPERATION_FAILED("50", "操作失败"),
	/** 传入参数有误 */
	PARAMETER_ERROR("100", "传入参数有误"),
	
	/** 发生异常 */
	EXCEPTION("500", "系统异常");
	
//	/** 系统错误 */
//	SYS_ERROR("402", "系统错误"),
//	
//	/** 参数错误 */
//	PARAMS_ERROR("403", "参数错误 "),
//	
//	/** 不支持或已经废弃 */
//	NOT_SUPPORTED("410", "不支持或已经废弃"),
//	
//	/** AuthCode错误 */
//	INVALID_AUTHCODE("444", "无效的AuthCode"),
//
//	/** 太频繁的调用 */
//	TOO_FREQUENT("445", "太频繁的调用")
	

	
	private ResultCode(String value, String msg){
		this.val = value;
		this.msg = msg;
	}
	
	public String val() {
		return val;
	}

	public String msg() {
		return msg;
	}
	
	private String val;
	private String msg;
}
