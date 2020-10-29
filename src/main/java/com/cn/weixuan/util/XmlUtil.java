package com.cn.weixuan.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import com.fasterxml.jackson.databind.MapperFeature;

public class XmlUtil {

	public static String writeObjectXml(Object obj) throws JsonProcessingException {  
		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.setDefaultUseWrapper(false);
        //字段为null，自动忽略，不再序列化
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //XML标签名:使用骆驼命名的属性名，  
        xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE); 
        //设置转换模式 
        xmlMapper.enable(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME);  
		return xmlMapper.writeValueAsString(obj);
	}
}
