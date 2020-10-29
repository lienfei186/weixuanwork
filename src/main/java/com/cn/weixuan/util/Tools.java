package com.cn.weixuan.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 去除数字前面的1，比如去除1000002前面的1
 *
 * @author ljh
 * @date 2020.1.6
 */
public class Tools {

    public static String subStr(String str, int start) {
        if (str == null || str.equals("") || str.length() == 0)
            return "";
        if (start < str.length()) {
            return str.substring(start);
        } else {
            return "";
        }
    }

    /**
     * 生成PYyyyyMMdd+000001此类流水编号
     *
     * @param maxOrderno
     * @return
     */
    public static String createNumber(String maxOrderno) {
        String orderNo = null;
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd"); // 时间字符串产生方式
        String str = sf.format(new Date()); // 组合流水号前一部分，时间字符串，如：20200710
        if (maxOrderno != null && maxOrderno.contains(str)) {
            String uid_end = maxOrderno.substring(14, 19); // 截取字符串最后四位，结果:0001
            int endNum = Integer.parseInt(uid_end); // 把String类型的001转化为int类型的1
            int tmpNum = 1000000 + endNum + 1; // 结果1002
            orderNo = str +"S009"+ Tools.subStr("" + tmpNum, 1);// 把1002首位的1去掉，再拼成0190710002字符串
        } else {
            //第一位
            orderNo = str + "S009000001";
        }
        return orderNo;
    }
}
