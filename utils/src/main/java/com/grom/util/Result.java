package com.grom.util;

/** 
* 类说明 :   接口返回结果定义
* @author  谭东
* @date 2016年9月6日  下午2:45:35
*/
public class Result {

	/*
	 * 成功
	 */
	public static Json success() {
        return success(null);
    }

    public static Json success(Object data) {
        return success(Constants.DESC_SUCCESS, data);
    }
	
    public static Json success(String msg, Object data){
    	return new Json(Constants.CODE_SUCCESS, msg, data);
    }
    
    /*
     * 业务逻辑失败
     */
    public static Json failure() {
        return failure(Constants.DESC_FAILTURE);
    }
    
    public static Json failure(String msg) {
        return failure(Constants.CODE_FAILTURE, msg);
    }
    
    public static Json failure(String code, String msg) {
        return new Json(code, msg, null);
    }
    
    /*
     * 异常
     */
    public static Json exception(Throwable e) {
    	return new Json(Constants.CODE_EXCEPTION, Constants.DESC_EXCEPTION, null);
    }
    
}
