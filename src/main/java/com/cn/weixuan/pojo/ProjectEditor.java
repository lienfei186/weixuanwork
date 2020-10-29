package com.cn.weixuan.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProjectEditor implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer projectId;
    private String projectName;
    private String leadTime;
    private String projects;
    private String picture;
    private String url;
    private Integer userId;
}
