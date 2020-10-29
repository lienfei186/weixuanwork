package com.cn.weixuan.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtils {

	public static final String YYYY_MM_DD = "YYYY-MM-dd";

	public static final int SUNDAY = 1;

	// 获取今日日期格式化为:YYYY-MM-dd
	public static String getDate(String strDate) {
		return DateUtil.format(DateUtil.parse(strDate), YYYY_MM_DD);
	}
	
	public static String getDateOffsetDate(String strDate,int offset) {
		return DateUtil.format(DateUtil.offsetDay(DateUtil.parse(strDate),1), YYYY_MM_DD);
	}

	// 时间转日期
	public static Date parseToDate(String strDate) {
		return DateUtil.parse(strDate);
	}

	public static int getDayOfWeek(Date date) {
		return DateUtil.dayOfWeek(date);
	}

	public static Boolean isSunday(String strDate) {

		// 如果是周日则返回true
		if (getDayOfWeek(parseToDate(strDate)) == SUNDAY) {
			return true;
		}
		return false;
	}

	public static Boolean isHoliday(String date, List<String> holidayList) {
		// 如果节假日为空，直接返回false;
		if (holidayList == null) {
			return false;
		}
		// 判断日期是否是节假日
		return holidayList.contains(date);

	}

	// 获得指定日期是星期几，1表示周日，2表示周一
	public static void main(String[] args) {
		List<String> holidayList = new ArrayList<String>();
		holidayList.add("2020-01-01");
		holidayList.add("2020-10-01");
		holidayList.add("2020-10-02");
		holidayList.add("2020-10-03");
		holidayList.add("2020-10-04");
		holidayList.add("2020-10-05");
		holidayList.add("2020-10-06");
		holidayList.add("2020-10-07");
		holidayList.add("2020-10-08");
		log.info(getDate("2020-06-30 01:01:01"));
		log.info(getDateOffsetDate(getDate("2020-06-30 01:01:01"),1));
	}

}
