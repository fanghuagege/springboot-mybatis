package com.grom.util;

/**
 * 接口返回格式
 * @author chenjing
 * @date 2016年7月22日
 */
public class Json {
	
	private String code;

	private String desc;

	private Object data;
	
	public Json(){
		
	}
	
	//测试用
	public Json(Object obj){
		this.code = Constants.CODE_SUCCESS;
		this.desc = Constants.DESC_SUCCESS;
		this.data = obj;
	}

	public Json(String code, String desc, Object data) {
		this.code = code;
		this.desc = desc;
		this.data = data;
	}
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Json [code=" + code + ", desc=" + desc + ", data=" + data + "]";
	}

}
