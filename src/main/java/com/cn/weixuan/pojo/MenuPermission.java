package com.cn.weixuan.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class MenuPermission implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer menuId;
    private Integer permissionId;
}
