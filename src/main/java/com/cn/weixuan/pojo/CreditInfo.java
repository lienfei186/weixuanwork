package com.cn.weixuan.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_credit")
public class CreditInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1925514L;

	private int id;
	private String userName;
	private String name;
	private String userId;
	private String idcard;
	private String yhkcard;
	private String beizhu;
	private String userType;
	private String telephone;
	private String email;
	private String sfzaddress;
	private String img1;
	private String img2;
	private String sex;
	private String img3;
	private String legalname;
	private String busnissname;
	private String creditcode;
	private String busnessaddress;
	private String type;
	private String business;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getYhkcard() {
		return yhkcard;
	}
	public void setYhkcard(String yhkcard) {
		this.yhkcard = yhkcard;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getImg1() {
		return img1;
	}
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	public String getImg2() {
		return img2;
	}
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getImg3() {
		return img3;
	}
	public void setImg3(String img3) {
		this.img3 = img3;
	}
	public String getLegalname() {
		return legalname;
	}
	public void setLegalname(String legalname) {
		this.legalname = legalname;
	}
	public String getBusnissname() {
		return busnissname;
	}
	public void setBusnissname(String busnissname) {
		this.busnissname = busnissname;
	}
	public String getCreditcode() {
		return creditcode;
	}
	public void setCreditcode(String creditcode) {
		this.creditcode = creditcode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSfzaddress() {
		return sfzaddress;
	}

	public void setSfzaddress(String sfzaddress) {
		this.sfzaddress = sfzaddress;
	}

	public String getBusnessaddress() {
		return busnessaddress;
	}

	public void setBusnessaddress(String busnessaddress) {
		this.busnessaddress = busnessaddress;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	@Override
	public String toString() {
		return "CreditInfo{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", name='" + name + '\'' +
				", userId='" + userId + '\'' +
				", idcard='" + idcard + '\'' +
				", yhkcard='" + yhkcard + '\'' +
				", beizhu='" + beizhu + '\'' +
				", userType='" + userType + '\'' +
				", telephone='" + telephone + '\'' +
				", email='" + email + '\'' +
				", sfzaddress='" + sfzaddress + '\'' +
				", img1='" + img1 + '\'' +
				", img2='" + img2 + '\'' +
				", sex='" + sex + '\'' +
				", img3='" + img3 + '\'' +
				", legalname='" + legalname + '\'' +
				", busnissname='" + busnissname + '\'' +
				", creditcode='" + creditcode + '\'' +
				", busnessaddress='" + busnessaddress + '\'' +
				", type='" + type + '\'' +
				", business='" + business + '\'' +
				'}';
	}
}
