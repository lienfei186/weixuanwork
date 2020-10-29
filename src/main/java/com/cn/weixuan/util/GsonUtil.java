package com.cn.weixuan.util;

import com.google.gson.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GsonUtil {
    public static Gson getGson() {
        Gson gson = new GsonBuilder().registerTypeAdapter(HashMap.class, new JsonDeserializer<HashMap>() {
            public HashMap<String, Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                HashMap<String, Object> resultMap = new HashMap();
                JsonObject jsonObject = json.getAsJsonObject();
                Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
                for (Map.Entry<String, JsonElement> entry : entrySet) {
                    resultMap.put(entry.getKey(), entry.getValue());
                }
                return resultMap;
            }

        }).create();
        return gson;
    }

    public static String toString(Object obj) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        return gson.toJson(obj);
    }

    /**
     * 转换json字符串为Json字符串
     * @param jsonStr
     * @param <T>
     * @return
     */
    public static JsonObject  toJsonObject(String jsonStr) {
        JsonParser jsonParser = new JsonParser();
        return (JsonObject) jsonParser.parse(jsonStr);
    }


    /**
     * 对象持久化到本地
     * @param obj
     * @param localPath
     * @param <T>
     * @return
     */
    public static <T> boolean localWrite(T obj, String localPath) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(localPath);
            byte[] bObj = Base64.getEncoder().encode(toString(obj).getBytes());
            fos.write(bObj);
            fos.flush();
            fos.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 本地反序列化
     * @param clazz
     * @param localPath
     * @return
     */
    public static <T>T localRead(Class<T> clazz, String localPath) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(localPath);
            int size = 0;
            byte[] buf = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while((size = fis.read(buf))!=-1){
                sb.append(new String(buf, 0, size));
            }
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd hh:mm:ss")
                    .create();
            return gson.fromJson(new String(Base64.getDecoder().decode(sb.toString())), clazz);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }


}
