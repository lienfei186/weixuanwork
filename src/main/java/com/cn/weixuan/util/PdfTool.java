package com.cn.weixuan.util;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Title PdfTool
 * @Package
 * @Description TODO
 * @Author cosco  coscofan@outlook.com
 * @Date 2020/5/21 0021 14:40
 */
@Slf4j
public class PdfTool {
    private static Logger logger = LoggerFactory.getLogger(PdfTool.class);
    /**
     *
     * @param toolPath       工具路径
     * @param url       远程网页地址
     * @param path      生成本地文件路径
     * @param cv        chrome 内核版本号 直接取本地配置
     * @return
     */
    public static boolean genPDF(String toolPath, String cv, String url, String path,Boolean status, String clearPrinterName) throws Exception{
        logger.info("toolPath: {} cv: {} url: {} path: {} ", toolPath, cv, url, path);
        String execPath = (toolPath + "/GenPDF.exe");
        if (!FileUtil.isFile(execPath)) {
            throw new Exception("配置转换pdf工具路径错误");
        }

        if (StringUtils.isBlank(cv) || StringUtils.isBlank(url) || StringUtils.isBlank(path)) {
            throw new Exception("转换文件关键参数缺失");
        }

        StringBuilder cmdStr = new StringBuilder();
        cmdStr.append(execPath)
//                .append(" -bp").append(" ").append(toolPath)
                .append(" -cv").append(" ").append(cv)
                .append(" -u").append(" ").append(url)
                .append(" -p").append(" ").append(path);
        // 归档状态推送到自助打印机
        if(status) {
            // 添加打印机
            cmdStr.append(" -send_to_print").append("=\"").append(clearPrinterName).append("\"");
        }

        try {
            runCMD(cmdStr.toString());
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public static boolean runCMD(String cmd) throws IOException, InterruptedException {
        logger.info(cmd);
        final String METHOD_NAME = "runCMD";

        // Process p = Runtime.getRuntime().exec("cmd.exe /C " + cmd);
        Process p = Runtime.getRuntime().exec(cmd);
        BufferedReader br = null;
        try {
            // br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String readLine = br.readLine();
            StringBuilder builder = new StringBuilder();
            while (readLine != null) {
                readLine = br.readLine();
                builder.append(readLine).append("\r\n");
            }
            logger.debug(METHOD_NAME + "#readLine: " + builder.toString());

            p.waitFor();
            int i = p.exitValue();
            logger.info(METHOD_NAME + "#exitValue = " + i);
            if (i == 0) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            logger.error(METHOD_NAME + "#ErrMsg=" + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String toolPath = "I:/python-workspace/GenPDF/dist";
        String cv = "83.0.4103";
        String url = "http://10.20.1.123:7078/html/print/report.html?id=ES20042900076";
        String path = "d:/10.pdf";
        boolean status = genPDF(toolPath, cv, url, path,false, "IPS Printer ( Color )");
        log.info("{}",status);
    }

}
