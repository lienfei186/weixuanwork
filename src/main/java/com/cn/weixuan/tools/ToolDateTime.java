package com.cn.weixuan.tools;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ToolDateTime {
    private static String year;
    private static String month;
    private static String day;
    private static String hour;
    private static String mins;

    static {
        Calendar c = Calendar.getInstance();
        year = String.valueOf(c.get(Calendar.YEAR));
        month = String.valueOf(c.get(Calendar.MONTH));
        day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
        hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        mins = String.valueOf(c.get(Calendar.MINUTE));
    }

    public static String getNowYear() {
        return year;
    }

    public static String getBeforeMounth(String date, int numBefore) {
        Date ddd = getDateYYYYMMDDHHMMSS(date);
        Calendar cc = Calendar.getInstance();
        cc.setTime(ddd);
        cc.add(Calendar.MONTH, numBefore);
        ddd = cc.getTime();
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sss = myFmt.format(ddd);
        return sss;
    }

    public static String getYYYYMMDDHHMMSS(Date date) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sss = myFmt.format(date);
        return sss;
    }

    public static String getYYYYMMDD(Date date) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd");
        String sss = myFmt.format(date);
        return sss;
    }

    public static String getYYYYMMDDNumber(Date date) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyyMMdd");
        String sss = myFmt.format(date);
        return sss;
    }

    public static String getMMDDHHMM(Date date) {
        SimpleDateFormat myFmt = new SimpleDateFormat("MM-dd HH:mm");
        String sss = myFmt.format(date);
        return sss;
    }

    public static String getMMDD(Date date) {
        SimpleDateFormat myFmt = new SimpleDateFormat("MM-dd");
        String sss = myFmt.format(date);
        return sss;
    }

    public static String getYYYYMM(Date date) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-M");
        String sss = myFmt.format(date);
        return sss;
    }

    public static String getYYYYMM(String yyyymmdd) {
        String yyyymm;
        try {
            Date d = new SimpleDateFormat("yyyy-MM-dd").parse(yyyymmdd);
            yyyymm = getYYYYMM(d);
        } catch (ParseException e) {
            e.printStackTrace();
            return yyyymmdd;
        }
        return yyyymm;
    }

    public static String getStringYYYYMMDD(String pdate) {
        String showStr;
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(pdate);
            DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
            showStr = format1.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
            showStr = pdate;
        }
        return showStr;
    }

    public static Date getDateYYYYMMDDHHMMSS(String pdate) {
        Date date;
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = format.parse(pdate);

        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return date;
    }

    /**
     * 中心医院推送过来的时间格式  20200108034338.000
     *
     * @param s
     * @return
     */
    public static Date getDateYYYYMMDDHHMMSSS(String s) {
        if (StringUtils.isEmpty(s)) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
        Date d = null;
        try {
            d = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static Date getDateYYYYMMDD(String pdate) {
        Date date;
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            date = format.parse(pdate);

        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return date;
    }

    /**
     * <p>Title: getCalendarYYYYMMDD</p>
     * <p>Description:获取上个月的日期 </p>
     *
     * @return String
     * @author Ting Xia
     */
    public static String getLastDate() {
        String predate;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);//获取年份
        int month = cal.get(Calendar.MONTH);//获取月份
        int day = cal.get(Calendar.DATE);//获取日
        predate = year + "-" + (month < 10 ? "0" + month : month) + "-"
                + (day < 10 ? "0" + day : day);
        return predate;
    }
}
