package com.cn.weixuan.sms;

import com.cn.weixuan.sms.inter.http.v1.dto.response.SmsResponse;
import com.cn.weixuan.sms.util.AES;
import com.cn.weixuan.sms.util.GZIPUtils;
import com.cn.weixuan.sms.util.JsonHelper;
import com.cn.weixuan.sms.util.http.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
public class SMSUtils {
    /**
     * http://bjksmtn.b2m.cn
     * http://bjmtn.b2m.cn
     * http://shmtn.b2m.cn
     * 端口是80
     * <p>
     * http://gzmtn.b2m.cn
     * http://www.btom.cn
     * 端口是8080
     */
    // appId
    static String appId = "EUCP-EMY-SMS1-35LC2";// 请联系销售，或者在页面中 获取
    // 密钥
    static String secretKey = "39545B83B904FEFE";// 请联系销售，或者在页面中 获取
    // 接口地址
    static String host = "http://bjmtn.b2m.cn";// 请联系销售获取
    // 加密算法
    static String algorithm = "AES/ECB/PKCS5Padding";
    // 编码
    static String encode = "UTF-8";
    // 是否压缩
    static boolean isGizp = true;

    /**
     * 发送单条短信
     */
    public static boolean setSingleSms(String mobile, String content) {
        log.info("发送单条短信，手机号:{},短信内容:{}", mobile, content);
        SmsSingleRequest pamars = new SmsSingleRequest();
        pamars.setContent(content);
        pamars.setCustomSmsId(null);
        pamars.setExtendedCode(null);
        pamars.setMobile(mobile);
        ResultModel result = request(appId, secretKey, algorithm, pamars, host + "/inter/sendSingleSMS", isGizp, encode);
        log.info("result code: {}", result.getCode());
        if ("SUCCESS".equals(result.getCode())) {
            SmsResponse response = JsonHelper.fromJson(SmsResponse.class, result.getResult());
            if (response != null) {
                log.info("data : " + response.getMobile() + "," + response.getSmsId() + "," + response.getCustomSmsId());
                return true;
            }
        }
        return false;
    }

    /**
     * 公共请求方法
     */
    private static ResultModel request(String appId, String secretKey, String algorithm, Object content, String url, final boolean isGzip, String encode) {
        Map<String, String> headers = new HashMap<String, String>();
        HttpRequest<byte[]> request = null;
        try {
            headers.put("appId", appId);
            headers.put("encode", encode);
            String requestJson = JsonHelper.toJsonString(content);
            log.info("result json: " + requestJson);
            byte[] bytes = requestJson.getBytes(encode);
            log.info("request data size : " + bytes.length);
            if (isGzip) {
                headers.put("gzip", "on");
                bytes = GZIPUtils.compress(bytes);
                log.info("request data size [com]: " + bytes.length);
            }
            byte[] parambytes = AES.encrypt(bytes, secretKey.getBytes(), algorithm);
            log.info("request data size [en] : " + parambytes.length);
            HttpRequestParams<byte[]> params = new HttpRequestParams<byte[]>();
            params.setCharSet("UTF-8");
            params.setMethod("POST");
            params.setHeaders(headers);
            params.setParams(parambytes);
            params.setUrl(url);
            if (url.startsWith("https://")) {
                request = new HttpsRequestBytes(params, null);
            } else {
                request = new HttpRequestBytes(params);
            }
        } catch (Exception e) {
            log.info("加密异常");
            e.printStackTrace();
        }
        HttpClient client = new HttpClient();
        String code = null;
        String result = null;
        try {
            HttpResponseBytes res = client.service(request, new HttpResponseBytesPraser());
            if (res == null) {
                log.info("请求接口异常");
                return new ResultModel(code, result);
            }
            if (res.getResultCode().equals(HttpResultCode.SUCCESS)) {
                if (res.getHttpCode() == 200) {
                    code = res.getHeaders().get("result");
                    if (code.equals("SUCCESS")) {
                        byte[] data = res.getResult();
                        log.info("response data size [en and com] : " + data.length);
                        data = AES.decrypt(data, secretKey.getBytes(), algorithm);
                        if (isGzip) {
                            data = GZIPUtils.decompress(data);
                        }
                        log.info("response data size : " + data.length);
                        result = new String(data, encode);
                        log.info("response json: " + result);
                    }
                } else {
                    log.info("请求接口异常,请求码:" + res.getHttpCode());
                }
            } else {
                log.info("请求接口网络异常:" + res.getResultCode().getCode());
            }
        } catch (Exception e) {
            log.info("解析失败");
            e.printStackTrace();
        }
        ResultModel re = new ResultModel(code, result);
        return re;
    }

    /**
     * 生成随机数字
     *
     * @param size 4~6之间
     * @return code
     */
    public static String genCode(int size) {
        if (size > 6) {
            size = 6;
        }
        if (size < 4) {
            size = 4;
        }
        StringBuffer code = new StringBuffer();
        for (int i = 1; i <= size; i++) {
            int ran1 = (int)(Math.random()*10);
            code.append(ran1);
        }
        return code.toString();
    }

    /**
     * 短信模版内容生成
     *
     * @param template 短信模版 (您的登录验证码：{1}，有效期{2}分钟，如非本人操作，请忽略本短信！)
     * @param args     模版参数 (3213, 5)
     * @return
     */
    public static String genMsgContent(String template, Object... args) {
        if (args == null | args.length == 0) {
            return template;
        }
        for (int i = 0; i < args.length; i++) {
            String fmt = "{" + (i + 1) + "}";
            String arg = args[i].toString();
            template = template.replace(fmt, arg);
        }
        return template;
    }

    public static void main(String[] args) {
        System.out.println(genCode(6));
        String template = "您的登录验证码：{1}，有效期{2}分钟，如非本人操作，请忽略本短信！";
        System.out.println(genMsgContent(template, 3241, 5));
    }
}
