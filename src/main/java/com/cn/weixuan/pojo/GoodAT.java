package com.cn.weixuan.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class GoodAT implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer goodatId;
    private String goodatName;
    private Integer userId;

}
