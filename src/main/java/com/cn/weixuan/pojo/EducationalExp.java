package com.cn.weixuan.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
//教育经历
@Data
public class EducationalExp implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer educationId;
    private String  schoolName;
    private String major;
    private String education;
    @JsonFormat(pattern = "yyyy.MM")
    @DateTimeFormat(pattern = "yyyy.MM")
    private Date startTime;
    @JsonFormat(pattern = "yyyy.MM")
    @DateTimeFormat(pattern = "yyyy.MM")
    private Date endTime;
    private Integer userId;
    private String synopsis;
}
