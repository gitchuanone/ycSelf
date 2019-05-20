package com.manage.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

public class TestDemo {
	
	@Test
	public void testDateTo() {
		String datdString="Thu May 18 2017 00:00:00 GMT+0800";
		datdString = datdString.replace("GMT", "").replaceAll("\\(.*\\)", "");
		System.out.println("1:"+datdString);
	    //将字符串转化为date类型，格式2016-10-12
	    SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
	    Date dateTrans = null;
	    try {
	        dateTrans = format.parse(datdString);
	        System.out.println("2="+dateTrans);
//	        String dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTrans).replace("-","/");
	        String dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTrans);
	        System.out.println("3="+dd);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
