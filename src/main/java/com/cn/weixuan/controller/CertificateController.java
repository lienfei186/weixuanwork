package com.cn.weixuan.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.weixuan.base.module.Response;
import com.cn.weixuan.pojo.CreditInfo;
import com.cn.weixuan.pojo.User;
import com.cn.weixuan.pojo.UserInfo;
import com.cn.weixuan.service.CreditInfoService;
import com.cn.weixuan.service.UserInfoService;
import com.cn.weixuan.util.FileUtils;
import com.cn.weixuan.util.HttpUtils;

import com.cn.weixuan.util.MultipartUtil;
import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/***
 * 营业执照
 * 
 * @author by
 *
 */
@Slf4j
@RestController
@RequestMapping("/certificate")
public class CertificateController {

	@Autowired
	private Response response;

	@Value("${web.upload-path}")
	private String localPath;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private CreditInfoService creditInfoService;

	String host = "https://dm-58.data.aliyun.com";
	String path = "/rest/160601/ocr/ocr_business_license.json";
	String method = "POST";
	String appcode = "bb2f88017cca444fad1f43a88bba4f8b";

	public String exchageIdcard(String base64) throws Exception {
		String host = "https://dm-58.data.aliyun.com";
		String path = "/rest/160601/ocr/ocr_business_license.json";
		String imgFile = "本地图片路径或者图片的url";
		String method = "POST";

		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		// 根据API的要求，定义相对应的Content-Type
		headers.put("Content-Type", "application/json; charset=UTF-8");

		Map<String, String> querys = new HashMap<String, String>();
		// 对图像进行base64编码
		String imgBase64 = FileUtils.img_base64(imgFile);

		// configure配置
		JSONObject configObj = new JSONObject();
		configObj.put("side", "face");

		String config_str = configObj.toString();

		// 拼装请求body的json字符串
		JSONObject requestObj = new JSONObject();
		requestObj.put("image", base64);
		if (configObj.size() > 0) {
			requestObj.put("configure", config_str);
		}
		String bodys = requestObj.toString();

		/**
		 * 重要提示如下: HttpUtils请从
		 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
		 * 下载
		 *
		 * 相应的依赖请参照
		 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
		 */
		HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
		int stat = response.getStatusLine().getStatusCode();
		if (stat != 200) {
			System.out.println("Http code: " + stat);
			System.out.println("http header error msg: " + response.getFirstHeader("X-Ca-Error-Message"));
			System.out.println("Http body error msg:" + EntityUtils.toString(response.getEntity()));
			return "Http body error msg" + EntityUtils.toString(response.getEntity());
		}

		String res = EntityUtils.toString(response.getEntity());
		JSONObject res_obj = JSON.parseObject(res);

		System.out.println(res_obj.toJSONString());
		return res_obj.toJSONString();

	}

	@ResponseBody
	@RequestMapping(value = "/certificateRecognize", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response certificateRecognize(MultipartFile[] files, String phone, HttpServletRequest request)
			throws Exception {
		log.info("身份证认证信息：{}", phone);
		String host = "http://dm-51.data.aliyun.com";
		String path = "/rest/160601/ocr/ocr_idcard.json";
		String appcode = "bb2f88017cca444fad1f43a88bba4f8b";
		String method = "POST";
		// 获取正面照片
		String[] nameAndBase64 = MultipartUtil.savePicToLocal(files[0], localPath);
		String facePicPath = nameAndBase64[0];
		// 获取反面照片
		String backPicPath = MultipartUtil.savePicToLocal(files[1], localPath)[0];
		String faceBase64 = nameAndBase64[1];

		// 营业执照照片
		String[] yyNameAndBase64 = MultipartUtil.savePicToLocal(files[2], localPath);
		// 营业执照照片
		String yyPicPath = yyNameAndBase64[0];
		String yyPicBase64 = yyNameAndBase64[1];
		log.info("获取正面照片：{}", facePicPath);
		log.info("获取反面照片：{}", backPicPath);
		log.info("获取营业执照照片：{}", yyPicPath);
		log.info("获取营业执Base64：{}", yyPicBase64);

		// 获取营业执照信息；不过暂时你没有返回，你可以返回一个实体类，这样所有的信息你就能对应了
		String yyStr = this.exchageIdcard(yyPicBase64);
		JSONObject yy_obj = JSON.parseObject(yyStr);
		String type = yy_obj.getString("type");
		String legalname = yy_obj.getString("person");
		String businessName = yy_obj.getString("name");
		String creditcode = yy_obj.getString("reg_num");
		String business = yy_obj.getString("business");
		String businessAddress = yy_obj.getString("address");
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		// 根据API的要求，定义相对应的Content-Type
		headers.put("Content-Type", "application/json; charset=UTF-8");

		Map<String, String> querys = new HashMap<String, String>();

		// configure配置
		JSONObject configObj = new JSONObject();
		configObj.put("side", "face");
		configObj.put("bside", "bface");

		String config_str = configObj.toString();

		// 拼装请求body的json字符串
		JSONObject requestObj = new JSONObject();
		requestObj.put("image", faceBase64);
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
		//保存执照信息
		CreditInfo creditInfo = new CreditInfo();
		creditInfo.setUserId(user.getUserId().toString());
		creditInfo.setUserType(user.getRole());
		creditInfo.setSex(sex);
		creditInfo.setSfzaddress(address);
		creditInfo.setBusnessaddress(businessAddress);
		creditInfo.setBeizhu(user.getBeizhu());
		creditInfo.setBusnissname(businessName);
		creditInfo.setLegalname(legalname);
		creditInfo.setName(name);
		creditInfo.setIdcard(num);
		creditInfo.setType(type);
		creditInfo.setCreditcode(creditcode);
		creditInfo.setBusiness(business);
		creditInfo.setImg1(facePicPath);
		creditInfo.setImg2(backPicPath);
		creditInfo.setImg3(yyPicPath);
		creditInfoService.insertCreditInfo(creditInfo);
		return response.success("执照认证成功！" + creditInfo, 0);
	}
	
	/**
     * 执照信息修改;
     *
     * @return CreditInfo
     */
    @RequestMapping(value = "/updateCreditInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Response updateCreditInfo(CreditInfo creditInfo, Integer id, HttpServletRequest request) {
        //用户执照身份证信息
    	log.info("开始查找执照信息");
    	CreditInfo findByCreditInfo = this.creditInfoService.selectCreditInfoId(id);
    	log.info("开始修改执照信息");
    	int r = this.creditInfoService.updateCreditInfo(findByCreditInfo);
        //删除验证码
        if (r > 0) {
            return response.success(0, "修改用户执照信息成功！", creditInfo);
        }
        return response.failure(1001, "修改用户执照信息失败！", null);
    }
}
