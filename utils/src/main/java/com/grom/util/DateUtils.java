package com.grom.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class DateUtils {
	
	
	final static  SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制  
	
	/**
	 * 当前时间
	 * @return
	 */
	public final static long currentTimeMillis()
	{
		
		return System.currentTimeMillis();
	}
	
	
	/**
	 * 
	 * 时间 格式化  yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public final static String toStringyyyyMMddHHmmss(Date date)
	{
		if(date==null)
		{
			return null;
		}
		
		return  sdformat.format(date)  ;
	}

	/**
	 * 
	 * yyyy-MM-dd HH:mm:ss 字符 转 date 
	 * ：
	 * @return
	 */
	public final static Date toDateyyyyMMddHHmmss(String  datest)
	{
		if(StringUtils.isEmpty(datest) )
		{
			return null;
		}
		
		try {
			return  sdformat.parse(datest)  ;
		} catch (ParseException e) {
		}
		
		return null;
	}
 
	
	
	public static boolean objectNotNull(Object object) {
		if (null == object) {
			return false;
		}
		return true;
	}

	private static final ThreadLocal<SimpleDateFormat> dateFullFormat = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};
	private static final ThreadLocal<SimpleDateFormat> dateFormat = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	public static Date getCurrent(String date) throws ParseException {
		return dateFullFormat.get().parse(date);
	}

	public static Date getCurrent(String date, String pattern) throws ParseException {
		return isEmpty(pattern) || !"yyyy-MM-dd".equals(pattern) ? getCurrent(date) : dateFullFormat.get().parse(date);
	}

	public static String format(Date date) {
		return dateFullFormat.get().format(date);
	}

	public static String format(Date date, String pattern) {
		return isEmpty(pattern) || !"yyyy-MM-dd".equals(pattern) ? dateFullFormat.get().format(date) : dateFormat.get().format(date);
	}

	public static Date addDayOfYear(Date old, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(old);
		calendar.add(Calendar.DAY_OF_YEAR, amount);
		return calendar.getTime();
	}

	public static Date addDayOfYear(String old, int amount, String pattern) {
		try {
			return addDayOfYear(getCurrent(old, pattern), amount);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date addDayOfYear(int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, amount);
		return calendar.getTime();
	}

	public static String addDayOfYear(int amount, String pattern) {
		if (isEmpty(pattern))
			pattern = "yyyy-MM-dd";
		return format(addDayOfYear(amount), pattern);
	}

	public static boolean isEmpty(String str) {
		return null == str || "".equals(str.trim());
	}

	public static String timeToString(Date date) {

		if (DateUtils.objectNotNull(date)) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			return sdf.format(date);
		}
		return null;
	}

	public static String timeToStringDay(Date date) {

		if (DateUtils.objectNotNull(date)) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			return sdf.format(date);
		}
		return null;
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			if (str != null && str != "") {
				date = format.parse(str);
			} else {
				return null;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDateDay(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			if (str != null && str != "") {
				date = format.parse(str);
			} else {
				return null;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date StrToDate(String str, String fmt) {
		SimpleDateFormat format = new SimpleDateFormat(fmt);
		Date date = null;
		try {
			if (str != null && str != "") {
				date = format.parse(str);
			} else {
				return null;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	* @autho 李方华
	* @remark 给定时间所在日期的最大时间
	* @date 2016年10月18日 下午4:21:43
	* @param
	* @return 
	* @throws  ParseException
	*/
    public static Date maxDayTime(Date date){
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.set(Calendar.HOUR_OF_DAY, 23);
    	c.set(Calendar.MINUTE, 59);
    	c.set(Calendar.SECOND, 59);
		return c.getTime();
    }
    
    public static Date minDayTime(Date date){
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.set(Calendar.HOUR_OF_DAY, 00);
    	c.set(Calendar.MINUTE, 00);
    	c.set(Calendar.SECOND, 00);
		return c.getTime();
    }
	
}
