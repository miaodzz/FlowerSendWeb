package util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Stringutil {
	
	public static boolean checkTelFormat(String tel)
	{
		if(tel.matches("^\\d{11}$")) return true; 
		return false;
	}
	
	public static String DateStamp2String(Timestamp now) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 定义格式，不显示毫秒
		return df.format(now);
	}

	public static Timestamp String2Timestamp(String time) {// String转化为Timestamp:
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return  Timestamp.valueOf(time);
	}
	
	public static Date String2Date(String str){
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");  
		Date date=null;
		try {
			date = new Date(format1.parse(str).getTime());   
		}catch(Exception e){
			e.printStackTrace();
		}
		return date;
	}
	
	public static String Date2String(Date startime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(startime);

		}
	
    public static boolean isInteger(String input){  
        Matcher mer = Pattern.compile("^[0-9]+$").matcher(input);  
        return mer.find();  
    }  
}
