package com.zhong.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    //日期转字符串
    public static String dateToString(Date date, String pattern){
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        String format = sdf.format(date);
        return format;

    }
    //字符串转日期
    public static Date stringToDate(String str,String pattern) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        Date parse = sdf.parse(str);
        return parse;
    }
}
