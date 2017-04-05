package com.eternal.look.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 邱永恒
 * @time 2017/2/16 23:18
 * @desc 将时间格式化成日期
 */

public class DateFormatterUtil {
    /**
     * 将long类date转换为String类型
     * @param date date
     * @return String date
     */
    public static String ZhihuDailyDateFormat(long date) {
        String sDate;
        Date d = new Date(date + 24*60*60*1000);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        sDate = format.format(d);
        return sDate;
    }

    public static String DoubanDateFormat(long date){
        String sDate;
        Date d = new Date(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        sDate = format.format(d);

        return sDate;
    }
}
