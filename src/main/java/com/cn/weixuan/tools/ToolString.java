package com.cn.weixuan.tools;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToolString {
    public static volatile boolean isDevMode = false;

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getCheckNumber() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 去除字符串中的空格、回车、换行符、制表符等
     *
     * @param str
     * @return
     */
    public static String replaceSpecialStr(String str) {
        String repl = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            repl = m.replaceAll("");
        }
        return repl;
    }

    public static String getUUIDTake(int c) {
        return getUUID().substring(0, c);
    }

   

    public static String getStringIsNull(String str) {
        if (str != null && !str.equals("")) {
            return str;
        } else {
            return "";
        }
    }

}
