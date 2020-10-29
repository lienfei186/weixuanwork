package com.cn.weixuan.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.weixuan.pojo.User;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cn.weixuan.base.module.Response;
import com.cn.weixuan.pojo.UserInfo;
import com.cn.weixuan.service.UserInfoService;
import com.cn.weixuan.util.HttpUtils;
import com.cn.weixuan.util.MultipartUtil;

import lombok.extern.slf4j.Slf4j;

/***
 * 身份证信息
 * 
 * @author by
 *
 */
@Slf4j
@RestController
@RequestMapping("/idNo")
public class IdNoRecognizeController {

	@Autowired
	private Response response;

	@Autowired
	private UserInfoService userInfoService;

	String host = "http://dm-51.data.aliyun.com";
	String path = "/rest/160601/ocr/ocr_idcard.json";
	String appcode = "bb2f88017cca444fad1f43a88bba4f8b";
	String method = "POST";

	@Value("${web.upload-path}")
	private String localPath;

	@ResponseBody
	@RequestMapping(value = "/idNoRecognize", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response idNoRecognize(MultipartFile[] files, String phone, HttpServletRequest request) throws Exception {
		log.info("身份证认证信息：{}", phone);
		// 获取正面照片
		String[] nameAndBase64 = MultipartUtil.savePicToLocal(files[0], localPath);
		String facePicPath = nameAndBase64[0];
		// 获取反面照片
		String backPicPath = MultipartUtil.savePicToLocal(files[1], localPath)[0];
		String faceBase64 = nameAndBase64[1];
		log.info("正面照片路径：{}", facePicPath);
		log.info("反面照片路径：{}", backPicPath);

		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		// 根据API的要求，定义相对应的Content-Type
		headers.put("Content-Type", "application/json; charset=UTF-8");

		Map<String, String> querys = new HashMap<String, String>();
		// 对图像进行base64编码
		// String imgBase64 = FileUtils.img_base64(imgFile);
		// System.out.println(imgBase64);
		// String imgBase32 = FileUtils.img_base64(imgFileBm);
		// configure配置
		JSONObject configObj = new JSONObject();
		configObj.put("side", "face");
		configObj.put("bside", "bface");

		String config_str = configObj.toString();

		// 拼装请求body的json字符串
		JSONObject requestObj = new JSONObject();
		requestObj.put("image", faceBase64);
//        requestObj.put("image", imgBase32);
		if (configObj.size() > 0) {
			requestObj.put("configure", config_str);
		}
		String bodys = requestObj.toString();

		HttpResponse responses = HttpUtils.doPost(host, path, method, headers, querys, bodys);
		int stat = responses.getStatusLine().getStatusCode();
		if (stat != 200) {
			System.out.println("Http code: " + stat);
			System.out.println("http header error msg: " + responses.getFirstHeader("X-Ca-Error-Message"));
			System.out.println("Http body error msg:" + EntityUtils.toString(responses.getEntity()));
			return response.failure(1042,
					"Http code: " + stat + "Http body error msg:" + EntityUtils.toString(responses.getEntity()));
		}
		String res = EntityUtils.toString(responses.getEntity());
		JSONObject res_obj = JSON.parseObject(res);
		User user = userInfoService.selectUserInfo(phone);
		// 地址
		String address = res_obj.getString("address");
		// 身份证真实用户名
		String name = res_obj.getString("name");
		String sex = res_obj.getString("sex");
		String num = res_obj.getString("num");
		//保存ocr识别后的信息
		UserInfo userInfo = new UserInfo();
		userInfo.setAddress(address);
		userInfo.setName(name);
		userInfo.setSex(sex);
		userInfo.setIdcard(num);
		userInfo.setUserType(user.getRole());
		userInfo.setEmail(user.getEmail());
		userInfo.setTelephone(phone);
		userInfo.setUserId(user.getUserId().toString());
		userInfo.setImg1(facePicPath);
		userInfo.setImg2(backPicPath);
		// 认证成功后保存用户信息
		userInfoService.insertUserInfo(userInfo);
		// todo信息入库
		return response.success("认证成功" + userInfo, 0);
	}

	
	
	/**
     * 身份证信息修改;
     *
     * @return
     */
    @RequestMapping(value = "/updateUserInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Response updateUserInfo(UserInfo userInfo, Integer id, HttpServletRequest request) {
        //用户身份证信息
    	UserInfo findByUserInfo = this.userInfoService.selectUserInfoIdcard(id);
        int r = this.userInfoService.updateUserInfo(findByUserInfo);
        //删除验证码
        if (r > 0) {
            return response.success(0, "修改用户信息成功！", userInfo);
        }
        return response.failure(1001, "修改用户信息失败！", null);
    }
}
