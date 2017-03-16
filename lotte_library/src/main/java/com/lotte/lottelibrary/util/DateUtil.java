package com.lotte.lottelibrary.util;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Lotte on 2016/10/8.
 * 日期和时间操作相关的函数
 */
public class DateUtil {

    private static SimpleDateFormat DATE_FORMAT_TILL_SECOND = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static SimpleDateFormat DATE_FORMAT_TILL_DAY_CURRENT_YEAR = new SimpleDateFormat("MM-dd");
    private static SimpleDateFormat DATE_FORMAT_TILL_DAY_CH = new SimpleDateFormat("yyyy-MM-dd");

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期逻辑
     *
     * @param dateStr 日期字符串
     * @return
     */
    public static String timeLogic(String dateStr) {
        Calendar calendar = Calendar.getInstance();
        calendar.get(Calendar.DAY_OF_MONTH);
        long now = calendar.getTimeInMillis();
        Date date = null;
        try {
            date = strToDate(dateStr, DATE_FORMAT);
        } catch (Exception e) {
            return dateStr;
        }
        try {
            calendar.setTime(date);
        } catch (Exception e) {
            return dateStr;
        }
        long past = calendar.getTimeInMillis();
        // 相差的秒数
        long time = (now - past) / 1000;

        StringBuffer sb = new StringBuffer();
        if (time > 0 && time < 60) { // 1小时内
            return sb.append(time + "秒前").toString();
        } else if (time > 60 && time < 3600) {
            return sb.append(time / 60 + "分钟前").toString();
        } else if (time >= 3600 && time < 3600 * 24) {
            return sb.append(time / 3600 + "小时前").toString();
        } else if (time >= 3600 * 24) {
            return sb.append((int) Math.floor(time / (3600 * 24)) + "天前").toString();
        }
        return "时间获取中";
    }

    /**
     * 日期字符串转换为Date
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date strToDate(String dateStr, String format) {
        Date date = null;

        if (!TextUtils.isEmpty(dateStr)) {
            DateFormat df = new SimpleDateFormat(format);
            try {
                date = df.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * 日期转换为字符串
     *
     * @param timeStr
     * @param format
     * @return
     */
    public static String dateToString(String timeStr, String format) {
        // 判断是否是今年
        Date date = strToDate(timeStr, format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 如果是今年的话，才去“xx月xx日”日期格式
        if (calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) {
            return DATE_FORMAT_TILL_DAY_CURRENT_YEAR.format(date);
        }

        return DATE_FORMAT_TILL_DAY_CH.format(date);
    }

    // 获取去年的年份
    public static int getLastYear() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -1);
        return c.get(Calendar.YEAR);
    }

    // 获取下一年年份
    public static int getNextYear() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, 1);
        return c.get(Calendar.YEAR);
    }

    public static String formatDate(int year, int month, int day) {//月份从0开始
        if (month < 10 && day < 10) {
            return year + "-" + "0" + (month + 1) + "-" + "0" + day;
        } else if (month < 10) {
            return year + "-" + "0" + (month + 1) + "-" + day;
        } else if (day < 10) {
            return year + "-" + (month + 1) + "-" + "0" + day;
        } else {
            return year + "-" + (month + 1) + "-" + day;
        }
    }

    public static String formatTime(int hour, int min) {
        if (hour < 10 && min < 10) {
            return "0" + hour + ":" + "0" + min;
        } else if (hour < 10) {
            return "0" + hour + ":" + min;
        } else if (min < 10) {
            return hour + ":" + "0" + min;
        } else {
            return hour + ":" + min;
        }
    }

    /**
     * 判断是否是闰年
     */
    public static boolean isRunNian(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
