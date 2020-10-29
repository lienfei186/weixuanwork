package com.cn.weixuan.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.ResourceBundle;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @description:封装map数据，把数据发送出去
 **/
@Slf4j
public class MapParamPass {
    private final static Logger logger = LoggerFactory.getLogger(MapParamPass.class);
    private static ResourceBundle resourceBundle;

    public static String doPost(String url, Map<String, Object> map) {
        HttpPost httpPost = null;
        String result = null;
        try {
            HttpClient client = new DefaultHttpClient();
            httpPost = new HttpPost(url);
            JSONObject putData = new JSONObject();
            if (map != null && map.size() > 0) {
                //通过entrySet遍历map里面的值
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (entry != null && entry.getKey() != null && entry.getValue() != null) {
                        //把map里面的键-值对数据存到putData里面，同时转化为json格式
                        putData.put(entry.getKey(), entry.getValue());
                    }
                }
                StringEntity se = new StringEntity(putData.toString(), "utf-8");
                httpPost.setEntity(se); // post方法中，加入json数据
                httpPost.setHeader("Content-Type", "application/json");

            }
            log.info(putData.toString());
            HttpResponse response = client.execute(httpPost);

            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "utf-8");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("发送到接口出错", ex);
        }
        return result;
    }

    /**
     * 读取配置文件中指定的key值
     *
     * @param fileName
     * @param key
     * @return
     */
    public static String getPropertiesVal(String fileName, String key) {
        String propertiesVal;

        try {
            resourceBundle = ResourceBundle.getBundle(fileName);
        } catch (Exception e) {
            logger.error("找不到 " + fileName + ".properties 文件");
            return null;
        }
        try {
            propertiesVal = resourceBundle.getString(key);
        } catch (Exception e) {
            logger.error(fileName + ".properties 文件中没有找到 " + key + " 对应的值");
            return null;
        }

        return propertiesVal;
    }
}
