package com.cn.weixuan.util;

import java.io.File;
import java.util.Base64;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;

public class MultipartUtil {

	public static String[] savePicToLocal(MultipartFile file, String localPath) {
		try {
			String name = file.getOriginalFilename();
			String suffixName = name.substring(name.lastIndexOf("."));// 获取后缀名
			String datePath = DateUtil.format(DateUtil.date(), "yyyy_MM_dd");// 日期
			String picName = UUID.randomUUID().toString().replace("-", "") + suffixName;// uuid+文件后缀
			String picPath = File.separator + datePath + File.separator + picName;
			// 存储路径
			File destFile = new File(localPath + File.separator + picPath);
			// 路径不存在
			if (!destFile.getParentFile().exists()) {
				// 创建路径
				FileUtil.mkdir(destFile.getParentFile());
			}
			String base64 = Base64.getEncoder().encodeToString(file.getBytes());
			file.transferTo(destFile);
			// 返回图片的路径及图片的base64
			return new String[] { picPath, base64 };
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[] { "", "" };
	}

}
