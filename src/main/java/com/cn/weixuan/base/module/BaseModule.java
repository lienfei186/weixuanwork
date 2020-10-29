package com.cn.weixuan.base.module;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BaseModule<T> implements Serializable {
	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = 1L;
	private String ID;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
