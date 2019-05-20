package com.campus.common;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class TestDateTime {
	/**
	 * 日期转字符串
	 */
//	@Test
	public void testDateDemo1() {
		Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(date));
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));
        sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        System.out.println(sdf.format(date));
	}
	/**
	 * 字符串转日期
	 * @throws ParseException 
	 */
//	@Test
	public void testDateDemo2() throws ParseException {
		String string = "2016-10-24 21:59:06";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.parse(string));
	}
//	@Test
	 public  void getNowDateShort() {
		   Date currentTime = new Date();
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		  String dateString = formatter.format(currentTime);
		  ParsePosition pos = new ParsePosition(8);
		   Date currentTime_2 = formatter.parse(dateString, pos);
		    System.out.println(currentTime_2);
	 }
	 @Test
	public void strToDate( ) {
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		   Date date=new Date();
		   String strDate=formatter.format(date);
		  ParsePosition pos = new ParsePosition(0);
		   Date strtodate = formatter.parse(strDate, pos);
		   System.out.println(strtodate);
	}
	
	
	
	
}
