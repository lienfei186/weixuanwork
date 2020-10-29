package com.cn.weixuan.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserProject implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer projectId;
    private Integer userId;
}
