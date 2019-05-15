package com.grom.util;


/**   
* @author chenjing
* @version 2016年7月21日 下午3:46:06  
* 类说明  
**/
public class Constants {
	/**
	 * session中唯一设备id的Key
	 */
	public static final String APP_CODE = "app_code";
	/**
	 * session中userId的Key
	 */
	public static final String USER_ID = "user_id";
	public static final String USER_TYPE = "user_type";  //0表示正常用户,1表示商户
	
    /**
     * 接口正确返回
     */
    public static final String CODE_SUCCESS = "0";
    public static final String DESC_SUCCESS = "操作成功";
    
    /**
     * 接口返回业务失败
     */
    public static final String CODE_FAILTURE = "1";
    public static final String DESC_FAILTURE = "操作失败";
    
    
    /**
	 * 接口异常
	 */
	public static final String CODE_EXCEPTION = "-1";
	public static final String DESC_EXCEPTION = "系统异常";
    
	
	/**
	 * 新用户或商户生成时，插入的系统消息内容
	 */
	public static final String REGISTER_USER_SYS_MSG ="终于等到你，还好我没放弃~亲，我们在这里恭候多时啦！欢迎您使用铠恩买手~";
	public static final String REGISTER_MERCHANT_SYS_MSG ="欢迎入驻铠恩买手平台";
	
	/**
	 * 订单 优惠券支付名称
	 */
	public static final String PREFER_REMARK = "买手优惠券";
	
	/**
	 * 用户内部求购 比比货标题
	 */
	public static final String INNER_PARITY_TITLE = "我想买类似的商品，有资源的商家请尽快联系我~";
	/**
	 * 用户内部求购比比货向商家推送消息内容
	 */
	public static final String INNER_PARITY_PUSH_MSG = "店内相似商品求购信息";
	
	/**
	 * 推送到商家的title
	 */
	public static final String MERCHANT_PUSH_TITLE = "铠恩买手商家";
	
	/**
	 * 手机request.getHeader("User-Agent")
	 */
	public final static String[] AGENT = { "Android", "iPhone", "iPod", "iPad", "Windows Phone", "MQQBrowser" };
	
	public static final String REGEX_MOBILE = "^1[34578]\\d{9}$" ; // 正则表达式：验证手机号  "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"
	
	
	
	
	
	
	
	/**
	 * 表示正常返回
	 */
	public static final Integer NORMAL_RETURN = 1; 
	
	
	/**
	 * 表示号码未注册
	 */
	public static final Integer PHONE_NOT_REGISTER = 2; 
	
	/**
	 * 电话已经被邀请
	 */
	public static final Integer PHONE_INVITATION = 3; 
	
	/**
	 * 不在活动时间内
	 */
	public static final Integer NOT_ACTIVITY = 4; 
	
	/**
	 * 表示邀请名额不足
	 */
	public static final Integer NOT_NUMBER = 5; 
	/**
	 * 号码注册时间不在活动时间内
	 */
	public static final Integer PHONE_NOT_ACTIVITY = 6; 
	
	/**
	 * 必须是审核状态
	 */
	public static final Integer SHENHE_STATE = -2; 
	
	/**
	 * 不能是删除状态
	 */
	public static final Integer NOT_DELETE_STATE = -3; 
	
	/**
	 * 必须是下架状态
	 */
	public static final Integer XIAJIA_STATE = -4; 
	/**
	 * 必须是收货状态
	 */
	public static final Integer SHOUHUO_STATE = -5; 
//	/**
//	 * 推送连接
//	 */
//	public static final String PUSH_URL =ClustersConfig.getAttributes("push_url"); 
	
	/**
	 * 用户事件id
	 */
	public static final String ORDERCREATED_SYSTEMEVENT_ID="ordercreated_systemevent_id";
}
