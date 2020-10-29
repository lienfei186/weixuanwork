package com.cn.weixuan.sms.inter.http.v1.dto.request;


import com.cn.weixuan.sms.inter.framework.dto.TemplateSmsIdAndMobile;

public class TemplateSmsSendRequest extends SmsBaseRequest {

	/**
	*/
	private static final long serialVersionUID = 1L;
	private TemplateSmsIdAndMobile[] smses;
	private String templateId;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public TemplateSmsIdAndMobile[] getSmses() {
		return smses;
	}

	public void setSmses(TemplateSmsIdAndMobile[] smses) {
		this.smses = smses;
	}

}
