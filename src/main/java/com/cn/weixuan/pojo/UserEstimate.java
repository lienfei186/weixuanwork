package com.cn.weixuan.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UserEstimate implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String userName;
    private String details;
    private String projectName;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private String addTime;
    private Double score;
    private Integer userId;

    private Integer projectId;
    private Integer status;
}
