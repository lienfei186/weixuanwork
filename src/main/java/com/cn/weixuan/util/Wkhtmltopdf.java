package com.cn.weixuan.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * HTML页面转换为PDF时调用的方法
 */

@Slf4j
public class Wkhtmltopdf {

    //wkhtmltopdf 在系统中的路径 切记:更换配置一定要修改此配置
    private static final String toPdfTool = "\\wkhtmltopdf\\bin\\wkhtmltopdf.exe";
    //转换报告需要的样式
    private static final String userStyleSheet = "\\wkhtmltopdf\\bin\\convertReport.css";
    /**
     * html转pdf
     * @param srcPath  html路径，可以是硬盘上的路径，也可以是网络路径
     * @param destPath pdf保存路径
     * @return 转换成功返回true
     */
    public static boolean convert(String srcPath, String destPath) {
        File file = new File(destPath);
        File parent = file.getParentFile();
        //如果pdf保存路径不存在，则创建路径
        if (!parent.exists()) {
            parent.mkdirs();
        }
        StringBuilder cmd = getFormal();
        //html路径 即目标网页路径
        cmd.append(" ");
        cmd.append(srcPath);
        cmd.append(" ");
        //pdf保存路径
        cmd.append(destPath);
        boolean result = true;
        try {
            Process proc = Runtime.getRuntime().exec(cmd.toString());
            HtmlToPdfInterceptor error = new HtmlToPdfInterceptor(proc.getErrorStream());
            HtmlToPdfInterceptor output = new HtmlToPdfInterceptor(proc.getInputStream());
            error.start();
            output.start();
            proc.waitFor();
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 标准格式
     * @return
     */
    public static StringBuilder getFormal() {
        StringBuilder cmd = new StringBuilder();
        //项目根路径
        String userDir=System.getProperty("user.dir");
        //wkhtmltopdf 在系统中的路径
        cmd.append(userDir+toPdfTool);
        cmd.append(" ");
        //cmd.append("  -T 0mm "); //设置页面上边距 (default 10mm)
        //cmd.append("  -B 0mm "); //设置页面下边距 (default 10mm)
        cmd.append("  --zoom 1.5"); //设置缩放比
        cmd.append("  --user-style-sheet "+userDir+userStyleSheet); //设置报告页面字体样式
        //cmd.append("  --disable-smart-shrinking "); //不使用智能收缩策略
        //cmd.append("  -L 0mm "); //设置页面左边距 (default 10mm)
        //cmd.append("  -R 0mm "); //设置页面右边距 (default 10mm)
        log.info("{}",cmd);
        return cmd;
    }
}